package model;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Mine extends AllObject{

    Type type = Type.Mine;
    
    Mine(){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
        this.imageSize = 31;
        var rand = new Random();
        int xUse = rand.nextInt(1000);
        x = new SimpleIntegerProperty(xUse);
        y = new SimpleIntegerProperty(imageSize);
    }
    
    //falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
        var rand = new Random();
        int ran = rand.nextInt(2);
        int ran2 = rand.nextInt(3);
        if(ran == 0){
            this.speed -= ran2;
        }else{
            this.speed += ran2;
        }
    }

    //TODO: figure out how mine serializatoin works
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
