package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Mine extends AllObject{
    
    Mine(){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
    }
    
    //falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
    }


}
