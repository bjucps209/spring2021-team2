//-----------------------------------------------------------
//File:   HighScoreTest.java
//Desc:   This file includes all the tests to ensure High Scores section is properly working
//-----------------------------------------------------------


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

    //tests adding scores and making sure they are in the correct order
    @Test
    public void testaddScore_TestAddingScore_ResultCorrect() throws IOException {
        HighScoreManager score = new HighScoreManager();
        HighScore lowerScore = new HighScore("Bob", 150);
        HighScore higherScore = new HighScore("Jerry", 180);
        score.addScore(lowerScore);
        score.addScore(higherScore);
        assert(score.getAllScores().get(0).score > score.getAllScores().get(1).score);
    }

    // if you called load and file doesnt exist 
    @Test
    public void testLoad_TestFileNotExist_ResultCorrect() throws IOException {
        HighScoreManager score = new HighScoreManager();
        // if the file does not exist, check if it created a new file
        Files.deleteIfExists(Paths.get("highscores.txt"));
        score.save();

        // https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
        File f = new File("highscores.txt");
        assertTrue(f.exists() && !f.isDirectory());
    }

    //tests the load feature of high scores to make sure it correctly reads from the txt file
    @Test
    public void testLoad_TestFileExists_ResultLoadData() throws IOException {
        HighScoreManager score = new HighScoreManager();
        // create
        File f = new File("highscores.txt");
        try {
            f.createNewFile();
        } catch(Exception e) {

        }        
        
        FileWriter fw = new FileWriter("highscores.txt", false);

        // writes test data to file using fw
        fw.write("Bob 150");
        fw.flush();

        score.load();
        assertEquals("Bob", score.getAllScores().get(0).name);
        assertEquals(150, score.getAllScores().get(0).score);
        fw.close();

    }

    //tests the save feature of high scores to make sure the scores save with the correct name properly
    @Test
    public void testSave_TestScore_Result() {
        HighScoreManager score = new HighScoreManager();
        HighScore testScore = new HighScore("Bob", 150);
        score.addScore(testScore);

        try {
            score.save();
        } catch (Exception e) {
            //TODO: handle exception
        }

        score.load(); // assume load works properly
        assertEquals("Bob", score.getAllScores().get(0).name);
        assertEquals(150, score.getAllScores().get(0).score);
    }

}
