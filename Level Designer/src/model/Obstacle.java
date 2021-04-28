// Object.java
//This file serves as the class for constructing Obstacle objects 


package model;


public class Obstacle extends AllObject {

    private String type;
    public  boolean flipped;

    public Obstacle(String type,boolean flipped,int x,int y) {
       
        this.type=type;
        this.flipped=flipped;
        this.x=x;
        this.y=y;

      

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    


}

    
