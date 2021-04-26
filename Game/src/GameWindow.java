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
    final Image IMG_Mine = new Image("/FishPicture/FirstStageUsage/mine0.jpg");
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

    static Timeline timer1;
    static Timeline timer2;
    static Timeline timer3;

    static boolean isPaused = false;

    Image current;

    public void initialize() {

        if (isLoading) {
            System.out.println("REEEEEEEEEEEEE!!!!!!!!!!!!!");
            start = new FishGame(saveGame);
        } else {
            start = new FishGame(1, 1, 0, 1, 1, 1, 1);
            System.out.println("111111111");

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

            point.textProperty().bind(FishGame.getPoints().asString());
            health.textProperty().bind(FishGame.getHealth().asString());
            life.textProperty().bind(FishGame.getlife().asString());
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

        KeyFrame timerF1 = new KeyFrame(Duration.millis(10), e -> updata());
        timer1 = new Timeline(timerF1);
        timer1.setCycleCount(-1);
        timer1.play();

        // KeyFrame timerF2 = new KeyFrame(Duration.millis(2000), e -> updataDS());
        // timer2 = new Timeline(timerF2);
        // timer2.setCycleCount(-1);
        // timer2.setDelay(Duration.millis(3));
        // timer2.play();

        // KeyFrame timerF3 = new KeyFrame(Duration.millis(1000), e -> updatanum());
        // timer3 = new Timeline(timerF3);
        // timer3.setCycleCount(-1);
        // timer3.play();
        ///////////////////////////////

        // Userfish.Up.get() +" " + Userfish.Right.get() + " " + Userfish.Down.get() + "
        // "+ Userfish.Left.get() + " " + Userfish.getDirectionenum()));
        // timer3 = new Timeline(timerF3);
        // timer3.setCycleCount(-1);
        // timer3.play();

        // System.out.println("111111111");
        // id = 0;

        // start = new FishGame(3, 5, 20, 15, 10, 5, 3);
        // imagePutting(start.getUser());
        // for (AllObject a : start.getObjectStorage()){
        // imagePutting(a);
        // }
        // updataCollison();

        // KeyFrame timerF2 = new KeyFrame(Duration.seconds(10), e -> updatanum());
        // timer2 = new Timeline(timerF2);
        // KeyFrame timerF3 = new KeyFrame(Duration.millis(100), e -> updataCollison());
        // timer3 = new Timeline(timerF3);
        // timer1.setCycleCount(-1);
        // timer2.setCycleCount(-1);
        // timer3.setCycleCount(-1);

        // timer2.play();
        // timer3.play();

    }

    // When the user presses P, it will pause the timelines, upon re-entry it will
    // resume the timelines
    // While the timelines are paused the user will be able to save by pressing the
    // ESC key
    public static void onPKeyPress() {
        if (isPaused == true) {
            timer1.play();
            timer2.play();
            // timer3.play();
            isPaused = false;
            System.out.println("Game is unpaused");
        }

        else if (isPaused == false) {
            timer1.pause();
            timer2.pause();
            // timer3.pause();
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

    // public void updatanum() {
    // for (int i = 0; i < start.getLimitOfFood() - start.getNumberOfFood(); i++) {
    // Food currentAdding = new Food();
    // start.getObjectStorage().add(currentAdding);
    // start.setNumberOfFood(start.getNumberOfFood() + 1);
    // imagePutting(currentAdding);
    // }
    // for (int i = 0; i < start.getLimitOfType1Fish() -
    // start.getNumberOfType1Fish(); i++) {
    // Fishes currentAdding = new Fishes(Type.FishType1, 7, 1, 50);
    // start.getObjectStorage().add(currentAdding);
    // start.setNumberOfType1Fish(start.getNumberOfType1Fish() + 1);
    // imagePutting(currentAdding);
    // }
    // for (int i = 0; i < start.getLimitOfType2Fish() -
    // start.getNumberOfType2Fish(); i++) {
    // Fishes currentAdding = new Fishes(Type.FishType2, 6, 2, 100);
    // start.getObjectStorage().add(currentAdding);
    // start.setNumberOfType2Fish(start.getNumberOfType2Fish() + 1);
    // imagePutting(currentAdding);
    // }
    // for (int i = 0; i < start.getLimitOfType3Fish() -
    // start.getNumberOfType3Fish(); i++) {
    // Fishes currentAdding = new Fishes(Type.FishType3, 10, 3, 150);
    // start.getObjectStorage().add(currentAdding);
    // start.setNumberOfType3Fish(start.getNumberOfType3Fish() + 1);
    // imagePutting(currentAdding);
    // }
    // for (int i = 0; i < start.getLimitOfPoisonFish() -
    // start.getNumberOfPoisonFish(); i++) {
    // Fishes currentAdding = new Fishes(Type.PoisonFish, 7, 1, 50);
    // start.getObjectStorage().add(currentAdding);
    // start.setNumberOfPoisonFish(start.getNumberOfPoisonFish() + 1);
    // imagePutting(currentAdding);
    // }
    // }

    public void updataCollison() {
        start.getUser().updatePosition();
        for (AllObject current : start.getObjectStorage()) {
            current.updatePosition();
        }
        for (int a : start.Fishmeet()) {
            pane.getChildren().removeIf((i) -> Integer.parseInt(i.getId()) == a);
        }
    }

    public void imageflipAndDeleting() {
        // ArrayList<AllObject> removeobjs = new ArrayList<>();
        for (AllObject a : start.getObjectStorage()) {
            if (a.getX().get() < -200 || a.getY().get() < -200) {
                pane.getChildren().removeIf((removeobj) -> Integer.parseInt(removeobj.getId()) == a.getId());
                // removeobjs.add(a);
                Type curr = a.getType();
                if (curr == Type.Food) {
                    start.setNumberOfFood(start.getNumberOfFood() - 1);
                } else if (curr == Type.FishType1) {
                    start.setNumberOfType1Fish(start.getNumberOfType1Fish() - 1);
                } else if (curr == Type.FishType2) {
                    start.setNumberOfType2Fish(start.getNumberOfType2Fish() - 1);
                } else if (curr == Type.FishType3) {
                    start.setNumberOfType3Fish(start.getNumberOfType3Fish() - 1);
                } else if (curr == Type.PoisonFish) {
                    start.setNumberOfPoisonFish(start.getNumberOfPoisonFish() - 1);
                    // TODO: mine need to be here
                }
                start.getObjectStorage().remove(a);
            } else if (a instanceof Fishes) {
                for (int i = 0; i < pane.getChildren().size(); i++) {
                    if (a.getId() == Integer.parseInt(pane.getChildren().get(i).getId())) {
                        if (a.getDirection() > 90 && a.getDirection() < 270) {
                            if (a.getType() == Type.FishType1) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish1l);
                            } else if (a.getType() == Type.FishType2) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish2l);
                            } else if (a.getType() == Type.FishType3) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish3l);
                            } else if (a.getType() == Type.PoisonFish) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_PoisonFish);
                            }
                        } else {
                            if (a.getType() == Type.FishType1) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish1r);
                            } else if (a.getType() == Type.FishType2) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish2r);
                            } else if (a.getType() == Type.FishType3) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_Fish3r);
                            } else if (a.getType() == Type.PoisonFish) {
                                ((ImageView) pane.getChildren().get(i)).setImage(IMG_PoisonFish);
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void isGameOver() {

    }

    public void loseLife() {

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
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else if (a.getType() == Type.FishType1) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish1l);
            } else {
                image = new ImageView(IMG_Fish1r);
            }
            image.setFitHeight(50);
            image.setFitWidth(50);
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else if (a.getType() == Type.FishType2) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish2l);
            } else {
                image = new ImageView(IMG_Fish2r);
            }
            image.setFitHeight(80);
            image.setFitWidth(80);
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else if (a.getType() == Type.FishType3) {
            if (a.getDirection() > 90 && a.getDirection() < 270) {
                image = new ImageView(IMG_Fish3l);
            } else {
                image = new ImageView(IMG_Fish3r);
            }
            image.setFitHeight(110);
            image.setFitWidth(110);
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else if (a.getType() == Type.Mine) {
            image = new ImageView(IMG_Mine);
            image.setFitHeight(35);
            image.setFitWidth(35);
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else if (a.getType() == Type.PoisonFish) {
            image = new ImageView(IMG_PoisonFish);
            image.setFitHeight(50);
            image.setFitWidth(50);
            // image.setRotationAxis(Rotate.Y_AXIS);
            // image.setRotate(0);
        } else {
            image = new ImageView();
        }
        image.setLayoutX(a.getX().get());
        image.setLayoutY(a.getY().get());
        image.setId("" + a.getId());
        pane.getChildren().add(image);
    }

    void sizeOfUserFishUpdata() {
        if (start.getUser().getImageSize() == 25) {
            ((ImageView) pane.getChildren().get(0)).setFitHeight(50);
            ((ImageView) pane.getChildren().get(0)).setFitWidth(50);
        } else if (start.getUser().getImageSize() == 40) {
            ((ImageView) pane.getChildren().get(0)).setFitHeight(80);
            ((ImageView) pane.getChildren().get(0)).setFitWidth(80);
        } else if (start.getUser().getImageSize() == 55) {
            ((ImageView) pane.getChildren().get(0)).setFitHeight(110);
            ((ImageView) pane.getChildren().get(0)).setFitWidth(110);
        } else if (start.getUser().getImageSize() == 70) {
            ((ImageView) pane.getChildren().get(0)).setFitHeight(140);
            ((ImageView) pane.getChildren().get(0)).setFitWidth(140);
        }
    }

    void updata() {
        // userfishimagechecking();
        if (!start.getUser().isStateOfLosingHealth() && !start.getUser().isStateOflosingLife()) {
            if (current != null) {
                current = ((ImageView) pane.getChildren().get(0)).getImage();
            }
        }
        pane.getChildren().clear();
        imagePuttingForUserFish();
        for (AllObject a : start.getObjectStorage()) {
            imagePutting(a);
        }

    }

    public static void setLoading() {
        amILoading = true;
    }

    // void ImageChange(){
    // ((ImageView) pane.getChildren().get(0)).setImage(swim_cycle1);
    // }

    // class RepaintThread implements Runnable{
    // public void run() {
    // while(true) {
    // start.filpimage( (int id) -> {imageflip(id);});
    // try {
    // Thread.sleep(30);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // }
}
