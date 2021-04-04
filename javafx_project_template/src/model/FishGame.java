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
    int life;
    int points;
    int health;

    public static int level;

    // it use to make sure there is no too many food on the screen
    int numberOfFood;

    // it use to make sure there is no too much each kind of fish in the screen
    int numberOfType1Fish;
    int numberOfType2Fish;
    int numberOfType3Fish;
    int numberOfPoisonFish;

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
        objectStorage.add(new FishType1());

    }

    // constructor while game start, model will recieve different value from
    // different level. however other limitation such as speed, will not in
    // constructor because each kind of fish have its own speed and their initial
    // speed in their own object;
    public FishGame(int life, int points, int health, int numberOfFood, int numberOfType1Fish, int numberOfType2Fish,
            int numberOfType3Fish) {
        this.life = life;
        this.points = points;
        this.health = health;
        isCheatModeOn = false;
        isGameOver = false;
        this.numberOfFood = numberOfFood;
        this.numberOfType1Fish = numberOfType1Fish;
        this.numberOfType2Fish = numberOfType2Fish;
        this.numberOfType3Fish = numberOfType3Fish;
        objectStorage = new ArrayList<AllObject>();
        objectStorage.add(new FishType1());
    }

    // from x and y we know image position, from image size we know how much it
    // covers
    // x and y + size x size we can get the area of fish that other fish can't
    // touches.
    // remind and undeterming, size of the fish will be grow, we need careful about
    // this.
    public void Fishmeet() {

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
