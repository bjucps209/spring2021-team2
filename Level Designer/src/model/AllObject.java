package model;

public class AllObject {
    
    //from class we learn this week those variable may change to intproperty in oder to bind
    String type;
    int x;
    int y;
    int speed;
    int size;
    int direction;


    //override by chrildren
    public void ChangeSpeedAndDirection() {}

    public void updatePosition() {
        x += speed * Math.cos(direction * Math.PI / 180);
        y += speed * Math.sin(direction * Math.PI / 180);
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
