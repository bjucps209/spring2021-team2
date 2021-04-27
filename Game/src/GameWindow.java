import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SetPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import model.AllObject;
import model.Direction;
import model.FishGame;
import model.Fishes;
import model.Food;
import model.Type;
import model.Userfish;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.*;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;

import javax.swing.Action;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabDragPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameWindow {

    final Image User_fishl = new Image("/FishPicture/Fish/userfish_left.gif");
    final Image User_fishr = new Image("/FishPicture/Fish/userfish_right.gif");
    final Image IMG_Fish1l = new Image("/FishPicture/Fish/fish1_l.gif");
    final Image IMG_Fish1r = new Image("/FishPicture/Fish/fish1_r.gif");
    final Image IMG_Fish2l = new Image("/FishPicture/Fish/fish2_l.gif");
    final Image IMG_Fish2r = new Image("/FishPicture/Fish/fish2_r.gif");
    final Image IMG_Fish3l = new Image("/FishPicture/Fish/fish3_l.gif");
    final Image IMG_Fish3r = new Image("/FishPicture/Fish/fish3_r.gif");
    final Image IMG_Food = new Image("/FishPicture/FirstStageUsage/foodnoback.png");
    final Image IMG_Mine = new Image("/FishPicture/mine.png");
    final Image IMG_PoisonFish = new Image("/FishPicture/FirstStageUsage/PoisonFish.png");
    // final Image swim_cycle1 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle1.jpg");
    // final Image swim_cycle2 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle.2.png");
    // final Image swim_cycle3 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle.3.png");
    // final Image swim_cycle4 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle.4.png");
    // final Image swim_cycle5 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle.5.png");
    // final Image swim_cycle6 = new
    // Image("/FishPicture/Fish/Fish1/swim_cycle.6.png");

    static FishGame start;
    static Boolean amILoading = false;
    final File saveGame = new File("P:\\Team Project\\spring2021-team2\\Game");
    final Boolean isLoading = Loading.getState();

    @FXML
    Pane pane;

    @FXML
    Label point;

    @FXML
    Label life;

    @FXML
    Label health;

    @FXML
    HBox hbox;

    @FXML
    HBox hbox2;

    @FXML
    VBox vbox;

    static Thread thread1;
    static Thread thread2;
    static Thread thread3;
    static Thread thread4;

    static Timeline timer1;

    static boolean isPaused = false;

    Image current;

    public void initialize() {

        if (isLoading) {
            System.out.println("REEEEEEEEEEEEE!!!!!!!!!!!!!");
            start = new FishGame(saveGame);
        } else {
            // start = new FishGame(1, 1, 0, 1, 1, 1, 1);

            // StringConverter<Number> converter = new NumberStringConverter();

            start = new FishGame(1, 1, 3, 3, 2, 1, 1);
            ((Label) hbox.getChildren().get(0)).setFont(new Font("Arial", 30));
            ((Label) hbox.getChildren().get(0)).setTextFill(Color.web("#FF0000"));
            point.setFont(new Font("Arial", 30));
            point.setTextFill(Color.web("#FF0000"));

            ((Label) hbox.getChildren().get(3)).setFont(new Font("Arial", 30));
            ((Label) hbox.getChildren().get(3)).setTextFill(Color.web("#FF0000"));
            ((Label) hbox.getChildren().get(3)).relocate(650, 0);

            life.setFont(new Font("Arial", 30));
            life.setTextFill(Color.web("#FF0000"));

            ((Label) hbox2.getChildren().get(1)).setFont(new Font("Arial", 30));
            ((Label) hbox2.getChildren().get(1)).setTextFill(Color.web("#FF0000"));
            health.setFont(new Font("Arial", 30));
            health.setTextFill(Color.web("#FF0000"));
        }

        // initial putting of image
        ImageView image = new ImageView();
        if (start.getUser().getType() == Type.UserFish) {
            image = new ImageView(User_fishl);
        }
        if (start.getUser().getSize() == 1) {
            image.setFitHeight(50);
            image.setFitWidth(50);
        } else if (start.getUser().getSize() == 2) {
            image.setFitHeight(80);
            image.setFitWidth(80);
        } else if (start.getUser().getSize() == 3) {
            image.setFitHeight(110);
            image.setFitWidth(110);
        } else if (start.getUser().getSize() == 4) {
            image.setFitHeight(140);
            image.setFitWidth(140);
        }
        image.setLayoutX(start.getUser().getX().get());
        image.setLayoutY(start.getUser().getY().get());
        image.setId("" + start.getUser().getId());
        pane.getChildren().add(image);

        thread1 = new Thread(() -> start.updata());
        thread2 = new Thread(() -> start.updataEach3seconds());
        thread3 = new Thread(() -> start.collisonHandler());
        thread4 = new Thread(() -> start.mineImport());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        KeyFrame timerF1 = new KeyFrame(Duration.millis(10), e -> updata());
        timer1 = new Timeline(timerF1);
        timer1.setCycleCount(-1);
        timer1.play();
    }

    // When the user presses P, it will pause the timelines, upon re-entry it will
    // resume the timelines
    // While the timelines are paused the user will be able to save by pressing the
    // ESC key
    public static void onPKeyPress() throws InterruptedException {
        if (isPaused == true) {
            start.continous();
            timer1.play();
            isPaused = false;
            System.out.println("Game is unpaused");
        }

        else if (isPaused == false) {
            timer1.pause();
            start.pause();
            isPaused = true;
            System.out.println("Game is paused");
        }
    }

    // while isPaused is true, the user can save by pressing the ESC key
    public static void onESCPress() throws Exception {
        if (isPaused == true) {
            System.out.println("The game has been saved.");
            start.save();
        }
    }

    @FXML
    public void isGameOver() {

    }

    public void imagePuttingForUserFish() {
        ImageView image = new ImageView();

        if (start.getUser().getType() == Type.UserFish) {
            if (start.getUser().getDirectionenum() == Direction.Left
                    || start.getUser().getDirectionenum() == Direction.LeftUp
                    || start.getUser().getDirectionenum() == Direction.LeftDown) {
                if (!start.getUser().isStateOfLosingHealth() && !start.getUser().isStateOflosingLife()) {
                    image = new ImageView(User_fishl);
                }
            } else if (start.getUser().getDirectionenum() == Direction.Right
                    || start.getUser().getDirectionenum() == Direction.RightUp
                    || start.getUser().getDirectionenum() == Direction.RightDown) {
                if (!start.getUser().isStateOfLosingHealth() && !start.getUser().isStateOflosingLife()) {
                    image = new ImageView(User_fishr);
                }
            } else {
                if (!start.getUser().isStateOfLosingHealth() && !start.getUser().isStateOflosingLife()) {
                    image = new ImageView(current);
                }
            }
        }
        if (start.getUser().getSize() == 1) {
            image.setFitHeight(50);
            image.setFitWidth(50);
        } else if (start.getUser().getSize() == 2) {
            image.setFitHeight(80);
            image.setFitWidth(80);
        } else if (start.getUser().getSize() == 3) {
            image.setFitHeight(110);
            image.setFitWidth(110);
        } else if (start.getUser().getSize() == 4) {
            image.setFitHeight(140);
            image.setFitWidth(140);
        }
        image.setLayoutX(start.getUser().getX().get());
        image.setLayoutY(start.getUser().getY().get());
        image.setId("" + start.getUser().getId());
        pane.getChildren().add(image);
    }

    public void imagePutting(AllObject a) {
        ImageView image;
        if (a.getType() == Type.Food) {
            image = new ImageView(IMG_Food);
            image.setFitHeight(25);
            image.setFitWidth(25);
        } else if (a.getType() == Type.FishType1) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish1l);
            } else {
                image = new ImageView(IMG_Fish1r);
            }
            image.setFitHeight(50);
            image.setFitWidth(50);
        } else if (a.getType() == Type.FishType2) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish2l);
            } else {
                image = new ImageView(IMG_Fish2r);
            }
            image.setFitHeight(80);
            image.setFitWidth(80);
        } else if (a.getType() == Type.FishType3) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish3l);
            } else {
                image = new ImageView(IMG_Fish3r);
            }
            image.setFitHeight(110);
            image.setFitWidth(110);
        } else if (a.getType() == Type.Mine) {
            image = new ImageView(IMG_Mine);
            image.setFitHeight(35);
            image.setFitWidth(35);
        } else if (a.getType() == Type.PoisonFish) {
            image = new ImageView(IMG_PoisonFish);
            image.setFitHeight(50);
            image.setFitWidth(50);
        } else {
            image = new ImageView();
        }
        image.setLayoutX(a.getX().get());
        image.setLayoutY(a.getY().get());
        image.setId("" + a.getId());
        pane.getChildren().add(image);
    }

    void updata() {
        // userfishimagechecking();
        if (!start.getUser().isStateOfLosingHealth() && !start.getUser().isStateOflosingLife()) {
            if (((ImageView) pane.getChildren().get(0)).getImage() != null) {
                current = ((ImageView) pane.getChildren().get(0)).getImage();
            }
        }
        pane.getChildren().clear();
        imagePuttingForUserFish();
        for (AllObject a : start.getObjectStorage()) {
            imagePutting(a);
        }

        point.setText(String.valueOf(start.getPoints().get()));
        life.setText(String.valueOf(start.getlife().get()));
        health.setText(String.valueOf(start.getHealth().get()));

    }

    public static void setLoading() {
        amILoading = true;
    }
}
