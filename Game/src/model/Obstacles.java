package model;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Obstacles extends AllObject {

    Obstacles(int size, int imageSize) {
        this.speed = 0;
        this.size = size;
        this.imageSize = imageSize;
        this.direction = 0;
        var rand = new Random();
        int xUse = rand.nextInt(1000);
        x = new SimpleIntegerProperty(xUse);
        y = new SimpleIntegerProperty(1000 - imageSize);
    }

    @Override
    public void ChangeSpeedAndDirection() {;}

    //TODO: figure out how Obstacle serializatoin works
    // public String serialize() {
    //     String dataString = "";
    //     dataString += this.type.toString();
    //     dataString += ":";
    //     dataString += Integer.toString(x) + "," + Integer.toString(y);
    //     dataString += ":";
    //     dataString += Integer.toString(direction);
    //     dataString += ":";
         
    //     return dataString;
    // }
    
}
