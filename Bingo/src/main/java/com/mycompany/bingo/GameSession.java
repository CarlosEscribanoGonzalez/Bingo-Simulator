package com.mycompany.bingo;
import java.util.Scanner;

public class GameSession {
    Scanner input;
    Player[] players;
    int numPlayers;
    int maxBalls;
    int POINTS_LINE;
    int POINTS_BINGO;
    int NUM_GAMES;
    
    public GameSession(){
        input = new Scanner(System.in);
    }
    
    public void requestPlayers(){
        do{
            System.out.println("How many players, between 2 and 4, are going to play?");
            numPlayers = input.nextInt();
        } while(numPlayers < 2 || numPlayers > 4);
        players = new Player[numPlayers];
    }
    
    public void requestNames(){
        boolean firstPlayer = false;
        for(int i = 0; i < numPlayers; i++){
            System.out.println("Please, insert player's " + (i+1) + " name");
            players[i] = new Player();
            players[i].name = input.nextLine();
            if(!firstPlayer){
                firstPlayer = true;
                players[i].name = input.nextLine();
            }
        }
    }
    
    public void requestNumBalls(){
        System.out.println("What is the maximum number of balls you wish to play with? It must be a multiple of 18.");
        do{
            maxBalls = input.nextInt();
            if(maxBalls < 18){
                System.out.println("Please, introduce other number higher than 18.");
            }
        }while (maxBalls < 18);         
        
        if(maxBalls % 18 != 0){
            maxBalls = (int) maxBalls / 18;
            maxBalls = (int) maxBalls * 18;
            System.out.println("Session will be played with " + maxBalls + " balls");
        }
    }
    
    public void requestPoints(){
        System.out.println("How many points will be awarded per line called?");
        POINTS_LINE = input.nextInt();
        System.out.println("And per bingo called?");
        POINTS_BINGO = input.nextInt();
    }
    
    public void requestNumGames(){
        System.out.println("How many games will be played?");
        NUM_GAMES = input.nextInt();
    }
    
    public void playGames(){
        for(int i = 0; i < NUM_GAMES; i++){
            Game game = new Game(maxBalls, players, POINTS_LINE, POINTS_BINGO);
            game.playGame();
            game.displayGameWinner();
            System.out.println("-----------------------");
            if(i == NUM_GAMES - 1){
                game.displaySessionWinner(NUM_GAMES);
            }
        }
    }
}