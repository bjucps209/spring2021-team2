package HighScores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class HighScoreTest {
    // if you called load and file doesnt exist 
    @Test
    public void testLoad_TestFileNotExist_ResultCorrect() throws IOException {
        HighScoreManager score = new HighScoreManager();
        // if the file does not exist, check if it created a new file
        Files.deleteIfExists(Paths.get("highscores.txt"));
        score.load();

        // https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
        File f = new File("highscores.txt");
        assertTrue(f.exists() && !f.isDirectory());
    }

    @Test
    public void testLoad_TestFileExists_ResultLoadData() throws IOException {
        HighScoreManager score = new HighScoreManager();
        // create
        File f = new File("highscores.txt");
        try {
            f.createNewFile();
        } catch(Exception e) {

        }        
        
        FileWriter fw = new FileWriter("highscore.txt", false);

        // write test data to file using fw
        fw.write("Bob 150");

        score.load();
        assertEquals("Bob", score.getAllScores().get(0).name);
        assertEquals(150, score.getAllScores().get(0).score);

    }

    @Test
    public void testSave_TestScore_Result() {
        HighScoreManager score = new HighScoreManager();
        HighScore testScore = new HighScore("Bob", 150);
        score.addScore(testScore);

        score.save();

        score.load(); // assume load works properly
        assertEquals("Bob", score.getAllScores().get(0).name);
        assertEquals(150, score.getAllScores().get(0).score);
    }

}
