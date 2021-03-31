package model;

import java.util.Random;

public class Fishes extends AllObject {

    int direction;
    int initialDirection;

    //random the inital direction. 
    Fishes() {
        var rand = new Random();
        int trya = rand.nextInt(2);
        if (trya == 0) {
            initialDirection = 0 + rand.nextInt(20);
        } else {
            initialDirection = 180 + rand.nextInt(20);
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
