//--------------------------------------------------------------------
//File:   Mine.java
//Desc:   Mine class containing it movement
//By:     Shubin Yuan
//-------------------------------------------------------------------
package model;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Mine extends AllObject {

    Type type;

    Mine() {
        type = Type.Mine;
        this.speed = 7;
        this.size = 0;
        this.direction = 90;
        this.imageSize = 17;
        var rand = new Random();
        int xUse = rand.nextInt(1000);
        x = new SimpleIntegerProperty(xUse);
        y = new SimpleIntegerProperty(imageSize);
        id = FishGame.id;
        FishGame.id += 1;
    }

    // falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
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

    // TODO: figure out how mine serializatoin works
    @Override
    public String serialize() {
        String dataString = "";
        dataString += this.type.toString();
        dataString += ":";
        dataString += x.get() + "," + y.get();
        dataString += ":";
        dataString += Integer.toString(direction);

        return dataString;
    }
}
