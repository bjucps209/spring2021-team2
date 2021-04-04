package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Mine extends AllObject{
    
    private final Image dir = new Image("/FishPicture/FirstStageUsage/mine0.jpg");

    private int imageSize = 68;



    Mine(){
        this.speed = 10;
        this.size = 1;
        this.direction = 270;
    }
    
    //falling change moving speed
    @Override
    public void ChangeSpeedAndDirection() {
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public Image getDir() {
		return this.dir;
    }

}
