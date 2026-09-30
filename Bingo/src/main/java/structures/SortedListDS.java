package structures;
import java.util.Iterator;

public interface SortedListDS<Type extends Comparable> {
  public boolean add(Type elem);
  public void clear();
  public SortedListDS<Type> clone();
  public boolean contains(Type elem);
  public boolean equals(SortedListDS<Type> list);
  public Type get(int pos) throws IndexOutOfBoundsException;
  public int indexOf(Type elem);
  public Iterator iterator();
  public boolean isEmpty();
  public Type remove(int pos) throws IndexOutOfBoundsException;
  public Type removeElem(Type elem);
  public int size();
}