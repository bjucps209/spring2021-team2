import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Userfish;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

public class MainWindow {

    @FXML
    void onStartClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        Stage gameWindow = new Stage();

        Scene scene = new Scene(loader.load());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                    switch (event.getCode()) {
                    case W:
                        Userfish.Up.set(true);
                        break;
                    case D:
                        Userfish.Right.set(true);
                        break;
                    case S:
                        Userfish.Down.set(true);
                        break;
                    case A:
                        Userfish.Left.set(true);
                        break;
                    case ESCAPE:
                        try {
                            GameWindow.onESCPress();
                        } catch (Exception e) {

                        }
                    }
                    Userfish.facingDirection();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    switch (event.getCode()) {
                    case W:
                        Userfish.Up.set(false);
                        break;
                    case D:
                        Userfish.Right.set(false);
                        break;
                    case S:
                        Userfish.Down.set(false);
                        break;
                    case A:
                        Userfish.Left.set(false);
                        break;
                    }
                    Userfish.facingDirection();
                }
            }
        });

        gameWindow.setScene(scene);

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
