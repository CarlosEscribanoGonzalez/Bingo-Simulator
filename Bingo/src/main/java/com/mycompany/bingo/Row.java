package com.mycompany.bingo;
import java.util.Iterator;
import structures.*;

public class Row {
    private final SimpleLinkedList<Integer> initialRow;
    private final SimpleLinkedList<Integer> crossedoutRow; //Row that displays if a line is called
    
    public Row(){
        initialRow = new SimpleLinkedList<>();
        crossedoutRow = new SimpleLinkedList<>();
    }
    
    public void add(int num){
        initialRow.add(num);
    }
    
    public boolean crossout(int ball){
        for(Iterator<Integer> it = initialRow.iterator(); it.hasNext();) {
            Integer element = it.next();
            if(element == ball){
                crossedoutRow.add(element);
                initialRow.removeElem(element);
                if(checkLine()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkLine(){
        return initialRow.isEmpty();
    }
    
    public void printRow(){
        for(Integer value : crossedoutRow){
            System.out.print(value + "\t");
        }
    }
}
