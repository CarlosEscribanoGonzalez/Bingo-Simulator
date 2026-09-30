package structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public interface SetDS<Type> extends Iterable<Type> {
  public boolean add(Type elem);
  public void addAll(SetDS<Type> other_set);
  public void clear();
  public boolean contains(Type elem);
  public boolean containsAll(SetDS<Type> other_set);
  public boolean equals(SetDS<Type> other_set);
  public boolean isEmpty();
  @Override
  public Iterator iterator();
  public Type randomElement() throws NoSuchElementException;
  public boolean remove(Type elem);
  public void removeAll(SetDS<Type> other_set);
  public void retainAll(SetDS<Type> other_set);
  public int size();
}
