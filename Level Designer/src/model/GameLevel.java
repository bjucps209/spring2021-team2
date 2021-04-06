package model;
import java.util.ArrayList;

public class GameLevel {
    
    String levelName;
    String levelPhotoPath;
    int numFish;
    boolean bossFish;


    ArrayList<AllObject> objects=new ArrayList<AllObject>();


    public String getLevelName() {
        return levelName;
    }


    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    public String getLevelPhotoPath() {
        return levelPhotoPath;
    }


    public void setLevelPhotoPath(String levelPhotoPath) {
        this.levelPhotoPath = levelPhotoPath;
    }


    public int getNumFish() {
        return numFish;
    }


    public void setNumFish(int numFish) {
        this.numFish = numFish;
    }


    public boolean isBossFish() {
        return bossFish;
    }


    public void setBossFish(boolean bossFish) {
        this.bossFish = bossFish;
    }


    public ArrayList<AllObject> getObjects() {
        return objects;
    }


    public void setObjects(ArrayList<AllObject> objects) {
        this.objects = objects;
    }


    public GameLevel(String levelName, String levelPhotoPath, int numFish, boolean bossFish,
            ArrayList<AllObject> objects) {
        this.levelName = levelName;
        this.levelPhotoPath = levelPhotoPath;
        this.numFish = numFish;
        this.bossFish = bossFish;
        this.objects = objects;
    }
  



    


    
}
