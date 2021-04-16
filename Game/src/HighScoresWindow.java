import HighScores.HighScore;
import HighScores.HighScoreManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HighScoresWindow {

    @FXML
    ImageView imageHS;

    @FXML
    VBox backgroundBox;

    @FXML
    VBox rank;

    @FXML
    VBox name;
    
    @FXML
    VBox score;

    public void initialize() {
        // BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        // Background background = new Background(backgroundFill);
        // backgroundBox.setBackground(background);

        imageHS.setPreserveRatio(true);

        HighScoreManager scores = new HighScoreManager();
        int count = 0;
        scores.load();
        for (int i = 0; i < scores.getAllScores().size(); i++) {
            HighScore currentScore = scores.getAllScores().get(i);
            count++;
            Label lblRank = new Label(count + ")");
            rank.getChildren().add(lblRank);
            Label lblName = new Label(currentScore.getName());
            name.getChildren().add(lblName);
            Label lblScore = new Label(String.valueOf(currentScore.getScore()));
            score.getChildren().add(lblScore);
        }
    }
}
