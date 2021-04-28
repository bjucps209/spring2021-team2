package HighScores;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HighScoreManager {

    public HighScoreManager() {
    } // constructor

    ArrayList<HighScore> allScores = new ArrayList<HighScore>(); // creates a new list for the high scores to be stored
                                                                 // in

    // adds score to list of high scores if the score is high enough to be in the
    // list
    public void addScore(HighScore newScore) {
        if (allScores.size() > 0){
        HighScore lowestHighScore = allScores.get(allScores.size() - 1);

        if (allScores.size() < 10) {
            allScores.add(newScore);
        } else if (newScore.score >= lowestHighScore.score) {
            this.allScores.remove(lowestHighScore.score);
            this.allScores.add(newScore);
        }
        allScores.sort((p1, p2) -> p2.score - p1.score);
    }
    else allScores.add(newScore);
    }

    // saves the scores
    public void save() throws IOException{
        try {
            FileWriter fw = new FileWriter("highscores.txt", false);
            for (int i = 0; i < allScores.size(); i++) {
                String stringScore = allScores.get(i).stringer();
                fw.write(stringScore);
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
        }
    }

    // gets all high scores from the file
    public String load() {
        FileInputStream in = null;
        BufferedReader reader;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader("highscores.txt"));
                do {
                    line = reader.readLine();
                    lowStringer(line);
                } while (line != "");
        } catch (Exception e) {
            System.out.println(e);
        }

        return line;
    }

    public ArrayList<HighScore> getAllScores() {
        return allScores;
    }

    //converts score into a integer and adds it to the variables in the HighScore class
    public String lowStringer(String lowString) {
        String[] lowScore = lowString.split(" ");
        HighScore hs = new HighScore(lowScore[0], Integer.parseInt(lowScore[1]));
        allScores.add(hs);
        return lowString;
    }
}