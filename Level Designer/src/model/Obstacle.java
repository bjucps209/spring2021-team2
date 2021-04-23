package model;

import java.util.Random;



public class Obstacle extends AllObject {
    private int imageSize = 317;
    private String type;

    public Obstacle(String type) {
        this.speed = 0;
        this.size = 3;
        this.type=type;

        // random the inital direction.
        var rand = new Random();
        int ran = rand.nextInt(2);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }


}

    
