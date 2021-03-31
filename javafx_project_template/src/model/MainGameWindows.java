package model;

import java.util.ArrayList;

public class MainGameWindows {
    
    ArrayList<AllObject> objectStorage;
    int life;
    int points;
    int health;

    boolean isCheatModeOn;
    boolean isGameOver;


    public MainGameWindows(){
        isCheatModeOn = false;
        isGameOver = false;
        
    }

    public MainGameWindows(int life, int points, int health){
        this.life = life;
        this.points = points;
        this.health = health;
        isCheatModeOn = false;
        isGameOver = false;
    }
    

    public ArrayList<AllObject> getObjectStorage() {
        return this.objectStorage;
    }

    public void setObjectStorage(ArrayList<AllObject> objectStorage) {
        this.objectStorage = objectStorage;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isIsCheatModeOn() {
        return this.isCheatModeOn;
    }

    public void setIsCheatModeOn(boolean isCheatModeOn) {
        this.isCheatModeOn = isCheatModeOn;
    }

    public boolean isIsGameOver() {
        return this.isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
