package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class FishGame {

    // the storage of all things in the sceen.
    // first element of list will be user's fish;
    // while using for loops don't cover first element.
    ArrayList<AllObject> objectStorage;

    // three basic value for the game
    // from class we learn this week those variable may change to intproperty in
    // order to bind

    // userfish
    Userfish user;
    public String name;

    static IntegerProperty life;
    static IntegerProperty points;
    static IntegerProperty health;

    static int id = 0;

    // Level static global variable
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
    GameEventObserver gameEvent;

    public GameEventObserver getGameEvent() {
        return this.gameEvent;
    }

    public void setGameEvent(GameEventObserver gameEvent) {
        this.gameEvent = gameEvent;
    }

    // basic constructor to start game if there is not input yet, purpose for
    // testing the program.
    public FishGame() {
        isCheatModeOn = false;
        objectStorage = new ArrayList<AllObject>();
        user = new Userfish(Type.FishType1, 5, 1, 30);
        FishGame.points = new SimpleIntegerProperty(0);
    }

    // constructor for passing soft coded game files into the model
    public FishGame(File file) {

        System.out.println("File read state: " + file.canRead());
        FishGame.points = new SimpleIntegerProperty(0);
        FishGame.life = new SimpleIntegerProperty(3);
        FishGame.health = new SimpleIntegerProperty(5);
        this.limitOfFood = 3;
        isCheatModeOn = false;
        objectStorage = new ArrayList<AllObject>();
        user = new Userfish(Type.UserFish, 5, 2, 50);
        // trying to read the file we are given
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // Read the first line, this should contain information about the level,
            // difficulty and how many objects we need to import
            String currentLine = reader.readLine();
            System.out.println(currentLine);
            String[] splitString = currentLine.split(":");
            // this.level = Integer.parseInt(splitString[0]);
            this.points.set(Integer.parseInt(splitString[3]));
            this.health.set(Integer.parseInt(splitString[4]));
            this.life.set(Integer.parseInt(splitString[5]));
            this.user.setImageSize(Integer.parseInt(splitString[6]));
            this.name = splitString[7];
            int objectCount = Integer.parseInt(splitString[2]);
            System.out.println("We have " + objectCount + " to walk through");
            for (int i = 0; i != objectCount; ++i) {
                System.out.println("We are now at step " + i);
                currentLine = reader.readLine();
                splitString = currentLine.split(":");
                String[] coords = splitString[1].split(",");
                Fishes fish;
                switch (splitString[0]) {
                case "FishType1":
                    System.out.println("Found one FishType1");
                    fish = new Fishes(Type.FishType1, 4, Integer.parseInt(splitString[2]),
                            Integer.parseInt(splitString[4]));
                    fish.setDirection(Integer.parseInt(splitString[3]));
                    fish.setX(Integer.parseInt(coords[0]));
                    fish.setY(Integer.parseInt(coords[1]));
                    objectStorage.add(fish);
                    break;

                case "FishType2":
                    System.out.println("Found one FishType2");

                    fish = new Fishes(Type.FishType2, 4, Integer.parseInt(splitString[2]),
                            Integer.parseInt(splitString[4]));
                    fish.setDirection(Integer.parseInt(splitString[3]));
                    fish.setX(Integer.parseInt(coords[0]));
                    fish.setY(Integer.parseInt(coords[1]));
                    objectStorage.add(fish);
                    break;

                case "FishType3":
                    System.out.println("Found one FishType3");

                    fish = new Fishes(Type.FishType3, 4, Integer.parseInt(splitString[2]),
                            Integer.parseInt(splitString[4]));
                    fish.setDirection(Integer.parseInt(splitString[3]));
                    fish.setX(Integer.parseInt(coords[0]));
                    fish.setY(Integer.parseInt(coords[1]));
                    objectStorage.add(fish);
                    break;

                case "PoisonFish":
                    System.out.println("Found one PoisonFish");

                    fish = new Fishes(Type.PoisonFish, 4, Integer.parseInt(splitString[2]),
                            Integer.parseInt(splitString[4]));
                    fish.setDirection(Integer.parseInt(splitString[3]));
                    fish.setX(Integer.parseInt(coords[0]));
                    fish.setY(Integer.parseInt(coords[1]));
                    objectStorage.add(fish);
                    break;

                case "Mine":
                    Mine mine = new Mine();
                    mine.setX(Integer.parseInt(coords[0]));
                    mine.setY(Integer.parseInt(coords[1]));
                    mine.setDirection(Integer.parseInt(splitString[2]));
                    objectStorage.add(mine);
                    break;

                case "Food":
                    Food food = new Food();
                    food.setX(Integer.parseInt(coords[0]));
                    food.setY(Integer.parseInt(coords[1]));
                    food.setDirection(Integer.parseInt(splitString[2]));

                default:
                    break;
                }
            }
            reader.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // constructor while game start, model will recieve different value from
    // different level. however other limitation such as speed, will not in
    // constructor because each kind of fish have its own speed and their initial
    // speed in their own object;
    public FishGame(int life, int health, int limitOfFood, int limitOfType1Fish, int limitOfType2Fish,
            int limitOfType3Fish, int limitOfPoisonFish) {
        FishGame.points = new SimpleIntegerProperty(0);
        FishGame.life = new SimpleIntegerProperty(life);
        FishGame.health = new SimpleIntegerProperty(health);
        isCheatModeOn = false;
        this.limitOfFood = limitOfFood;
        this.limitOfType1Fish = limitOfType1Fish;
        this.limitOfType2Fish = limitOfType2Fish;
        this.limitOfType3Fish = limitOfType3Fish;
        this.limitOfPoisonFish = limitOfPoisonFish;
        objectStorage = new ArrayList<AllObject>();
        user = new Userfish(Type.UserFish, 5, 1, 50);
        for (int i = 0; i < limitOfFood; i++) {
            objectStorage.add(new Food());
            numberOfFood += 1;
        }
        for (int i = 0; i < limitOfType1Fish; i++) {
            objectStorage.add(new Fishes(Type.FishType1, 4, 1, 25));
            numberOfType1Fish += 1;
        }
        for (int i = 0; i < limitOfType2Fish; i++) {
            objectStorage.add(new Fishes(Type.FishType2, 4, 2, 40));
            numberOfType2Fish += 1;
        }
        for (int i = 0; i < limitOfType3Fish; i++) {
            objectStorage.add(new Fishes(Type.FishType3, 5, 3, 55));
            numberOfType3Fish += 1;
        }
        for (int i = 0; i < limitOfPoisonFish; i++) {
            objectStorage.add(new Fishes(Type.PoisonFish, 4, 1, 24));
            numberOfPoisonFish += 1;
        }
    }

    public void userfishcollision() {
        boolean loseHealthCheck = false;
        ArrayList<AllObject> removea = new ArrayList<>();

        for (AllObject a : objectStorage) {
            if (user.getRectangle().intersects(a.getRectangle())) {
                if (a instanceof Fishes || a.getType() == Type.Mine || a.getType() == Type.Food) {
                    if (user.getSize() == a.getSize()) {
                        loseHealthCheck = user.sameSize();
                    } else if (user.getSize() < a.getSize()) {
                        loseHealthCheck = user.beeaten(a);
                    } else if (user.getSize() > a.getSize()) {
                        user.Usereat(a);
                        removea.add(a);
                        gameEvent.EatingSound();
                    }
                }
            }
            if (a.getX().get() < -400 || a.getY().get() < -500 || a.getX().get() > 1600 || a.getY().get() > 900) {
                removea.add(a);
            }
        }
        if (removea.size() != 0) {
            for (AllObject b : removea) {
                objectStorage.remove(b);
            }
        }
        if (removea.size() != 0) {
            for (AllObject b : removea) {
                if (b.getType() == Type.Food) {
                    numberOfFood -= 1;
                } else if (b.getType() == Type.FishType1) {
                    numberOfType1Fish -= 1;
                } else if (b.getType() == Type.FishType2) {
                    numberOfType2Fish -= 1;
                } else if (b.getType() == Type.FishType3) {
                    numberOfType3Fish -= 1;
                } else if (b.getType() == Type.PoisonFish) {
                    numberOfPoisonFish -= 1;
                }
            }
        }

        if (!healthAndLifeChecker()) {
            if (loseHealthCheck) {
                user.stateOfLosingHealthHandle();
            }
        }
        increaseSizeChecker();
    }

    public static int circleChecking(int[] circle1, int[] circle2) {
        int dispostion = (circle1[0] - circle2[0]) * (circle1[0] - circle2[0])
                + (circle1[1] - circle2[1]) * (circle1[1] - circle2[1]);
        int radiussum = (circle1[2] + circle2[2]) * (circle1[2] + circle2[2]);
        if (dispostion == radiussum) {
            return 1;
        } else if (dispostion > radiussum) {
            return -1;
        } else {
            return 0;
        }
    }
    // handle all kinds of situation while different things met
    // return first object eat second object
    // return second object eat first object
    // return first object is obstacles and second object is not fish
    // return seoncond object is obstacles and first object is not fish
    // else do nothing

    public static AllObject[] situationHandle(AllObject firstobject, AllObject secondObject) {
        AllObject[] eaten = new AllObject[] {};
        if (firstobject.getType() == Type.Shark) {
            if (secondObject.getType() != Type.Obstacles) {
                eaten = ((Shark) firstobject).Sharkeat(secondObject);
            }
        } else if (secondObject.getType() == Type.Shark) {
            if (firstobject.getType() != Type.Obstacles) {
                eaten = ((Shark) secondObject).Sharkeat(firstobject);
            }
        } else if ((firstobject.getSize() > secondObject.getSize()
                || (firstobject instanceof Fishes && secondObject.getType() == Type.Food))) {
            eaten = ((Fishes) firstobject).eat(secondObject);
        } else if ((secondObject.getSize() > firstobject.getSize()
                || (secondObject instanceof Fishes && firstobject.getType() == Type.Food))) {
            eaten = ((Fishes) secondObject).eat(firstobject);
        } else if ((firstobject.getType() == Type.Obstacles) && (secondObject instanceof Fishes == false)
                && (secondObject.getType() != Type.Shark)) {
            secondObject.setSpeed(0);
        } else if ((secondObject.getType() == Type.Obstacles) && (firstobject instanceof Fishes)) {
            firstobject.ChangeSpeedAndDirection();
        }
        return eaten;
    }

    public void increaseSizeChecker() {
        if (FishGame.points.get() > 80 && user.getSize() == 3) {
            user.setSize(user.getSize() + 1);
            user.setImageSize(user.getImageSize() + 15);
            gameEvent.GrowSound();
        } else if (FishGame.points.get() > 40 && user.getSize() == 3) {
            user.setSize(user.getSize() + 1);
            user.setImageSize(user.getImageSize() + 15);
            gameEvent.GrowSound();
        } else if (FishGame.points.get() > 20 && user.getSize() == 2) {
            user.setSize(user.getSize() + 1);
            user.setImageSize(user.getImageSize() + 15);
            gameEvent.GrowSound();
        } else if (FishGame.points.get() > 5 && user.getSize() == 1) {
            user.setSize(user.getSize() + 1);
            user.setImageSize(user.getImageSize() + 15);
            gameEvent.GrowSound();
        } else if (FishGame.points.get() > 100) {
            gameEvent.gameOver();
            gameEvent.winSound();
        }
    }

    public boolean healthAndLifeChecker() {
        if (FishGame.health.get() < 1) {
            FishGame.setLife(FishGame.getlife().get() - 1);
            // fish heal become 5
            FishGame.health.set(5);
            user.stateOfLosingLifeHandle();
            if (FishGame.life.get() < 1) {
                if (!isCheatModeOn) {
                    gameEvent.gameOver();
                    gameEvent.loseSound();
                }
                return true;
            }
            return true;
        }
        return false;
    }

    // block the area that fish can't go cross, will not use in early level,
    // lt can work on later of the project.
    public void blockingArea() {
    }

    public void mineImport() {
        objectStorage.add(new Mine());
    }

    public void add(AllObject a) {
        objectStorage.add(a);
    }

    public void remove(AllObject a) {
        objectStorage.remove(a);
    }

    // turns cheat mode on
    public void enableCheatMode() {
        if (isCheatModeOn == false) {
            isCheatModeOn = true;
        }
    }

    // turns cheat mode off
    public void disableCheatMode() {
        if (isCheatModeOn == true) {
            isCheatModeOn = false;
        }
    }

    // add shark into fishGame list
    public void addShark() {
        objectStorage.add(new Shark());
    }

    public void updata() {
        user.updatePosition();
        for (AllObject a : objectStorage) {
            a.updatePosition();
        }
    }

    public void updatanum() {
        for (int i = 0; i < limitOfFood - numberOfFood; i++) {
            Food currentAdding = new Food();
            objectStorage.add(currentAdding);
            numberOfFood += 1;
        }
        for (int i = 0; i < limitOfType1Fish - numberOfType1Fish; i++) {
            Fishes currentAdding = new Fishes(Type.FishType1, 7, 1, 50);
            objectStorage.add(currentAdding);
            numberOfType1Fish += 1;
        }
        for (int i = 0; i < limitOfType2Fish - numberOfType2Fish; i++) {
            Fishes currentAdding = new Fishes(Type.FishType2, 6, 2, 100);
            objectStorage.add(currentAdding);
            numberOfType2Fish += 1;
        }
        for (int i = 0; i < limitOfType3Fish - numberOfType3Fish; i++) {
            Fishes currentAdding = new Fishes(Type.FishType3, 10, 3, 150);
            objectStorage.add(currentAdding);
            numberOfType3Fish += 1;
        }
        for (int i = 0; i < limitOfPoisonFish - numberOfPoisonFish; i++) {
            Fishes currentAdding = new Fishes(Type.PoisonFish, 7, 1, 50);
            objectStorage.add(currentAdding);
            numberOfPoisonFish += 1;
        }

    }

    public void userFishOutOfScreenChecker() {
        if (user.getX().get() < -15) {
            user.setX(-15);
        } else if (user.getX().get() > 1280) {
            user.setX(1280);
        }
        if (user.getY().get() < -105) {
            user.setY(-105);
        } else if (user.getY().get() > 535) {
            user.setY(535);
        }
    }

    public void updataDS() {
        for (AllObject a : objectStorage) {
            if (a instanceof Fishes) {
                ((Fishes) a).ChangeSpeedAndDirection();
            }
        }
    }

    // public void updataMine() {
    // while (!pause) {
    // mineImport();
    // try {
    // Thread.sleep(6000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    public void updataEveryseocnds() {
        updata();
    }

    public void updataEach3seconds() {
        updatanum();
        updataDS();
    }

    // load and save, I don't know yet
    public void load() {
        // A loop through the save file which will spawn all the objects contained
        // within

    }

  
    public void save() throws Exception {
        try {
            File save = new File("C:\\.FishGame\\SaveData\\save.game");
            save.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(save, false));
            writer.append("Level:Difficulty:" + Integer.toString(objectStorage.size()));
            writer.append(":" + this.getPoints().get() + ":" + this.getHealth().get() + ":" + this.getlife().get() + ":"
                    + this.user.getImageSize() + ":" + this.name);
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

    public static IntegerProperty getlife() {
        return life;
    }

    public static void setLife(int life) {
        FishGame.life = new SimpleIntegerProperty(life);
    }

    public static IntegerProperty getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        FishGame.points = new SimpleIntegerProperty(points);
    }

    public static IntegerProperty getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        FishGame.health = new SimpleIntegerProperty(health);
    }

    public boolean getIsCheatModeOn() {
        return this.isCheatModeOn;
    }

    public void setIsCheatModeOn(boolean isCheatModeOn) {
        this.isCheatModeOn = isCheatModeOn;
    }

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

    public int getLimitOfFood() {
        return this.limitOfFood;
    }

    public void setLimitOfFood(int limitOfFood) {
        this.limitOfFood = limitOfFood;
    }

    public Userfish getUser() {
        return this.user;
    }

    public void setUser(Userfish user) {
        this.user = user;
    }

    public int getNumberOfPoisonFish() {
        return this.numberOfPoisonFish;
    }

    public void setNumberOfPoisonFish(int numberOfPoisonFish) {
        this.numberOfPoisonFish = numberOfPoisonFish;
    }

}
