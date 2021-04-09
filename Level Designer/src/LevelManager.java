import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.GameLevel;
public class LevelManager {



    ArrayList<GameLevel> levelArray=new ArrayList<GameLevel>();

    public GameLevel load(String filename) throws IOException {
        
        try (var reader = new DataInputStream(new FileInputStream(filename))) {
            


                
            
            while(reader.available()!=0){
                GameLevel level=new GameLevel("","", 0, false, null);
               // level.setLevelName(levelName);

             /*
             Suit suit=new Suit("","","","",0,false,"");
             suit.marknumber=reader.readUTF();
             suit.name = reader.readUTF();
             suit.equipment=reader.readUTF();
             suit.purpose = reader.readUTF();
             suit.rating=reader.readInt();
             suit.destroyed=reader.readBoolean();
             suit.photo=reader.readUTF();
             suits.add(suit);
            }
             
            instance.getSuits().clear();
            instance.getSuits().addAll(suits);
            
         }
         return suits;
         */
            }
 
 
            
          
         
 
     return null;
   
 }
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
