package model;

import java.util.Random;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Shark extends AllObject {


    Shark() {
        this.speed = 20;
        this.size = 10;
        this.imageSize = 200;
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            direction = 0;
            x = 1-imageSize;
            y = rand.nextInt(1000);
        } else {
            // remember import from right edge of screen
            direction = 180;
            x = 1;
            y = rand.nextInt(1000);
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
}

