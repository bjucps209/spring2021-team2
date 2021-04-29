package model;

import java.util.Random;

import javax.naming.directory.DirContext;

import javafx.beans.property.SimpleIntegerProperty;

public class Fishes extends AllObject {

    // initial dirction of fish
    // it use to descide the initial facing direction of the Fish.
    // Because fish have random direction, it also serves for fish favor direction
    private int initialDirection;

    // constructot for fish class
    // give fish type, size, inital speed, and imagesize
    // it rand the initial postion, inital directon for fish
    public Fishes(Type objType, int speed, int size, int imageSize) {
        this.speed = speed;
        this.size = size;
        this.objtype = objType;
        this.imageSize = imageSize;

        // random the inital direction.
        // inital x, intial y, intial initalDirection, intial direction
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // import from right edge of screen
            initialDirection = 180 + rand.nextInt(5);
            int yuse = rand.nextInt(750);
            x = new SimpleIntegerProperty(1366);
            y = new SimpleIntegerProperty(yuse);
            direction = initialDirection;
        } else {
            // import from left edge of screen
            initialDirection = 0 + rand.nextInt(10);
            int yuse = rand.nextInt(700);
            y = new SimpleIntegerProperty(yuse);
            x = new SimpleIntegerProperty(0 - imageSize);
            direction = initialDirection;
        }
        id = FishGame.id;
        FishGame.id += 1;

    }

    // fish need change both speeed and directon
    @Override
    public void ChangeSpeedAndDirection() {
        randDirection();
        randSpeed();
    }

    // direction change, it can use up rand for 0 - 10;
    // is change change fish facing side;
    public void randDirection() {
        var rand = new Random();
        var ran = rand.nextInt(3);
        var ran2 = rand.nextInt(10);
        var ran3 = rand.nextInt(2);

        // change facing direction for fish
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
            direction = initialDirection + rand.nextInt(10);
            break;
        }

        // make fish move up or down little bit
        if (direction < 180) {
            if (180 - direction < 5 || (180 - direction < 155 && 180 - direction > 135)) {
                direction -= ran2;
            } else if ((180 - direction > 4 && 180 - direction < 26)
                    || (180 - direction < 176 && 180 - direction > 134)) {
                switch (ran3) {
                case 0:
                    direction += ran2;
                case 1:
                    direction -= ran2;
                }
            } else if ((180 - direction > 25 && 180 - direction < 45) || 180 - direction > 175) {
                direction += ran2;
            }
        } else {
            if (360 - direction < 5 || (360 - direction < 155 && 360 - direction > 135)) {
                direction -= ran2;
            } else if ((360 - direction > 4 && 360 - direction < 26)
                    || (360 - direction < 176 && 360 - direction > 134)) {
                switch (ran3) {
                case 0:
                    direction += ran2;
                case 1:
                    direction -= ran2;
                }
            } else if ((360 - direction > 25 && 360 - direction < 45) || 360 - direction > 175) {
                direction += ran2;
            }
        }

    }

    /**
     * function called when fish eat happens
     * 
     * @param AllObject
     * @return AllObject that will be remove for the list.
     */
    public AllObject[] eat(AllObject a) {
        AllObject[] stor = new AllObject[] {};
        if (a.getType() == Type.PoisonFish || a.getType() == Type.Mine) {
            stor = new AllObject[2];
            stor[0] = this;
            stor[1] = a;
        } else if ((a instanceof Fishes) && a.getType() != Type.PoisonFish) {
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
        if (ran == 0) {
            if (speed < 3) {
                this.speed += ran2;
            } else if (speed > 10) {
                this.speed -= ran2;
            } else {
                this.speed -= ran2;
            }
        } else {
            if (speed < 3) {
                this.speed += ran2;
            } else if (speed > 11) {
                this.speed -= ran2;
            } else {
                this.speed += ran2;
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

    // getters and setters
    public int getInitialDirection() {
        return this.initialDirection;
    }

    public void setInitialDirection(int initialDirection) {
        this.initialDirection = initialDirection;
    }

}
