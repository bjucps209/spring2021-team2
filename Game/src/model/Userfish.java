package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Userfish extends Fishes{
    
    static Direction direction = Direction.STOP;

    public static BooleanProperty Up = new SimpleBooleanProperty(false);
    public static BooleanProperty Right = new SimpleBooleanProperty(false);
    public static BooleanProperty Down = new SimpleBooleanProperty(false);
    public static BooleanProperty Left = new SimpleBooleanProperty(false);

    Userfish(Type objType, int speed, int size, int imageSize) {
        super(objType, speed, size, imageSize);
        this.x = new SimpleIntegerProperty(450);
        this.y = new SimpleIntegerProperty(300);
        setInitialDirection(0);
        setDirection(0);
        
    }

    @Override
    public void updatePosition(){
        switch(direction){
        case Up :
            y.set(y.get() - speed);
            break;
        case RightUp :
            y.set(y.get() - speed);
            x.set(x.get() + speed);
            break;
        case Right :
            x.set(x.get() + speed);
            break;
        case RightDown :
            y.set(y.get() + speed);
            x.set(x.get() + speed);
            break;
        case Down :
            y.set(y.get() + speed);
            break;
        case LeftDown :
            y.set(y.get() + speed);
            x.set(x.get() - speed);
            break;
        case Left :
            x.set(x.get() - speed);
            break;
        case LeftUp :
            y.set(y.get() - speed);
            x.set(x.get() - speed);
            break;
            }
    }

    @Override
    public void ChangeSpeedAndDirection(){;}


    public AllObject Usereat(AllObject a){
        AllObject stor;
        stor = a;
        if (a.getType() == Type.PoisonFish||a.getType() == Type.Mine){
            FishGame.setLife(FishGame.getlife().get()-1);
            FishGame.health = new SimpleIntegerProperty(5);
            //TODO: user fish need to show some thing to user if he lose a life.
        }else if((a instanceof Fishes) && (a.getType() != Type.PoisonFish)||(a.getType() == Type.Food)){
            if (a.getType() == Type.Food){
                FishGame.setPoints(FishGame.getPoints().get()+1);
            }else if(a.getType() == Type.FishType1){
                FishGame.setPoints(FishGame.getPoints().get()+2);
            }else if(a.getType() == Type.FishType2){
                FishGame.setPoints(FishGame.getPoints().get()+4);
            }else if(a.getType() == Type.FishType3){
                FishGame.setPoints(FishGame.getPoints().get()+8);;
            }
            System.out.println(FishGame.getPoints().get());
        }
        return stor;
    }

    public void beeaten(){
        
    }


        // if up press and down press do nothing
    // if right press and left press do nothing
    // if right press and up press move up right
    public static void facingDirection() {
        if (Up.get() && !Right.get() && !Down.get() && !Left.get()){    
            direction = Direction.Up;
        }else if (Up.get() && Right.get() && !Down.get() && !Left.get()){
            direction = Direction.RightUp;
        }else if(!Up.get() && Right.get() && !Down.get() && !Left.get()){
            direction = Direction.Right;
        }else if(!Up.get() && Right.get() && Down.get() && !Left.get()){
            direction = Direction.RightDown;
        }else if (!Up.get() && !Right.get() && Down.get() && !Left.get()){
            direction = Direction.Down;
        }else if(!Up.get() && !Right.get() && Down.get() && Left.get()){
            direction = Direction.LeftDown;
        }else if(!Up.get() && !Right.get() && !Down.get() && Left.get()){
            direction = Direction.Left;
        }else if (Up.get() && !Right.get() && !Down.get() && Left.get()){
            direction = Direction.LeftUp;
        }else{
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
}
