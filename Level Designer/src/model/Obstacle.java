package model;

import java.util.Random;



public class Obstacle extends AllObject {
    private int imageSize = 317;
    private String type;
    public  boolean flipped;

    public Obstacle(String type,boolean flipped,int x,int y) {
        this.speed = 0;
        this.size = 3;
        this.type=type;
        this.flipped=flipped;
        this.x=x;
        this.y=y;

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

    
