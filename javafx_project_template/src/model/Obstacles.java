package model;

import java.util.Random;

import javafx.scene.image.Image;

public class Obstacles extends AllObject {


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

    public void remove(Obstacles obstacles) {
    }
}

    
