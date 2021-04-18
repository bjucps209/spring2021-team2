package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Food extends AllObject{
    
    
    private int imageSize = 68;
    private String type;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Food(String type){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
        this.type=type;
    }

    //falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
    }

  
   

}
