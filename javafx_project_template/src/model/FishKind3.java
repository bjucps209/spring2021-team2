package model;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class FishKind3 extends Fishes{
    
    final Image dir = new Image("/FishPicture/FirstStageUsage/noback3.png");

    int imageSize = 317;

    FishKind3(){
        this.speed = 8;
        this.size = 3;
    }
    
    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }
}
