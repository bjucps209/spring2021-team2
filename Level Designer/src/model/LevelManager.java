/*
LevelManager.java
Level Manager functions as the manager for the levels. It has the save and load methods that Main Window
makes use of. to load and save the objects.

*/
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
        ArrayList<AllObject> levelObjects = new ArrayList<AllObject>();
        GameLevel level=new GameLevel("","", 0, false,levelObjects );
        
        System.out.println(filename);


        
        try (var reader = new DataInputStream(new FileInputStream(filename))) {


            String levelDetails="";
            


                
            
            while(reader.available()!=0){
               
               String levelRawDetails=reader.readLine();
               levelDetails+=levelRawDetails+"\n";

            }
   
     String[] lines =levelDetails.split("\\r?\\n");
    
  for (String line : lines) {
      System.out.println(line);
      if (!(line.isBlank())){

         if(line.contains("Level")){
            line=line.substring(1);
           
            System.out.println(line);
            String[] values = line.split(":");
            level.levelName=values[0];
            level.levelPhotoPath=values[1];
            level.numFish=Integer.parseInt(values[2]);
           


      }
      else {
      
        String object=line.substring(1,line.length());
      
         

         String[] objDetails=object.split(":");
         String objtype=objDetails[0];
         int objx=Integer.parseInt(objDetails[1]);
        
         int objy=Integer.parseInt(objDetails[2]);
        
        if(objtype.contains("Fish")){
           
            Fish fish=new Fish(objtype, false,objx,objy);
            level.objects.add(fish);



            

        }else if(objtype.contains("Rock") || objtype.contains("Concrete")) {
            System.out.println("O");
            Obstacle obstacle=new Obstacle(objtype, false,objx,objy);
            level.objects.add(obstacle);


        
    }else if(objtype.contains("Algae") || objtype.contains("Kelp")) {
        Food food=new Food(objtype, false,objx,objy);
        level.objects.add(food);


    }

          
        
            
        }
 
            
          
         
 
   
    }
 }
 return level;
}
    }
    /**
     * Save
     * @param GameLevel level
     * Takes in a level and converts into a text file for loading either into the game or the loader 
     */
    
    public void save(GameLevel level) throws FileNotFoundException {
        try{
        
            try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(level.levelName+".txt"))) {
                

                int levelBossFish=0;
                if (level.isBossFish()){
                    levelBossFish=1;
                }

                String save=String.format("%s:%s:%d:%d \n",level.levelName,level.levelPhotoPath,level.numFish,levelBossFish);
               
                for(AllObject obj: level.objects){
                save+=obj.getType()+ ":"+obj.getX()+":"+obj.getY()+"\n";
              

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
