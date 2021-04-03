package model;

import java.util.Random;

import javafx.scene.image.Image;

public class Obstacles extends AllObject {

    private final Image dir = new Image("/PoisonFish.png");// not picture yet

    private int imageSize = 317;

    Obstacles() {
        this.speed = 0;
        this.size = 3;

        // random the inital direction.
        var rand = new Random();
        int ran = rand.nextInt(2);

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

    
