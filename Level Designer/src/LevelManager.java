import java.nio.file.Paths;
import java.util.ArrayList;

import model.AllObject;
import model.GameLevel;
public class LevelManager {
    ArrayList<GameLevel> levelArray=new ArrayList<GameLevel>();

    public ArrayList<GameLevel> load() {
        throw new RuntimeException("Not Implemented");
    }
    
    public void save(GameLevel level) {
        throw new RuntimeException("Not Implemented");
    }

    public ArrayList<GameLevel> getLevelArray() {
        return levelArray;
    }

    public void setLevelArray(ArrayList<GameLevel> levelArray) {
        this.levelArray = levelArray;
    }

   
   
   
    
}
