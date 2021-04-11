import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class MainWindow {

    @FXML
    void onStartClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        Stage gameWindow = new Stage();

        gameWindow.setScene(new Scene(loader.load()));

        gameWindow.show();
        
    }

    @FXML
    void onHighScoresClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HighScoresWindow.fxml"));

        Stage HighScoresWindow = new Stage();

        HighScoresWindow.setScene(new Scene(loader.load()));

        HighScoresWindow.show();
        
    }
}
