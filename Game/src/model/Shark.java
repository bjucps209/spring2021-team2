package model;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Shark extends AllObject {

    Type type = Type.Shark;


    Shark() {
        this.speed = 20;
        this.size = 10;
        this.imageSize = 200;
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            direction = 0;
            x = new SimpleIntegerProperty(1-imageSize);
            int yUse = rand.nextInt(1000);
            y = new SimpleIntegerProperty(yUse);
        } else {
            // remember import from right edge of screen
            direction = 180;
            x = new SimpleIntegerProperty(1);
            int yUse = rand.nextInt(1000);
            y = new SimpleIntegerProperty(yUse);
        }
    }

    @Override
    public void ChangeSpeedAndDirection() {;}

    //change object to a list and return
    public AllObject[] Sharkeat(AllObject a){
        AllObject[] b = new AllObject[1];
        b[0] = a;
        return b;
        }


    //TODO: figure out how Shark serializatoin works
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

