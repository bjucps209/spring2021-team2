//--------------------------------------------------------------------
//File:   Userfish.java
//Desc:   Main code to due with userfish action, move, eat and beaten
//By:     Shubin Yuan
//-------------------------------------------------------------------
package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Userfish extends Fishes {

    // diection for fish
    static Direction direction = Direction.STOP;

    // use to konw state for fish
    boolean stateOflosingLife = false;
    boolean stateOfLosingHealth = false;

    // user to know why key pressed pass from view class
    public static BooleanProperty Up = new SimpleBooleanProperty(false);
    public static BooleanProperty Right = new SimpleBooleanProperty(false);
    public static BooleanProperty Down = new SimpleBooleanProperty(false);
    public static BooleanProperty Left = new SimpleBooleanProperty(false);

    Userfish(Type objType, int speed, int size, int imageSize) {
        super(objType, speed, size, imageSize);
        this.x = new SimpleIntegerProperty(615);
        this.y = new SimpleIntegerProperty(180);
        setInitialDirection(0);
        setDirection(0);

    }

    // action for userfish updatapostion
    @Override
    public void updatePosition() {
        switch (direction) {
        case Up:
            y.set(y.get() - speed);
            break;
        case RightUp:
            y.set(y.get() - speed);
            x.set(x.get() + speed);
            break;
        case Right:
            x.set(x.get() + speed);
            break;
        case RightDown:
            y.set(y.get() + speed);
            x.set(x.get() + speed);
            break;
        case Down:
            y.set(y.get() + speed);
            break;
        case LeftDown:
            y.set(y.get() + speed);
            x.set(x.get() - speed);
            break;
        case Left:
            x.set(x.get() - speed);
            break;
        case LeftUp:
            y.set(y.get() - speed);
            x.set(x.get() - speed);
            break;
        }
    }

    // userfish will not auto change speed and direction
    @Override
    public void ChangeSpeedAndDirection() {
        ;
    }

    // uses to handle when use fish meet the fish smaller then him.
    public void Usereat(AllObject a) {
        if (a.getType() == Type.PoisonFish || a.getType() == Type.Mine) {
            FishGame.setLife(FishGame.getlife().get() - 1);
            FishGame.setHealth(FishGame.getHealth().get() - 5);
            // TODO: user fish need to show some thing to user if he lose a life.
        } else if ((a instanceof Fishes) && (a.getType() != Type.PoisonFish) || (a.getType() == Type.Food)) {
            if (a.getType() == Type.Food) {
                FishGame.setPoints(FishGame.getPoints().get() + 1);
            } else if (a.getType() == Type.FishType1) {
                FishGame.setPoints(FishGame.getPoints().get() + 2);
            } else if (a.getType() == Type.FishType2) {
                FishGame.setPoints(FishGame.getPoints().get() + 4);
            } else if (a.getType() == Type.FishType3) {
                FishGame.setPoints(FishGame.getPoints().get() + 8);
            }
        }
    }

    // use to handel the state of losing life, use to blink the image and central
    // image
    public void stateOfLosingLifeHandle() {
        this.getX().set(615);
        this.getY().set(180);
        stateOflosingLife = true;
    }

    // use to handel the state of losing health, use to blink the image
    public void stateOfLosingHealthHandle() {
        if (FishGame.getHealth().get() > 0) {
            stateOfLosingHealth = true;
        }
    }

    // use to handle when two fish collsion with user fish have larger;
    // return ture, it user minus health
    public boolean beeaten(AllObject a) {
        FishGame.setHealth(FishGame.getHealth().get() - (a.getSize() - size));
        return true;
    }

    // use to handle when two fish collsion with user fish have same size;
    // return ture, it user minus health
    public boolean sameSize() {
        FishGame.setHealth(FishGame.getHealth().get() - 1);
        return true;
    }

    // if up press and down press do nothing
    // if right press and left press do nothing
    // if right press and up press move up right
    public static void facingDirection() {
        if (Up.get() && !Right.get() && !Down.get() && !Left.get()) {
            direction = Direction.Up;
        } else if (Up.get() && Right.get() && !Down.get() && !Left.get()) {
            direction = Direction.RightUp;
        } else if (!Up.get() && Right.get() && !Down.get() && !Left.get()) {
            direction = Direction.Right;
        } else if (!Up.get() && Right.get() && Down.get() && !Left.get()) {
            direction = Direction.RightDown;
        } else if (!Up.get() && !Right.get() && Down.get() && !Left.get()) {
            direction = Direction.Down;
        } else if (!Up.get() && !Right.get() && Down.get() && Left.get()) {
            direction = Direction.LeftDown;
        } else if (!Up.get() && !Right.get() && !Down.get() && Left.get()) {
            direction = Direction.Left;
        } else if (Up.get() && !Right.get() && !Down.get() && Left.get()) {
            direction = Direction.LeftUp;
        } else {
            direction = Direction.STOP;
        }
    }

    public static void setDirectionenum(Direction direction) {
        Userfish.direction = direction;
    }

    public static Direction getDirectionenum() {
        return Userfish.direction;
    }

    public static BooleanProperty getUp() {
        return Userfish.Up;
    }

    public static void setUp(BooleanProperty Up) {
        Userfish.Up = Up;
    }

    public static BooleanProperty getRight() {
        return Userfish.Right;
    }

    public static void setRight(BooleanProperty Right) {
        Userfish.Right = Right;
    }

    public static BooleanProperty getDown() {
        return Userfish.Down;
    }

    public static void setDown(BooleanProperty Down) {
        Userfish.Down = Down;
    }

    public static BooleanProperty getLeft() {
        return Userfish.Left;
    }

    public static void setLeft(BooleanProperty Left) {
        Userfish.Left = Left;
    }

    public boolean isStateOflosingLife() {
        return this.stateOflosingLife;
    }

    public void setStateOflosingLife(boolean stateOflosingLife) {
        this.stateOflosingLife = stateOflosingLife;
    }

    public boolean isStateOfLosingHealth() {
        return this.stateOfLosingHealth;
    }

    public void setStateOfLosingHealth(boolean stateOfLosingHealth) {
        this.stateOfLosingHealth = stateOfLosingHealth;
    }
}
