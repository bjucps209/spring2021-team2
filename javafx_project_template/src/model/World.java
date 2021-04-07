package model;

public class World {
    
    //from class we learn this week those variable may change to intproperty in oder to bind
    int x;
    int y;
    int speed;
    int size;
    int direction;

    int imageSize = 115;

    

    //override by chrildren
    public void ChangeSpeedAndDirection() {}

    public void updatePosition() {
        x += speed * Math.cos(direction * Math.PI / 180);
        y += speed * Math.sin(direction * Math.PI / 180);
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    
}
