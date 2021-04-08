package model;

import java.util.ArrayList;
import java.util.Random;

public class FishGame {

    // the storage of all things in the sceen.
    // first element of list will be user's fish;
    // while using for loops don't cover first element.
    ArrayList<AllObject> objectStorage;

    // three basic value for the game
    // from class we learn this week those variable may change to intproperty in
    // order to bind

    //userfish
    Userfish user;

    int life;
    int points;
    int health;

    //Level static global variable
    public static int level;


    // How many food on the screen for now 
    int numberOfFood;

    // iHow many each kind of fish on the screen for now
    int numberOfType1Fish;
    int numberOfType2Fish;
    int numberOfType3Fish;
    int numberOfPoisonFish;

    // make sure there is not too many food on the screen
    int limitOfFood;

    // it use to make sure there is no too much each kind of fish in the screen
    int limitOfType1Fish;
    int limitOfType2Fish;
    int limitOfType3Fish;
    int limitOfPoisonFish;


    // to bolean for state for game,
    // if isCheatModeOn = true, user fish will not die
    // if isGameGver = true either use win or lose,
    boolean isCheatModeOn;
    boolean isGameOver;

    // basic constructor to start game if there is not input yet, purpose for
    // testing the program.
    public FishGame() {
        isCheatModeOn = false;
        isGameOver = false;
        objectStorage = new ArrayList<AllObject>();
    }

    // constructor while game start, model will recieve different value from
    // different level. however other limitation such as speed, will not in
    // constructor because each kind of fish have its own speed and their initial
    // speed in their own object;
    public FishGame(int life, int points, int health, int limitOfFood, int limitOfType1Fish, int limitOfType2Fish,
            int limitOfType3Fish, int limitOfPoisonFish) {
        this.life = life;
        this.points = points;
        this.health = health;
        isCheatModeOn = false;
        isGameOver = false;
        this.limitOfFood = limitOfFood;
        this.limitOfType1Fish = limitOfType1Fish;
        this.limitOfType2Fish = limitOfType2Fish;
        this.limitOfType3Fish = limitOfType3Fish;
        this.limitOfPoisonFish = limitOfPoisonFish;
        objectStorage = new ArrayList<AllObject>();
    }

    // from x and y we know image position, from image size we know how much it
    // covers
    // x and y + size x size we can get the area of fish that other fish can't
    // touches.
    // remind and undeterming, size of the fish will be grow, we need careful about
    // this.
    public void Fishmeet() {
        for (int i = 0; i < objectStorage.size(); i++ ){
            for (int o = 0; o < objectStorage.size(); i++ ){
                if ( i != o){
                    if (FishGame.circleChecking(objectStorage.get(i).drawCircle(), objectStorage.get(o).drawCircle()) != -1){
                        if (objectStorage.get(i).getType() == Type.Obstacles && objectStorage.get(o).getType() == Type.Obstacles){

                        }
                        if(objectStorage.get(i).getSize() > objectStorage.get(o).getSize()){
                            objectStorage.remove(objectStorage.get(i).eat((Fishes)objectStorage.get(o)));
                        }
                    };
                }
            }
        }
    }

    public static int circleChecking(int[] circle1, int[] circle2){
        int dispostion = (circle1[0] - circle2[0])*(circle1[0] - circle2[0]) + (circle1[1] -circle2[1]) *  (circle1[1] -circle2[1]);
        int radiussum = (circle1[2] + circle2[2]) * (circle1[2] + circle2[2]);
        if (dispostion == radiussum){
            return 1;
        }else if(dispostion > radiussum){
            return -1;
        }else{
            return 0;
        }
    }
    // handle all kinds of situation while different things met
    // return -1       while firstobject delete, secondOject stay same
    // return 0        while other object meet wieh obstacles.
    // return 1        while secondOject delete, firstobject stay same
    // return 2        while fish meet wieh obstacles.

    public static int situationHandle(AllObject firstobject, AllObject secondObject){
        if (
            (firstobject.getType() == Type.Shark)||
            (firstobject.getSize() > secondObject.getSize()||
            (firstobject instanceof Fishes && secondObject.getType() == Type.Food))){
        }else if(
            (secondObject.getType() == Type.Shark)||
            (secondObject.getSize() > firstobject.getSize()||
            (secondObject instanceof Fishes && firstobject.getType() == Type.Food))){
        }else if()
    }


    // block the area that fish can't go cross, will not use in early level,
    // lt can work on later of the project.
    public void blockingArea() {

    }

    // add a new object to arraylist
    public void add(AllObject a){
        objectStorage.add(a);
    }

    public void remove(AllObject a){
        objectStorage.remove(a);
    }

    //turns cheat mode on
    public void enableCheatMode(){
        if(isCheatModeOn == false) {
            isCheatModeOn = true;
        }
    }
    //turns cheat mode off
    public void disableCheatMode(){
        if(isCheatModeOn == true) {
            isCheatModeOn = false;
        }
    }

    //add shark into fishGame list 
    public void addShark(){

    }
    //the motion of the shark delate everything in the FishGame list
    public void sharkPass(){
        
    }

    public void updata(){

    }

    //load and save, I don't know yet
    public void load(){

    }

    public void save(){

    }

    // all getters and setters
    public int getNumberOfFood() {
        return this.numberOfFood;
    }

    public void setNumberOfFood(int numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public int getNumberOfType1Fish() {
        return this.numberOfType1Fish;
    }

    public void setNumberOfType1Fish(int numberOfType1Fish) {
        this.numberOfType1Fish = numberOfType1Fish;
    }

    public int getNumberOfType2Fish() {
        return this.numberOfType2Fish;
    }

    public void setNumberOfType2Fish(int numberOfType2Fish) {
        this.numberOfType2Fish = numberOfType2Fish;
    }

    public int getNumberOfType3Fish() {
        return this.numberOfType3Fish;
    }

    public void setNumberOfType3Fish(int numberOfType3Fish) {
        this.numberOfType3Fish = numberOfType3Fish;
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

    public boolean getIsCheatModeOn() {
        return this.isCheatModeOn;
    }

    public void setIsCheatModeOn(boolean isCheatModeOn) {
        this.isCheatModeOn = isCheatModeOn;
    }

    public boolean getIsGameOver() {
        return this.isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
