package model;

import java.util.Random;

import javax.naming.directory.DirContext;


public class Fishes extends AllObject {

    //initialdirction of fish final?
    private int initialDirection;

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

    Fishes(Type objType, int speed, int size, int imageSize){
        this.speed = speed;
        this.size = size;
        this.objtype = objType;
        this.imageSize = imageSize;

        // random the inital direction.
        //inital x, intial y, intial initalDirection, intial direction
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            initialDirection = 0 + rand.nextInt(10);
            x = 1-imageSize;
            y = rand.nextInt(1000);
            direction = initialDirection;
        } else {
            // remember import from right edge of screen
            initialDirection = 180 + rand.nextInt(10);
            x = 1;
            y = rand.nextInt(1000);
            direction = initialDirection;
        }
    }

    @Override
    public void ChangeSpeedAndDirection() {
        randDirection();
        randSpeed();
    }

    public void randDirection() {
        var rand = new Random();
        var ran = rand.nextInt(3);
        var ran2 = rand.nextInt(10);
        var ran3 = rand.nextInt(2);
        switch (ran) {
        case 0:
            if (direction <= 180) {
                direction += 180;
                if(360 - direction < 10){
                    direction -= ran2;
                }else if (360 - direction < 30){
                    if(ran3 == 0){
                        direction -= ran2;
                    }else{
                        direction += ran2;
                    }
                }else{
                    direction += ran2;
                }
            } else {
                direction -= 180;
                if (direction-180 < 10){
                    direction += ran2;
                }else if (direction-180 < 30){
                    if(ran3 == 0){
                        direction -= ran2;
                    }else{
                        direction += ran2;
                    }
                }else{
                    direction -= ran2;
                }
            }
            break;
        case 1:
        case 2:
        if (direction <= 180) {
            if(360 - direction < 10){
                direction -= ran2;
            }else if (360 - direction < 30){
                if(ran3 == 0){
                    direction -= ran2;
                }else{
                    direction += ran2;
                }
            }else{
                direction += ran2;
            }
        } else {
            if (direction-180 < 10){
                direction += ran2;
            }else if (direction-180 < 30){
                if(ran3 == 0){
                    direction -= ran2;
                }else{
                    direction += ran2;
                }
            }else{
                direction -= ran2;
            }
        }
            break;
        }
    }

    public AllObject[] eat(AllObject a){
        AllObject[] stor;
        if (a.getType() == Type.PoisonFish||a.getType() == Type.Mine){
            stor = new AllObject[2];
            stor[0] = this;
            stor[1] = a;
        }else if((a instanceof Fishes) && a.getType() != Type.PoisonFish){
            stor = new AllObject[1];
            stor[0] = a;
        }else{
            stor = new AllObject[1];
            stor[0] = null;
        }
        return stor;
    }

    // change randon speed for fish, 
    // remember up bound and low bound limiation onfish.
    public void randSpeed() {
        var rand = new Random();
        int ran = rand.nextInt(2);
        int ran2 = rand.nextInt(10);
        if(ran == 0){
            this.speed -= ran2;
        }else{
            this.speed += ran2;
        }
    }
    // returns a string for the save function
    public String serialize() {
        String dataString = "";
        dataString += this.objtype.toString();
        dataString += ":";
        dataString += Integer.toString(x) + "," + Integer.toString(y);
        dataString += ":";
        dataString += Integer.toString(this.size);
        dataString += ":";
        dataString += Integer.toString(direction);
        dataString += ":";
        dataString += Integer.toString(this.imageSize);
         
        return dataString;
    }
}
