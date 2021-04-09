package HighScores;

public class HighScore {
    String name; //holds the name value for the player
    int score; //holds the actual value of the score

    // sets the name and score equal to name and score in HighScore
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
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

    //converts current score to string with space in the middle
    public String stringer() {
        String newString = (this.name.toString() + " " + Integer.toString(this.score));
        return newString;
    }
}
