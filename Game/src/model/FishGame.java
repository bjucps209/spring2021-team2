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

    static int id = 0;

    //Level static global variable
    public static int level;


    // How many food on the screen for now 
    int numberOfFood;

    // iHow many each kind of fish on the screen for now
    int numberOfType1Fish;
    int numberOfType2Fish;
    int numberOfType3Fish;
    int numberOfPoisonFish;

    public int getNumberOfPoisonFish() {
        return this.numberOfPoisonFish;
    }

    public void setNumberOfPoisonFish(int numberOfPoisonFish) {
        this.numberOfPoisonFish = numberOfPoisonFish;
    }

    // make sure there is not too many food on the screen
    int limitOfFood;

    public int getLimitOfFood() {
        return this.limitOfFood;
    }

    public void setLimitOfFood(int limitOfFood) {
        this.limitOfFood = limitOfFood;
    }

    // it use to make sure there is no too much each kind of fish in the screen
    int limitOfType1Fish;
    int limitOfType2Fish;
    int limitOfType3Fish;
    int limitOfPoisonFish;

    public int getLimitOfType1Fish() {
        return this.limitOfType1Fish;
    }

    public void setLimitOfType1Fish(int limitOfType1Fish) {
        this.limitOfType1Fish = limitOfType1Fish;
    }

    public int getLimitOfType2Fish() {
        return this.limitOfType2Fish;
    }

    public void setLimitOfType2Fish(int limitOfType2Fish) {
        this.limitOfType2Fish = limitOfType2Fish;
    }

    public int getLimitOfType3Fish() {
        return this.limitOfType3Fish;
    }

    public void setLimitOfType3Fish(int limitOfType3Fish) {
        this.limitOfType3Fish = limitOfType3Fish;
    }

    public int getLimitOfPoisonFish() {
        return this.limitOfPoisonFish;
    }

    public void setLimitOfPoisonFish(int limitOfPoisonFish) {
        this.limitOfPoisonFish = limitOfPoisonFish;
    }


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
        user = new Userfish(Type.FishType2, 5, 2, 73);
        for (int i = 0; i < limitOfFood; i++){
            objectStorage.add(new Food());
            numberOfFood += 1;
        }
        for (int i = 0; i < limitOfType1Fish; i++){
            objectStorage.add(new Fishes(Type.FishType1, 4, 1, 53));
            numberOfType1Fish += 1;
        }
        for (int i = 0; i < limitOfType2Fish; i++){
            objectStorage.add(new Fishes(Type.FishType2, 4, 2, 73));
            numberOfType2Fish += 1;
        }
        for (int i = 0; i < limitOfType3Fish; i++){
            objectStorage.add(new Fishes(Type.FishType3, 5, 3, 103));
            numberOfType3Fish += 1;
        }
        for (int i = 0; i < limitOfPoisonFish; i++){
            objectStorage.add(new Fishes(Type.PoisonFish, 4, 1, 53));
            numberOfPoisonFish += 1;
        }
    }

    // from x and y we know image position, from image size we know how much it
    // covers
    // x and y + size x size we can get the area of fish that other fish can't
    // touches.
    // remind and undeterming, size of the fish will be grow, we need careful about
    // this.
    public ArrayList<Integer> Fishmeet() {
        ArrayList<Integer> idToRemove = new ArrayList<Integer>();
        ArrayList<AllObject> removeList = new ArrayList<>();
        for (int i = 0; i < objectStorage.size(); i++ ){
            for (int o = 0; o < objectStorage.size(); o++ ){
                if ( i != o){
                    if (FishGame.circleChecking(objectStorage.get(i).drawCircle(), objectStorage.get(o).drawCircle()) != -1){
                        if (situationHandle(objectStorage.get(i), objectStorage.get(o)).length != 0){
                            for (AllObject a : situationHandle(objectStorage.get(i), objectStorage.get(o))){
                                idToRemove.add(a.getId());
                                removeList.add(a);
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
        for (AllObject needRmove :  removeList){
            objectStorage.remove(needRmove);
        }
        return idToRemove;
    }

    public int userfishcollision(){
        int idToDelete = 0;
        AllObject removea = null;

        for (AllObject a : objectStorage){

            if (FishGame.circleChecking(user.drawCircle(), a.drawCircle()) != -1){
                if (a instanceof Fishes||a.getType() == Type.Mine||a.getType() == Type.Food){
                    if (user.getSize() == a.getSize()){
                        health -= 1;
                    }else if(user.getSize() < a.getSize()){
                        health -= (a.getSize() - user.getSize());
                    }else if(user.getSize() > a.getSize()){
                        idToDelete = (user.Usereat(a).getId());
                        removea = a;
                        System.out.print("ssssssssssssssssssssssssssssss\n");
                    }
                }
            }
        }
        if (FishGame.health < 1){
            System.out.print("TTTTTTTTTTTTTTTTTTTTTTTT\n");
            FishGame.life -= 1;
            // fish heal become 5
            FishGame.health = 5;
        }
        if (FishGame.life < 1){
            System.out.print("SDSDSDSDSDSDSDSDS\n");
            if (isCheatModeOn = false){
                isGameOver = true;
            }
        }
        objectStorage.remove(removea);
        System.out.print("FFFFFFFFFFFFFFFFFF\n");
        return idToDelete;
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
        AllObject[] eaten = new AllObject[]{};
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
        // A loop through the save file which will spawn all the objects contained within

    }

    public void save() throws Exception {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("save.game", true));
            writer.append("Level:Difficulty:" + Integer.toString(objectStorage.size()));
            writer.append("\n");
            for (AllObject item : objectStorage) {
                writer.append(item.serialize());
                writer.append("\n");
            }
            writer.close();
            } catch (Exception e) {
                throw new Exception("Save crashed in FishGame.java");
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
