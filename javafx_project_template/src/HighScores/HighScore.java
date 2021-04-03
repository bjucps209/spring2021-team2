package HighScores;

public class HighScore {
    String name; //holds the name value for the player
    int score; //holds the actual value of the score

    // sets the name and score equal to name and score in HighScore
    public HighScore(String name, int score) {
        throw new RuntimeException("Method not implemented");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
