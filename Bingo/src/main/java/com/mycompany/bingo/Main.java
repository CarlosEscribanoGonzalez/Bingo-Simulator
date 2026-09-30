package com.mycompany.bingo;

public class Main {
    public static void main(String[] args) {
        GameSession session = new GameSession();
        session.requestPlayers();
        session.requestNames();
        session.requestNumBalls();
        session.requestPoints();
        session.requestNumGames();
        session.playGames();
    }
    
}
