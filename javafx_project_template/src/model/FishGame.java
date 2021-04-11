package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

    public Userfish getUser() {
        return this.user;
    }

    public void setUser(Userfish user) {
        this.user = user;
    }

    static int life;
    static int points = 0; 
    static int health;

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
        user = new Userfish(Type.FishType1, 5, 1, 30);
    }

    // constructor while game start, model will recieve different value from
    // different level. however other limitation such as speed, will not in
    // constructor because each kind of fish have its own speed and their initial
    // speed in their own object;
    public FishGame(int life, int health, int limitOfFood, int limitOfType1Fish, int limitOfType2Fish,
            int limitOfType3Fish, int limitOfPoisonFish) {
        FishGame.life = life;
        FishGame.health = health;
        isCheatModeOn = false;
        isGameOver = false;
        this.limitOfFood = limitOfFood;
        this.limitOfType1Fish = limitOfType1Fish;
        this.limitOfType2Fish = limitOfType2Fish;
        this.limitOfType3Fish = limitOfType3Fish;
        this.limitOfPoisonFish = limitOfPoisonFish;
        objectStorage = new ArrayList<AllObject>();
        for (int i = 0; i < limitOfFood; i++){
            objectStorage.add(new Food());
            numberOfFood += 1;
        }
        for (int i = 0; i < limitOfType1Fish; i++){
            objectStorage.add(new Fishes(Type.FishType1, 7, 1, 50));
            numberOfType1Fish += 1;
        }
        for (int i = 0; i < limitOfType2Fish; i++){
            objectStorage.add(new Fishes(Type.FishType2, 6, 2, 100));
            numberOfType2Fish += 1;
        }
        for (int i = 0; i < limitOfType3Fish; i++){
            objectStorage.add(new Fishes(Type.FishType3, 10, 3, 150));
            numberOfType3Fish += 1;
        }
        for (int i = 0; i < limitOfPoisonFish; i++){
            objectStorage.add(new Fishes(Type.PoisonFish, 7, 1, 50));
            numberOfPoisonFish += 1;
        }
        user = new Userfish(Type.FishType1, 5, 1, 30);

    }

    // from x and y we know image position, from image size we know how much it
    // covers
    // x and y + size x size we can get the area of fish that other fish can't
    // touches.
    // remind and undeterming, size of the fish will be grow, we need careful about
    // this.
    public void Fishmeet() {
        for (int i = 0; i < objectStorage.size(); i++ ){
            for (int o = 0; o < objectStorage.size(); o++ ){
                if ( i != o){
                    if (FishGame.circleChecking(objectStorage.get(i).drawCircle(), objectStorage.get(o).drawCircle()) != -1){
                        if (situationHandle(objectStorage.get(i), objectStorage.get(o)).length != 0);
                            for (AllObject a : situationHandle(objectStorage.get(i), objectStorage.get(o))){
                                objectStorage.remove(a);
                                if ((a.getType() == Type.Food)) {
                                    numberOfFood -= 1;
                                }else if ((a.getType() == Type.FishType1)){
                                    numberOfType1Fish -=1;
                                }else if ((a.getType() == Type.FishType2)){
                                    numberOfType2Fish -=1;
                                }else if ((a.getType() == Type.FishType3)){
                                    numberOfType3Fish -=1;
                                }else if (a.getType() == Type.PoisonFish){
                                    numberOfPoisonFish -= 1;
                                }
                            }
                        }
                    }
                }
            } 
    }

    public void userfishcollision(){
        for (AllObject a : objectStorage){
            if (FishGame.circleChecking(user.drawCircle(), a.drawCircle()) != -1){
                if (user.getSize() == a.getSize()){
                    health -= 1;
                }else if(user.getSize() < a.getSize()){
                    health -= (a.getSize() - user.getSize());
                }else if(user.getSize() > a.getSize()){
                    user.eat(a);
                }
            if (FishGame.health < 1){
                FishGame.life -= 1;
                if (FishGame.life < 1){
                    if (isCheatModeOn = false){
                        isGameOver = true;
                }

                }
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
    // return        first object eat second object
    // return        second object eat first object
    // return        first object is obstacles and second object is not fish
    // return        seoncond object is obstacles and first object is not fish
    // else do nothing

    public static AllObject[] situationHandle(AllObject firstobject, AllObject secondObject){
        AllObject[] eaten = new AllObject[0];
        if (firstobject.getType() == Type.Shark){
            if (secondObject.getType() != Type.Obstacles){
                eaten = ((Shark) firstobject).Sharkeat(secondObject);
            }
        }
        else if(secondObject.getType() == Type.Shark){
            if (firstobject.getType() != Type.Obstacles){
                eaten = ((Shark) secondObject).Sharkeat(firstobject);
            }
        }
        else if (
            (firstobject.getSize() > secondObject.getSize()||
            (firstobject instanceof Fishes && secondObject.getType() == Type.Food))){
                eaten = ((Fishes) firstobject).eat(secondObject);
        }else if(
            (secondObject.getSize() > firstobject.getSize()||
            (secondObject instanceof Fishes && firstobject.getType() == Type.Food))){
                eaten = ((Fishes) secondObject).eat(firstobject);
        }else if(
            (firstobject.getType() == Type.Obstacles) && (secondObject instanceof Fishes == false) && (secondObject.getType() != Type.Shark)
        ){
            secondObject.setSpeed(0);
        }else if(
            (secondObject.getType() == Type.Obstacles) && (firstobject instanceof Fishes)
        ){
            firstobject.ChangeSpeedAndDirection();
        }
        return eaten;
    }

    public void checknumberofFish(){
        for (int i = 0; i < limitOfFood - numberOfFood; i++){
            objectStorage.add(new Food());
        }for (int i = 0; i < limitOfType1Fish- numberOfType1Fish; i++){
            objectStorage.add(new Fishes(Type.FishType1, 7, 1, 50));
        }for (int i = 0; i < limitOfType2Fish- numberOfType2Fish; i++){
            objectStorage.add(new Fishes(Type.FishType2, 6, 2, 100));
        }for (int i = 0; i < limitOfType2Fish- numberOfType3Fish; i++){
            objectStorage.add(new Fishes(Type.FishType2, 10, 3, 150));
        }for (int i = 0; i < limitOfPoisonFish- numberOfPoisonFish; i++){
            objectStorage.add(new Fishes(Type.PoisonFish, 7, 1, 50));
        }
    }

    public void increaseSize(){
        if (FishGame.points == 10){
            user.setSize(user.getSize()+1);
            user.setImageSize(user.getImageSize()+30);
        }else if(FishGame.points == 40){
            user.setSize(user.getSize()+1);
            user.setImageSize(user.getImageSize()+30);
        }else if(FishGame.points == 80){
            user.setSize(user.getSize()+1);
            user.setImageSize(user.getImageSize()+30);
        }else if(FishGame.points == 110){
            user.setSize(user.getSize()+1);
            user.setImageSize(user.getImageSize()+30);
        }
    }


    // block the area that fish can't go cross, will not use in early level,
    // lt can work on later of the project.
    public void blockingArea() {
    }

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
        objectStorage.add(new Shark());
    }

    public void updata(){
        for (AllObject a: objectStorage){
            a.updatePosition();
        }
    }

    //load and save, I don't know yet
    public void load(){
        // probably a for loop to cycle through each object's serialize method which will then be passed to the StreamReader

    }

    public void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("save.game", true));
            } catch (Exception e) {
            //TODO: this breaks model/view separation
            System.out.println("Save just died");
        }
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
