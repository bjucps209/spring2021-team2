package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Userfish extends Fishes{
    
    Direction direction = Direction.STOP;

     BooleanProperty Up = new SimpleBooleanProperty(false);
     BooleanProperty Right = new SimpleBooleanProperty(false);
     BooleanProperty Down = new SimpleBooleanProperty(false);
     BooleanProperty Left = new SimpleBooleanProperty(false);

    Userfish(Type objType, int speed, int size, int imageSize) {
        super(objType, speed, size, imageSize);
        this.x = new SimpleIntegerProperty(450);
        this.y = new SimpleIntegerProperty(450);
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

    @Override
    public AllObject[] eat(AllObject a){
        AllObject[] stor;
        if (a.getType() == Type.PoisonFish||a.getType() == Type.Mine){
            stor = new AllObject[2];
            stor[0] = this;
            stor[1] = a;
            FishGame.life -= 1;
        }else if((a instanceof Fishes) && (a.getType() != Type.PoisonFish)||(a.getType() == Type.Food)){
            stor = new AllObject[1];
            stor[0] = a;
            if (a.getType() == Type.Food){
                FishGame.points += 1;
            }else if(a.getType() == Type.FishType1){
                FishGame.points += 2;
            }else if(a.getType() == Type.FishType2){
                FishGame.points += 4;
            }else if(a.getType() == Type.FishType3){
                FishGame.points += 8;
            }
        }else{
            stor = new AllObject[1];
            stor[0] = null;
        }
        return stor;
    }


        // if up press and down press do nothing
    // if right press and left press do nothing
    // if right press and up press move up right
    public void moveDirection() {
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
            direction = Direction.Down;
        }else if (Up.get() && !Right.get() && !Down.get() && Left.get()){
            direction = Direction.LeftUp;
        }else{
            direction = Direction.STOP;
        }

    }

    public void setDirectionenum(Direction direction) {
		this.direction = direction;
	}

    public Direction getDirectionenum() {
        return this.direction;
    }

    public BooleanProperty getUp() {
        return this.Up;
    }

    public void setUp(BooleanProperty Up) {
        this.Up = Up;
    }

    public BooleanProperty getRight() {
        return this.Right;
    }

    public void setRight(BooleanProperty Right) {
        this.Right = Right;
    }

    public BooleanProperty getDown() {
        return this.Down;
    }

    public void setDown(BooleanProperty Down) {
        this.Down = Down;
    }

    
    public BooleanProperty getLeft() {
        return this.Left;
    }

    public void setLeft(BooleanProperty Left) {
        this.Left = Left;
    }
}
