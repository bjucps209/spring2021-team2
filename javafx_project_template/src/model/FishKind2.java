package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class FishKind2 extends Fishes{
    
    private final Image dir = new Image("/FishPicture/FirstStageUsage/noback2.png");

    private int imageSize = 183;

    FishKind2(){
        this.speed = 12;
        this.size = 2;
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
