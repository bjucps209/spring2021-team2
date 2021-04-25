package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Food extends AllObject{
    
    
    private int imageSize = 68;
    private String type;
    private boolean flipped;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Food(String type,boolean flipped,int x,int y){
        this.flipped=flipped;
        this.type=type;
        this.x=x;
        this.y=y;
    }

    //falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
    }

  
   

}
