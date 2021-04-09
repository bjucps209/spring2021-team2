package model;

import java.util.Random;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Food extends AllObject{

    Type type = Type.Food;


    Food(){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
        this.imageSize = 20;
        var rand = new Random();
        x = rand.nextInt(1000);
        y = this.imageSize;
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

}
