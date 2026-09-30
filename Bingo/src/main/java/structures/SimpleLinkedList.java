
package structures;
import java.util.Iterator;

public class SimpleLinkedList<Type> implements List<Type>, Iterable<Type>{
    private SimpleNode<Type> first; 
    private SimpleNode<Type> last;
    int length;
    
    public SimpleLinkedList(){
        first = null;
        last = first;
        length = 0;
    }

    @Override
    public boolean add(Type elem) {
        if(first == null){
            first = new SimpleNode<>(elem);
            last = first;
        } 
        else{
            SimpleNode<Type> newNode = new SimpleNode<>(elem);
            last.setNext(newNode);
            last = newNode;
        }
        length++;
        return true;
    }

    @Override
    public void add(int pos, Type elem) throws IndexOutOfBoundsException {
        SimpleNode<Type> newNode = new SimpleNode<>(elem);
        if (pos == 0){
            newNode.setNext(first);
            first = newNode;
        } else{
            SimpleNode<Type> previous = getNode(pos-1);
            newNode.setNext(previous.getNext());
            previous.setNext(newNode);
        }
        length++;
        last = this.getNode(length);
    }

    @Override
    public void clear() {
        first = null;
        length = 0;
        last = null;
    }

    @Override
    public List<Type> clone() {
        List<Type> newList = new SimpleLinkedList<>();
        for(Type element : this){
            newList.add(element);
        }
        return newList;
    }

    @Override
    public boolean contains(Type elem) {
        for(Type element : this){
            if(element.equals(elem)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(List<Type> list) {
        if(this.size() != list.size()){
            return false;
        } 
        else{
            Iterator<Type> this_iterator = this.iterator();
            Iterator<Type> list_iterator = list.iterator();
            while(this_iterator.hasNext()){
                for(Type element : this){
                    if(!this_iterator.next().equals(list_iterator.next())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public SimpleNode<Type> getNode(int pos) throws IndexOutOfBoundsException{
        if(isEmpty() || pos<0) throw new IndexOutOfBoundsException();
        SimpleNode<Type> current = first;
        for(int i = 0; i < pos; i++){
            if(current.getNext() == null){
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }
        return current;
    }
    
    @Override
    public Type get(int pos) throws IndexOutOfBoundsException {
        return getNode(pos).getValue();
    }
    
    @Override
    public int indexOf(Type elem) {
        int index = 0;
        for(Type element : this){
            if(element.equals(elem)){
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(this.first);
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Type remove(int pos) throws IndexOutOfBoundsException {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(pos == 0){
            SimpleNode<Type> nodeToRemove = first;
            first = first.getNext();
            length--;
            return nodeToRemove.getValue();
        } else{
            SimpleNode<Type> previous = this.getNode(pos-1);
            if(previous.getNext() == null) throw new IndexOutOfBoundsException();
            SimpleNode<Type> nodeToRemove = previous.getNext();
            previous.setNext(nodeToRemove.getNext());
            length--;
            last = this.getNode(length-1);
            return nodeToRemove.getValue();
        }
    }

    @Override
    public boolean removeElem(Type elem) {
        int index = indexOf(elem);
        if(index == -1){
            return false;
        } else{
            remove(index);
            return true;
        }
    }

    @Override
    public Type set(int pos, Type elem) throws IndexOutOfBoundsException {
        SimpleNode<Type> nodeToModify = getNode(pos);
        Type oldValue = nodeToModify.getValue();
        nodeToModify.setValue(elem);
        return oldValue;
    }

    @Override
    public int size() {
        return length;
    }
}