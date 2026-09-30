package com.mycompany.bingo;
import structures.*;

public class BingoDrum {
    private Set<Integer> balls;
    private DrawnBalls drawn;
    
    public BingoDrum(int max){
        balls = new Set<>();
        drawn = new DrawnBalls();
        generateDrum(max);
    }
    
    public void generateDrum (int max){
        for(int i = 1; i <= max; i++){
            balls.addNum(i);
        }
    }
    
    public int drawBall(){
        int ball = balls.randomElement();
        drawn.add(ball);
        return ball;
    }
    
    public void printDrawn(){
        drawn.print();
    }
}
