package structures;
import java.util.Iterator;

public class LinkedListIterator<Type> implements Iterator<Type> {
    private SimpleNode<Type> current;
    public LinkedListIterator(SimpleNode<Type> first){
        current = first;
    }
    
    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Type next() {
        Type element = current.getValue();
        current = current.getNext();
        return element;
    }
}