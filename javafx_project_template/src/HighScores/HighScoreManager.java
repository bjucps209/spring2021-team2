package HighScores;

import java.util.ArrayList;

public class HighScoreManager {

    public HighScoreManager() {} //constructor

    ArrayList<HighScore> allScores = new ArrayList<HighScore>(); //creates a new list for the high scores to be stored in

    //adds score to list of high scores if the score is high enough to be in the list
    public void addScore(HighScore newScore) {
        throw new RuntimeException("Method not implemented");
    }

    //saves the scores
    public void save() {
        throw new RuntimeException("Method not implemented");
    }

    //gets all high scores from the file
    public void load() {
        throw new RuntimeException("Method not implemented");
    }

    public ArrayList<HighScore> getAllScores() {
        return allScores;
    }
}