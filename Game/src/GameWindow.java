import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SetPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.AllObject;
import model.Direction;
import model.FishGame;
import model.Fishes;
import model.Food;
import model.Type;
import model.Userfish;
import javafx.util.Duration;
import java.awt.Graphics;
import java.awt.Toolkit;
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

    final Image IMG_Fish1 = new Image("/FishPicture/FirstStageUsage/noback.png");
    final Image IMG_Fish2 = new Image("/FishPicture/FirstStageUsage/noback2.png");
    final Image IMG_Fish3 = new Image("/FishPicture/FirstStageUsage/noback3.png");
    final Image IMG_Food = new Image("/FishPicture/FirstStageUsage/foodnoback.png");
    final Image IMG_Mine = new Image("/FishPicture/FirstStageUsage/mine0.jpg");
    final Image IMG_PoisonFish = new Image("/FishPicture/FirstStageUsage/PoisonFish.png");
    
    FishGame start;

    @FXML
    Pane pane;
    
    Timeline timer1;
    Timeline timer2;
    Timeline timer3;

    // main cord for action, becasue we first working on mousement user fish
    public void initialize(){

        System.out.println("111111111");

        start = new FishGame(1, 1, 0, 1, 1, 1, 1);
        for (AllObject a : start.getObjectStorage()){
            imagePutting(a);
        }
        imagePutting(start.getUser());
        KeyFrame timerF1 = new KeyFrame(Duration.millis(50), e -> updata());
        timer1 = new Timeline(timerF1);
        timer1.setCycleCount(-1);
        timer1.play();

        KeyFrame timerF2 = new KeyFrame(Duration.millis(200), e -> updataDS());
        timer2 = new Timeline(timerF1);
        timer2.setCycleCount(-1);
        timer2.play();
        // KeyFrame timerF3 = new KeyFrame(Duration.millis(50), e -> System.out.println(
        //     Userfish.Up.get() +" " +  Userfish.Right.get() + " " + Userfish.Down.get() + " "+ Userfish.Left.get() + " " + Userfish.getDirectionenum()));
        // timer3 = new Timeline(timerF3);
        // timer3.setCycleCount(-1);
        // timer3.play();






        // System.out.println("111111111");
        // id = 0;

        // start = new FishGame(3, 5, 20, 15, 10, 5, 3);
        // imagePutting(start.getUser());
        // for (AllObject a : start.getObjectStorage()){
        //     imagePutting(a);
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

    //will user press P key, timeline stoped
    //what happen after that? Press P agin keep play, press Esc quit?
    public void onPKeyPress(){
        timer1.pause();
        timer2.pause();
        timer3.pause();
    }


    //Screen action again, if ESC click promp windows ask user to save
    void onESCPress(){

    }

    public void updataDS(){
        for(AllObject a : start.getObjectStorage()){
            if (a instanceof Fishes){
                ((Fishes)a).randDirection();
                ((Fishes)a).randSpeed();
            }
        }
    }

    public void updatanum(){
        for (int i = 0; i < start.getLimitOfFood() - start.getNumberOfFood(); i++){
            Food currentAdding = new Food();
            start.getObjectStorage().add(currentAdding);
            imagePutting(currentAdding);
        }for (int i = 0; i < start.getLimitOfType1Fish() - start.getNumberOfType1Fish(); i++){
            Fishes currentAdding = new Fishes(Type.FishType1, 7, 1, 50);
            start.getObjectStorage().add(currentAdding);
            imagePutting(currentAdding);
        }for (int i = 0; i < start.getLimitOfType2Fish() - start.getNumberOfType2Fish(); i++){
            Fishes currentAdding = new Fishes(Type.FishType2, 6, 2, 100);
            start.getObjectStorage().add(currentAdding);
            imagePutting(currentAdding);
        }for (int i = 0; i < start.getLimitOfType3Fish() - start.getNumberOfType3Fish(); i++){
            Fishes currentAdding = new Fishes(Type.FishType3, 10, 3, 150);
            start.getObjectStorage().add(currentAdding);
            imagePutting(currentAdding);
        }for (int i = 0; i <  start.getLimitOfPoisonFish() - start.getNumberOfPoisonFish(); i++){
            Fishes currentAdding = new Fishes(Type.PoisonFish, 7, 1, 50);
            start.getObjectStorage().add(currentAdding);
            imagePutting(currentAdding);
        }
    }


    public void updataCollison(){
        start.getUser().updatePosition();
        for (AllObject current : start.getObjectStorage()){
            current.updatePosition();
        }
        for (int a : start.Fishmeet()){
            pane.getChildren().removeIf((i) -> Integer.parseInt(i.getId()) == a);
        }
    }

    public void fishchecking(){

    }

    @FXML
    public void isGameOver(){

    }

    @FXML
    public void imagePutting(AllObject a){
        ImageView image;
        if (a.getType() == Type.Food ){
            image = new ImageView(IMG_Food);
        }else if (a.getType() == Type.FishType1){
            image = new ImageView(IMG_Fish1);
        }else if (a.getType() == Type.FishType2){
            image = new ImageView(IMG_Fish2);
        }else if (a.getType() == Type.FishType3){
            image = new ImageView(IMG_Fish3);
        }else if (a.getType() == Type.Mine){
            image = new ImageView(IMG_Mine);
        }else if (a.getType() == Type.PoisonFish){
            image = new ImageView(IMG_PoisonFish);
        }else{image = new ImageView();};
        image.layoutXProperty().bind(a.getX());
        image.layoutYProperty().bind(a.getY());
        image.setId(""+a.getId());
        pane.getChildren().add(image);
    }


    void updata(){
        start.getUser().updatePosition();
        start.updata();
        // for (int a : start.Fishmeet()){
        //      pane.getChildren().removeIf((i) -> Integer.parseInt(i.getId()) == a);
        // }
        start.userfishcollision().size();
        // if (start.userfishcollision().size() != 0){
        //     for (int i :start.userfishcollision()){
        //         pane.getChildren().removeIf((e) -> Integer.parseInt(e.getId()) == i);
        //     }
        // };
    }
}
