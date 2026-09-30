package structures;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import structures.SetDS;

public class Set<Type> implements SetDS<Type> {
  private Type[] elements;
  private final int INITIAL_CAPCITY = 2;
  private int cardinal;
  private Random generator;
  
  public Set() {
    elements = (Type[]) new Object[INITIAL_CAPCITY];
    cardinal = 0;
    generator = new Random();
  }

  private void addLast(Type elem) {
    final int CURR_NUM_ELEMS = this.size();
    final int CAPACITY = elements.length;

    if (CURR_NUM_ELEMS < CAPACITY) {
      elements[CURR_NUM_ELEMS] = elem;
      ++cardinal;
    } 
    else {
      Type[] tmp = (Type[]) new Object[CURR_NUM_ELEMS * 2];
      for (int i = 0; i < CURR_NUM_ELEMS; ++i) {
        tmp[i] = elements[i];
      }
      tmp[CURR_NUM_ELEMS] = elem;
      elements = tmp;
      ++cardinal;
    }
  }

  @Override
  public boolean add(Type elem) {
    if(contains(elem)){
        return false;
    } else{
        addLast(elem);
        return true;
    }
  }
  
  public void addNum(Type elem){
      addLast(elem);
  }

  @Override
  public void addAll(SetDS<Type> other_set) {
    for(Type element : other_set){
        this.add(element);
    }
  }

  @Override
  public void clear() {
    elements = (Type[]) new Object[INITIAL_CAPCITY];
    cardinal = 0;
  }

  @Override
  public boolean contains(Type elem) {
    for (Type element : this) {
      if (element.equals(elem)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(SetDS<Type> other_set) {
    for(Type element : other_set){
        if(!contains(element)){
            return false;
        }
    }
    return true;
  }

  @Override
  public boolean equals(SetDS<Type> other_set) {
    final int CURR_NUM_ELEMS = this.size();
    if (CURR_NUM_ELEMS != other_set.size()) {
      return false;
    }
    for (Type element : this) {
      if (!other_set.contains(element)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    final int CURR_NUM_ELEMS = this.size();
    return CURR_NUM_ELEMS == 0;
  }

  @Override
  public Iterator iterator() {
    return new SmartArraySetIterator<>();
  }

  @Override
  public Type randomElement() throws NoSuchElementException {
    if(isEmpty()) throw new NoSuchElementException("Set must have at least 1 element");
    int index = generator.nextInt(cardinal);
    Type valueToReturn = elements[index];
    remove(index);
    return valueToReturn;
  }
  
  public void remove(int i){
    elements[i] = elements[cardinal-1];
    cardinal--;
  }

  @Override
  public boolean remove(Type elem) {
    for(int i = 0; i < cardinal; i++){
        if(elements[i].equals(elem)){
            remove(i);
            return true;
        }
    }
    return false;
  }

  @Override
  public void removeAll(SetDS<Type> other_set) {
    for(Type element : other_set){
        remove(element);
    }
  }

  @Override
  public void retainAll(SetDS<Type> other_set) {  
    for (int i = 0; i < cardinal; ++i){ 
        Type elem = elements[i];
        if(other_set.contains(elem)){
            remove(i); 
            --i;
        }
    }
  }

  @Override
  public int size() {
    return cardinal;
  }

  private class SmartArraySetIterator<Type> implements Iterator<Type> {
    private int index;

    public SmartArraySetIterator() {
      index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < cardinal;
    }

    @Override
    public Type next() {
      Type element = (Type) elements[index];
      index = index + 1;
      return element;
    }
  }
}