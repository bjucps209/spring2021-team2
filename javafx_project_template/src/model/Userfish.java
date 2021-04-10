package model;

public class Userfish extends Fishes{

    Userfish(Type objType, int speed, int size, int imageSize) {
        super(objType, speed, size, imageSize);
        setX(450);
        setY(450);
        setInitialDirection(0);
        setDirection(0);
    }

    @Override
    public void updatePosition(){

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
}
