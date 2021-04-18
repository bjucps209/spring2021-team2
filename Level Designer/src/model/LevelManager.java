package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
public class LevelManager {



    ArrayList<GameLevel> levelArray=new ArrayList<GameLevel>();


    /**
     * 
     * @param filename
     * @return  Game Level 
     * @throws IOException
     * Takes a file name and loads the level from the file, this method assumes that the file selected exists 
     * 
     */
    
    public GameLevel load(String filename) throws IOException {
        GameLevel level=new GameLevel("","", 0, false, null);
        
        System.out.println(filename);


        
        try (var reader = new DataInputStream(new FileInputStream(filename))) {


            String levelDetails="";
            


                
            
            while(reader.available()!=0){
               
               String levelRawDetails=reader.readLine();
               levelDetails+=levelRawDetails+"\n";

            }
   
     String[] lines =levelDetails.split("\\r?\\n");
  for (String line : lines) {
      if (!(line.isBlank())){

         if(line.charAt(0)=='L'){
            line=line.substring(1);
           
            System.out.println(line);
            String[] values = line.split(",");
            level.levelName=values[0];
            level.levelPhotoPath=values[1];
            level.numFish=Integer.parseInt(values[2]);
            System.out.println(level.levelName);
            System.out.println(level.numFish);



      }
      else if((line.charAt(0)=='O')){
          System.out.println("Object Found");
            
        }
 
            
          
         
 
   
    }
 }
 return level;
}
    }
    
    public void save(GameLevel level) throws FileNotFoundException {
        try{
        
            try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(level.levelName+".txt"))) {
                

                int levelBossFish=0;
                if (level.isBossFish()){
                    levelBossFish=1;
                }

                String save=String.format("L %s,%s,%d,%d \n",level.levelName,level.levelPhotoPath,level.numFish,levelBossFish);
               
                for(AllObject obj: level.objects){
                save+=obj.getType()+ ","+obj.getX()+","+obj.getY()+"\n";
                //rewrite this 4/18^

                }
                writer.writeBytes(save);
            }
              
                  
                   
                    
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There has been an issue with saving");

        }
                    
                 
                       
      
    }

    public ArrayList<GameLevel> getLevelArray() {
        return levelArray;
    }

    public void setLevelArray(ArrayList<GameLevel> levelArray) {
        this.levelArray = levelArray;
    }

   
   
   
    
}
