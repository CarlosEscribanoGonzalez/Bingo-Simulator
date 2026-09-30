package com.mycompany.bingo;
import java.util.Iterator;
import structures.*;

public class DrawnBalls implements Iterable<Integer> {
    private List<Integer> drawn;
    
    public DrawnBalls(){
        drawn = new SimpleLinkedList<>();
    }
    
    public void add(int elem){
        drawn.add(elem);
    }
    
    public void print(){
        for(Integer element : this){ 
            System.out.print(element + "\t");
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return drawn.iterator();
    }
}
