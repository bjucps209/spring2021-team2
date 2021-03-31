package model;

import java.util.ArrayList;

public class MainGameWindows {
    
    ArrayList<AllObject> objectStorage;
    int life;
    int points;
    int health;

    public MainGameWindows(){
        
    }

    public MainGameWindows(int life, int points, int health){
        this.life = life;
        this.points = points;
        this.health = health;
    }

}
