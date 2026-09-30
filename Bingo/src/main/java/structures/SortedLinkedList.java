package structures;
import java.util.Iterator;

public class SortedLinkedList<Type extends Comparable>
    implements SortedListDS<Type>, Iterable<Type> {
  private SimpleNode<Type> first;

  public SortedLinkedList() {
    first = null;
  }

  @Override
  public boolean add(Type elem) {
    SimpleNode<Type> newNode = new SimpleNode<>(elem);
    if(first == null){
        first = newNode;
    } else if (elem.compareTo(first.getValue()) < 0){
        newNode.setNext(first);
        first = newNode;
    } else{
        SimpleNode<Type> current = first;
        while(current.getNext() != null && elem.compareTo(current.getNext().getValue()) > 0){
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }
    return true;
  }

  @Override
  public void clear() {
    first = null;
  }

  @Override
  public SortedListDS<Type> clone() {
    SortedListDS<Type> newList = new SortedLinkedList<>();
    for (Type element : this) {
      newList.add(element);
    }
    return newList;
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
  public boolean equals(SortedListDS<Type> other_list) {
    final int CURR_NUM_ELEMS = this.size();
    if (CURR_NUM_ELEMS != other_list.size()) {
      return false;
    }
    Iterator<Type> list_iterator = other_list.iterator();
    for (Type element : this) {
      if (!element.equals(list_iterator.next())) {
        return false;
      }
    }

    return true;
  }

  private SimpleNode<Type> getNode(int pos) throws IndexOutOfBoundsException {
    if (isEmpty() || pos < 0) {
      throw new IndexOutOfBoundsException();
    }
    SimpleNode<Type> current = first;
    for (int i = 0; i < pos; ++i) {
      if (current.getNext() == null) {
        throw new IndexOutOfBoundsException();
      }
      current = current.getNext();
    }
    return current;
  }

  @Override
  public Type get(int pos) throws IndexOutOfBoundsException {
    return this.getNode(pos).getValue();
  }

  @Override
  public int indexOf(Type elem) {
    int index = 0;
    for(Type element : this) {
      if (element.equals(elem)) {
        return index;   
      }
      ++index;
    }
    return -1;
  }

  @Override
  public boolean isEmpty() {
    // Tiempo O(1).
    return first == null;
  }

  @Override
  public Iterator<Type> iterator() {
    return new SortedLinkedListIterator();
  }

  @Override
  public Type remove(int pos) throws IndexOutOfBoundsException {
    if (pos == 0 && !isEmpty()) {
      SimpleNode<Type> nodeToRemove = first;
      first = first.getNext();
      return nodeToRemove.getValue();
    } 
    else {
      SimpleNode<Type> previous = this.getNode(pos - 1);
      if (previous.getNext() == null) {
        throw new IndexOutOfBoundsException();
      }
      SimpleNode<Type> nodeToRemove = previous.getNext();
      previous.setNext(nodeToRemove.getNext());
      return nodeToRemove.getValue();
    }
  }

  @Override 
  public Type removeElem(Type elem) {
    int index = indexOf(elem);
        remove(index);
        return elem;
  }

  @Override
  public int size() {
    int size = 0;
    for (Type element : this) {
      ++size;
    }
    return size;
  }

  public SimpleNode<Type> first(){
      return first;
  }
  
  public class SortedLinkedListIterator implements Iterator<Type> {

    private SimpleNode<Type> current;

    public SortedLinkedListIterator() {
      current = first;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Type next() {
      Type value = current.getValue();
      current = current.getNext();
      return value;
    }
  }
}