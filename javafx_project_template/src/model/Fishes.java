package model;

import java.util.Random;

import javax.naming.directory.DirContext;

public class Fishes extends AllObject {

    //initialdirction of fish final?
    private final int initialDirection;

    // random the inital direction.
    Fishes() {
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            initialDirection = 0 + rand.nextInt(10);
        } else {
            // remember import from right edge of screen
            initialDirection = 180 + rand.nextInt(10);
        }
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
    @Override
    public void ChangeSpeedAndDirection() {
    }

    public void randDirection() {
        var rand = new Random();
        var ran = rand.nextInt(3);
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
        var ran2 = rand.nextInt(10);
        // up bit and down bit need to write here.

    }

    // change randon speed for fish, 
    // remember up bound and low bound limiation onfish.
    public void randSpeed() {

    }


    

}
