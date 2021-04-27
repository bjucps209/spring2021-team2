import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.InnerShadow;
import javafx.stage.Stage;
import model.Userfish;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;

public class MainWindow {

    @FXML
    Label title;

    public void initialize() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.web("black"));

        title.setEffect(innerShadow);
        title.setText("fish.io");
        title.setTextFill(Color.color(0.2, .7, 1));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 55));
    }

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
                            e.printStackTrace();
                        }
                        break;
                    case P:
                        try {
                            GameWindow.onPKeyPress();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
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
    void onLoadClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Loading.setLoading(true);

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
                        break;
                    case P:
                        try {
                            GameWindow.onPKeyPress();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
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

    @FXML
    void onHelpClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpWindow.fxml"));

        Stage Help = new Stage();

        Help.setScene(new Scene(loader.load()));

        Help.show();

    }
}