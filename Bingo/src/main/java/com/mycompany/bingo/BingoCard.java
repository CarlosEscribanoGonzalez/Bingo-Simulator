package com.mycompany.bingo;
import java.util.Iterator;
import java.util.Random;
import structures.*;

public class BingoCard {
    private final Object[][]card; //Cards are arrays to which numbers are added from a sorted linked list
    private final Set totalBalls;
    private final Random generator;
    private final int MAX_NUMBERS; //Highest possible number
    private final SortedLinkedList<Integer> cardNumbers;
    private final Row[] rows; //Card rows
    private final SimpleLinkedList<Integer> drawn;
   
    public BingoCard(int max){
        card = new Object[3][9];
        totalBalls = new Set<>();
        generator = new Random();
        MAX_NUMBERS = max;
        cardNumbers = new SortedLinkedList<>();
        rows = new Row[3];
        drawn = new SimpleLinkedList<>();
    }
    
    public void generateCard(){
        for(int i = 1; i <= MAX_NUMBERS; i++){
            totalBalls.addNum(i);
        }
        for(int i = 0; i < 15; i++){
            int num = (Integer) totalBalls.randomElement();
            cardNumbers.add(num);
        }
        fillCard();
    }
    
    public void fillCard(){
        for(int i = 0; i < card.length; i++){
            int asterisks = 0; //Number of * in the row
            int numbers = 0; //Number of numbers in the row
            
            for(int j = 0; j < card[i].length; j++){
                if(asterisks == 4){
                    numbers++;
                } else if (numbers == 5){
                    asterisks++;
                    card[i][j] = "*";
                } else{
                    boolean isNum = getRandomBool();
                    if(isNum){
                        numbers++;
                    } else{
                        asterisks++;
                        card[i][j] = "*";
                    }
                }
            }
        }
        
        for(int j = 0; j < 9; j++){ //Last row is reordered, since asterisk-only columns shouldn't be generated
            if(card[0][j] == null && card[1][j] == null && card[2][j] == null){
                replace(null, j);
            } else if(card[0][j] == "*" && card[1][j] == "*" && card[2][j] == "*"){
                replace("*", j);
            }
        }
        addNumbers();
    }
    
    public boolean getRandomBool(){
        int rand = generator.nextInt();
        return rand % 2 == 0;
    }
    
    public void replace(Object obj, int pos){
        boolean switched = false;
        for(int j = 0; j < 9 && !switched; j++){
            if(obj == null){
                if(card[2][j] == "*" && (card[1][j] != null || card[0][j] != null)){
                    Object aux = card[2][j];
                    card[2][j] = card[2][pos];
                    card [2][pos] = aux;
                    switched = true;
                } 
            } else{
                if(card[2][j] == null && (card[1][j] != "*" || card[0][j] != "*")){
                    Object aux = card[2][j];
                    card[2][j] = card[2][pos];
                    card [2][pos] = aux;
                    switched = true;
                } 
            }
        }
    }
    
    public void addNumbers(){
        SimpleNode<Integer> current = cardNumbers.first();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                if(card[j][i] != "*"){
                    card[j][i] = current.getValue();
                    current = current.getNext();
                }
            }
        }
        createRows();
    }
    
    public void createRows(){
        for(int i = 0; i < card.length; i++){
            rows[i] = new Row();
            for(int j = 0; j < card[i].length; j++){
                if(card[i][j] != "*"){
                    rows[i].add((int) card[i][j]);
                }
            }
        }
    }
    
    public void printCard(){
        for(int i = 0; i < card.length; i++){
            for(int j = 0; j < card[i].length; j++){
                if(card[i][j] != "*" && (int)card[i][j] < 10 || card[i][j] == "*"){
                    System.out.print(card[i][j] + "    ");
                } else{
                    System.out.print(card[i][j] + "   ");
                }
            }
            System.out.println("");
        }
    }
    
    public boolean check(int ball, Game game, Player player){
        for(Iterator<Integer> it = cardNumbers.iterator(); it.hasNext();) {
            Integer element = it.next();
            if(element.equals(ball)){
                for(int i = 0; i < rows.length; ++i){
                    if(rows[i].crossout(ball)){
                        game.callLine(player, rows[i]);
                    } 
                }
                drawn.add(cardNumbers.removeElem(element));
                if(checkBingo()){
                    game.callBingo(player, drawn);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkBingo(){
        return cardNumbers.isEmpty();
    }
    
    public void printDrawn(){
        for(Iterator<Integer> it = drawn.iterator(); it.hasNext();) {
            Integer element = it.next();
            System.out.print(element + " ");
        }
    }
}
