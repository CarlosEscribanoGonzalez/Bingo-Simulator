package structures;

public class SimpleNode<Type> {
    private Type value;
    private SimpleNode next;
    private SimpleNode prev;
    
    public SimpleNode(Type elem){
        this.value = elem;
    }

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }

    public SimpleNode getNext() {
        return next;
    }

    public void setNext(SimpleNode next) {
        this.next = next;
    }
}