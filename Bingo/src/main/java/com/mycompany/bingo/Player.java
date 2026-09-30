package com.mycompany.bingo;

public class Player {
    String name;
    BingoCard card;
    private int gamePoints;
    private int totalPoints;
    
    public Player(){
        gamePoints = 0;
        totalPoints = 0;
    }
    
    public void addPoints(int valor){
        gamePoints += valor;
        totalPoints += valor;
    }
    
    public int getGamePoints(){
        return gamePoints;
    }
    
    public int getTotalPoints(){
        return totalPoints;
    }
    
    public void resetPoints(){
        gamePoints = 0;
    }
}


