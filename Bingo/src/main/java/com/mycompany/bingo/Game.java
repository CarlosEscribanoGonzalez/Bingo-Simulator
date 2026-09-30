package com.mycompany.bingo;
import java.util.Iterator;
import structures.*;

public class Game {
    BingoDrum drum;
    Player[] players;
    final int POINTS_LINE;
    final int POINTS_BINGO;
    boolean line;
    boolean bingo;
    boolean lineCalled;
    
    public Game(int maxBalls, Player[] player, int pointsLine, int pointsBingo){
        drum = new BingoDrum(maxBalls);
        players = player;
        POINTS_LINE = pointsLine;
        POINTS_BINGO = pointsBingo; 
        line = false;
        bingo = false;
        lineCalled = false;
        
        for(int i = 0; i < players.length; i++){ //A card is created for each player
            players[i].card = new BingoCard(maxBalls);
            players[i].card.generateCard();
            System.out.println(players[i].name +" will play with the following card: ");
            players[i].card.printCard();
            System.out.println("");
        }
    }
    
    public void playGame(){
        int move = 1;
        int ball;
        
        while(!bingo){
            System.out.println("Move " +move + ":");
            move++;
            ball = drum.drawBall();
            System.out.println("Ball " + ball + " has been drawn");
            System.out.print("Drawn balls: ");
            drum.printDrawn();
            System.out.println("");
            
            List<String> winners = new SimpleLinkedList<>();
            for(int i = 0; i < players.length; i++){
                if(players[i].card.check(ball, this, players[i])){
                    winners.add(players[i].name);
                }
            }
            
            if(!bingo && !lineCalled){
                if(winners.isEmpty()){
                    System.out.println("Nobody has crossed out the number " +ball);
                } else{
                    System.out.print("Players who held the number: ");
                    for(Iterator<String> it = winners.iterator(); it.hasNext();) {
                        String element = it.next();
                        System.out.print(element +" ");
                    }
                    System.out.println("");
                } 
            }
            System.out.println("");
            lineCalled = false;
        }
    }
    
    public void displayGameWinner(){
        int max = 0;
        String name = null;
        for(int i = 0; i < players.length; i++){
            if(players[i].getGamePoints() > max){
                max = players[i].getGamePoints();
                name = players[i].name;
            }
            players[i].resetPoints();
        }
        System.out.println("This game's winner is: " +name +" , with " +max + " points");
    }
    
    public void displaySessionWinner(int numGames){
        int max = 0;
        String name = null;
        for(int i = 0; i < players.length; i++){
            if(players[i].getTotalPoints() > max){
                max = players[i].getTotalPoints();
                name = players[i].name;
            }
        }
        System.out.println("The " +numGames +"-game session winner is: " +name +" , with " +max + " points");
    }
    
    public void callLine(Player player, Row row){
        if(!line){
            player.addPoints(POINTS_LINE);
            System.out.println("¡Línea de " + player.name + "!");
            System.out.print("Números de la línea (en orden de aparición): ");
            row.printRow();
            System.out.println("");
            line = true;
            lineCalled = true;
        }
    }
    
    public void callBingo(Player player, SimpleLinkedList drawn){
        bingo = true;
        System.out.println("¡Bingo callde by: " +player.name +"!");
        player.addPoints(POINTS_BINGO);
        System.out.print("Bingo numbers (in order of appearance): ");
        player.card.printDrawn();
        System.out.println("");
    }
}
