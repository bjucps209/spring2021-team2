package model;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class AllObject {

    // integerproperty direction for x and y
    IntegerProperty x;
    IntegerProperty y;

    // three important value for all object
    int speed;
    int size;
    int direction;

    // id for each object
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

    public Bounds getRectangle() {
        return new BoundingBox(x.get(), y.get(), imageSize, imageSize);

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
