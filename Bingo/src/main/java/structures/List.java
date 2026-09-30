package structures;
import java.util.Iterator;

public interface List<Type> {
  public boolean add(Type elem);
  public void add(int pos, Type elem) throws IndexOutOfBoundsException;
  public void clear();
  public List<Type> clone();
  public boolean contains(Type elem);
  public boolean equals(List<Type> list);
  public Type get(int pos) throws IndexOutOfBoundsException;
  public int indexOf(Type elem);
  public Iterator iterator();
  public boolean isEmpty();
  public Type remove(int pos) throws IndexOutOfBoundsException;
  public boolean removeElem(Type elem);
  public Type set(int pos, Type elem) throws IndexOutOfBoundsException;
  public int size();
}