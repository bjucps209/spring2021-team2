package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Mine extends AllObject{
    
    final Image dir = new Image("/FishPicture/FirstStageUsage/mine0.jpg");

    int imageSize = 68;

    int direction;


    Mine(){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    
    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
