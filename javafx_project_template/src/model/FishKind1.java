package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class FishKind1 extends Fishes{
    
    private final Image dir = new Image("/FishPicture/FirstStageUsage/noback.png");

    private int imageSize = 115;

    FishKind1(){
        this.speed = 10;
        this.size = 1;
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
