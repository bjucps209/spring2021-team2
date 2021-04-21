import HighScores.HighScore;
import HighScores.HighScoreManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;


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

    @FXML
    Label hiScore;

    public void initialize() {
        // BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        // Background background = new Background(backgroundFill);
        // backgroundBox.setBackground(background);

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.web("0x3b596d"));
       
        hiScore.setEffect(innerShadow);
        hiScore.setText("High Scores");
        hiScore.setTextFill(Color.color(0.5, .8, 1));
        hiScore.setFont(Font.font("Verdana", FontWeight.BOLD, 55));

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
