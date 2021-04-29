package model;

import javafx.beans.property.IntegerProperty;

public class AllObject {

    // from class we learn this week those variable may change to intproperty in
    // oder to bind
    IntegerProperty x;
    IntegerProperty y;
    int speed;
    int size;
    int direction;

    int id;

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int imageSize;

    Type objtype;

    AllObject() {
        ;
    }

    // override by chrildren
    public void ChangeSpeedAndDirection() {
    }

    public String serialize() throws Exception {
        throw new Exception("Method cannot serialize");
    }

    public Type getType() {
        return this.objtype;
    }

    public void setType(Type objtype) {
        this.objtype = objtype;
    }

    public void updatePosition() {
        int xpostion = (int) (x.get() + (speed * Math.cos(direction * Math.PI / 180)));
        int ypostion = (int) (y.get() + (speed * Math.sin(direction * Math.PI / 180)));
        x.set(xpostion);
        y.set(ypostion);

    }

    public int[] drawCircle() {
        int[] circle = new int[3];
        if (size == 1 || size == 0) {
            circle[0] = ((x.get() + imageSize) / 2);
            circle[1] = ((y.get() + imageSize) / 2);
            circle[2] = imageSize / 2;
        } else if (size == 2) {
            circle[0] = ((x.get() + imageSize) / 2);
            circle[1] = ((y.get() + imageSize) / 2);
            circle[2] = (imageSize - 3) / 2;
        } else if (size == 3) {
            circle[0] = ((x.get() + imageSize) / 2);
            circle[1] = ((y.get() + imageSize) / 2);
            circle[2] = (imageSize) / 2;
        } else if (size == 4) {
            circle[0] = ((x.get() + imageSize) / 2);
            circle[1] = ((y.get() + imageSize) / 2);
            circle[2] = (imageSize) / 2;
        } else if (size == 5) {
            circle[0] = ((x.get() + imageSize) / 2);
            circle[1] = ((y.get() + imageSize) / 2);
            circle[2] = (imageSize) / 2;
        }
        return circle;

    }

    public int getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public IntegerProperty getX() {
        return this.x;
    }

    public IntegerProperty getY() {
        return this.y;
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
