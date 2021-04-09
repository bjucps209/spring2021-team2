package model;

import java.util.Random;

import javafx.scene.image.Image;

public class Obstacles extends AllObject {

    Obstacles(int size, int imageSize) {
        this.speed = 0;
        this.size = size;
        this.imageSize = imageSize;
        this.direction = 0;
        var rand = new Random();
        x = rand.nextInt(1000);
        y = 1000 - imageSize;
    }

    @Override
    public void ChangeSpeedAndDirection() {;}
}
