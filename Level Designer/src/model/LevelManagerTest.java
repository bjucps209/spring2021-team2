package model;

import org.junit.Test;


import java.util.ArrayList;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LevelManagerTest {



    @Test
    public void saveTest() throws FileNotFoundException{

        LevelManager manager= new LevelManager();
        ArrayList<AllObject> objects=new ArrayList<AllObject>();
        GameLevel level=new GameLevel("The Tank", "/images/somepath", 20, false,objects);
        manager.save(level);
       
        try {
            manager.load("The Tank");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            
        } //This assumes load works.
        GameLevel levelfromLoad=manager.getLevelArray().get(0);
        assertEquals(levelfromLoad.getLevelName(),"The Tank");
        assertEquals(levelfromLoad.getLevelPhotoPath(),"/somepath");
        assertEquals(levelfromLoad.getNumFish(),20);
        assertEquals(levelfromLoad.isBossFish(),false);
        assertEquals(levelfromLoad.getObjects(), objects);



        
        

        

    }
    @Test
    public void loadTest() throws IOException{
        LevelManager manager= new LevelManager();
         // create
        File f = new File("testsave.txt");
        try {
            f.createNewFile();
        } catch(Exception e) {

        }        
        
        FileWriter fw = new FileWriter("testsave.txt", false);

        // write test data to file using fw
        fw.write("L The Aquarium,1, Aquarium.png,9 ,1");

         manager.load("The Aquarium");
        assertEquals("The Aquarium", manager.getLevelArray().get(0).getLevelName());
    

    }
    @Test
    public void doesFileExist_Throws()  throws IOException{
        Files.deleteIfExists(Paths.get("testload.txt"));
        LevelManager manager = new LevelManager();
       

        





        

    }
    


}