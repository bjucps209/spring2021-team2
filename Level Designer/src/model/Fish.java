package model;

import java.util.Random;

import javax.naming.directory.DirContext;
import javax.sound.sampled.CompoundControl.Type;

import javafx.beans.property.SimpleIntegerProperty;


public class Fish extends AllObject {

    //initialdirction of fish final?
    private int initialDirection;
    private boolean flipped;
    

    public int getInitialDirection() {
        return this.initialDirection;
    }

    public void setInitialDirection(int initialDirection) {
        this.initialDirection = initialDirection;
    }



    // each fish is on their owe movement.
    // they will change speed and direction every 3 seconds. It should be called
    // every 3s in timeline at viewclass
    // And for speed, there is the limitation,
    // it can't increasing too much or stop
    // for direction, we import fish for left edge and right edge of screen.
    // we purpose want to make fish go cross the screen,
    // Therefore the random for direction will be 66% go for initial direction,
    // 33% go for opposite direction.
    // Also Our fish will not go up and down, we are try to much them slightly go up
    // and down while they are move cross the creen

    // remember limitation of dirction number;

    public Fish(String type,boolean flipped,int x,int y){
       
        this.type=type;
        this.flipped=flipped;
        this.x=x;
        this.y=y;
    }

    public boolean getflipped() {
        return flipped;
    }
        
}
