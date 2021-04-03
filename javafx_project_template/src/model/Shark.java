package model;

import java.util.Random;

import javafx.scene.image.Image;

//For this class it might not useful for current usage, but will be usefull for later level
public class Shark extends AllObject {

    private final Image dir = new Image("/PoisonFish.png");// not picture yet

    private int imageSize = 317;

    Shark() {
        this.speed = 20;
        this.size = 10;
    }

    @Override
    //sharks have it own movement
    public void updatePosition() {}


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
