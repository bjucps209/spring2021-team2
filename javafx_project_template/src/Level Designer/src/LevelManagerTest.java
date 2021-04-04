import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LevelManagerTest {



    @Test
    public void saveTest(){
        
        

        

    }
    @Test
    public void loadTest() throws IOException{
        

    }
    @Test
    public void doesFileExist_Throws()  throws IOException{
        Files.deleteIfExists(Paths.get("testload.txt"));
        LevelManager manager = new LevelManager();
        manager.load();


        





        

    }
    


}