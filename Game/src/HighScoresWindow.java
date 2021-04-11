import HighScores.HighScore;
import HighScores.HighScoreManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HighScoresWindow {

    @FXML
    VBox rank;

    @FXML
    VBox name;
    
    @FXML
    VBox score;

    public void initialize() {
        HighScoreManager scores = new HighScoreManager();
        HighScore details = new HighScore(null, 0);
        int count = 0;
        scores.load();
        System.out.println(scores.getAllScores().size());
        for (int i = 0; i < scores.getAllScores().size(); i++) {
            count++;
            Label lblRank = new Label(count + ")");
            rank.getChildren().add(lblRank);
            Label lblName = new Label(details.getName());
            name.getChildren().add(lblName);
            Label lblScore = new Label(String.valueOf(details.getScore()));
            score.getChildren().add(lblScore);
        }
    }
}
