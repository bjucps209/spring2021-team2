package model;

import java.util.Random;

import javax.naming.directory.DirContext;

import javafx.beans.property.SimpleIntegerProperty;


public class Fishes extends AllObject {

    //initialdirction of fish final?
    private int initialDirection;

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

    public Fishes(Type objType, int speed, int size, int imageSize){
        this.speed = speed;
        this.size = size;
        this.objtype = objType;
        this.imageSize = imageSize;
        id = FishGame.id;
        FishGame.id += 1;

        // random the inital direction.
        //inital x, intial y, intial initalDirection, intial direction
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            initialDirection = 180 + rand.nextInt(5);
            x = new SimpleIntegerProperty(1-imageSize);
            int yuse = rand.nextInt(1000);
            y = new SimpleIntegerProperty(yuse);
            direction = initialDirection;
        } else {
            // remember import from right edge of screen
            initialDirection = 0 + rand.nextInt(5);
            y = new SimpleIntegerProperty();
            int yuse = rand.nextInt(1000);
            x = new SimpleIntegerProperty(yuse);
            direction = initialDirection;
        }
        id = FishGame.id;
        FishGame.id += 1;

    }

    @Override
    public void ChangeSpeedAndDirection() {
        randDirection();
        randSpeed();
    }

    public void randDirection() {
        var rand = new Random();
        var ran = rand.nextInt(3);
        var ran2 = rand.nextInt(5);
        var ran3 = rand.nextInt(2);
        switch (ran) {
        case 0:
            if (direction < 180) {
                direction += 180;
            } else {
                direction -= 180;
            }
            break;
        case 1:
        case 2:
            break;
        }

        if (direction < 180){
            if (180 - direction < 5 || (180 - direction < 155 && 180 - direction > 135)){
                direction -= ran2;
            }else if ((180 - direction > 4 && 180 - direction < 26)||(180 - direction < 176 && 180 - direction > 134)){
                switch(ran3){
                    case 0:
                        direction += ran2;
                    case 1:
                        direction -= ran2;
                }
            }else if ((180 - direction > 25 && 180 - direction < 45)|| 180 - direction > 175){
                direction += ran2;
            }
        }else{
            if (360 - direction < 5 || (360 - direction < 155 && 360 - direction > 135)){
                direction -= ran2;
            }else if ((360 - direction > 4 && 360 - direction < 26)||(360 - direction < 176 && 360 - direction > 134)){
                switch(ran3){
                    case 0:
                        direction += ran2;
                    case 1:
                        direction -= ran2;
                }
            }else if ((360 - direction > 25 && 360 - direction < 45)|| 360 - direction > 175){
                direction += ran2;
            }
        }
        
    }

    public AllObject[] eat(AllObject a){
        AllObject[] stor = new AllObject[]{};
        if (a.getType() == Type.PoisonFish||a.getType() == Type.Mine){
            stor = new AllObject[2];
            stor[0] = this;
            stor[1] = a;
        }else if((a instanceof Fishes) && a.getType() != Type.PoisonFish){
            stor = new AllObject[1];
            stor[0] = a;
        }
        return stor;
    }

    // change randon speed for fish, 
    // remember up bound and low bound limiation onfish.
    public void randSpeed() {
        var rand = new Random();
        int ran = rand.nextInt(2);
        int ran2 = rand.nextInt(3);
        if(ran == 0){
            if (speed < 3){
                this.speed += ran2;
            }
        }else{
            if (speed > 10){
                this.speed -= ran2;
            }
        }
    }
    // returns a string for the save function
    @Override
    public String serialize() {
        String dataString = "";
        dataString += this.objtype.toString();
        dataString += ":";
        dataString += Integer.toString(x.get()) + "," + Integer.toString(y.get());
        dataString += ":";
        dataString += Integer.toString(this.size);
        dataString += ":";
        dataString += Integer.toString(direction);
        dataString += ":";
        dataString += Integer.toString(this.imageSize);
         
        return dataString;
    }
}
