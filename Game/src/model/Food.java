package model;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Food extends AllObject{



    public Food(){
        objtype = Type.Food;
        this.speed = 3;
        this.size = 0;
        this.direction = 90;
        this.imageSize = 13;
        var rand = new Random();
        //TODO: figure out how the x and y are generated
        int xUse = rand.nextInt(1000);
        x = new SimpleIntegerProperty(xUse);
        y = new SimpleIntegerProperty(0);
        id = FishGame.id;
        FishGame.id += 1;
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

    @Override
    public String serialize() {
        String dataString = "";
        dataString += objtype.toString();
        dataString += ":";
        dataString += Integer.toString(x.get()) + "," + Integer.toString(y.get());
        dataString += ":";
        dataString += Integer.toString(direction);
        dataString += ":";
         
        return dataString;
    }
}
