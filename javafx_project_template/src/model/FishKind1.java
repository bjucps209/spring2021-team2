package model;

import java.util.Random;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class FishKind1 extends Fishes{
    
    //private final Image dir = new Image("/FishPicture/FirstStageUsage/noback.png");

    private int imageSize = 115;

    FishKind1(){
        this.speed = 10;
        this.size = 1;

        // random the inital direction.
        var rand = new Random();
        int ran = rand.nextInt(2);
        if (ran == 0) {
            // remember import from left edge of screen
            initialDirection = 0 + rand.nextInt(10);
            x = 1-imageSize;
            y = rand.nextInt(1000);
        } else {
            // remember import from right edge of screen
            initialDirection = 180 + rand.nextInt(10);
            x = 1;
            y = rand.nextInt(1000);
        }
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

/*    public Image getDir() {
		return this.dir;
    }*/
}
