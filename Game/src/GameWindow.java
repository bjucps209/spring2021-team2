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
    // final Image swim_cycle1 = new Image("/FishPicture/Fish/Fish1/swim_cycle1.jpg");
    // final Image swim_cycle2 = new Image("/FishPicture/Fish/Fish1/swim_cycle.2.png");
    // final Image swim_cycle3 = new Image("/FishPicture/Fish/Fish1/swim_cycle.3.png");
    // final Image swim_cycle4 = new Image("/FishPicture/Fish/Fish1/swim_cycle.4.png");
    // final Image swim_cycle5 = new Image("/FishPicture/Fish/Fish1/swim_cycle.5.png");
    // final Image swim_cycle6 = new Image("/FishPicture/Fish/Fish1/swim_cycle.6.png");
    
    static FishGame start;

    @FXML
    Pane pane;
    
    static Timeline timer1;
    static Timeline timer2;
    static Timeline timer3;

    static boolean isPaused = false;

    public void initialize(){

        
    }


    // Call from MainWindow in order to start the game
    @FXML
    public void startGame() {
        System.out.println("111111111");

        start = new FishGame(1, 1, 0, 1, 1, 1, 1);

        ImageView image =  new ImageView(User_fishl);
        image.layoutXProperty().bind(start.getUser().getX());
        image.layoutYProperty().bind(start.getUser().getY());
        image.setId(""+start.getUser().getId());
        image.setFitHeight(80);
        image.setFitWidth(80);
        pane.getChildren().add(image);
        


        for (AllObject a : start.getObjectStorage()){
            imagePutting(a);
        }

        KeyFrame timerF1 = new KeyFrame(Duration.millis(50), e -> updata());
        timer1 = new Timeline(timerF1);
        timer1.setCycleCount(-1);
        timer1.play();

        KeyFrame timerF2 = new KeyFrame(Duration.millis(2000), e -> updataDS());
        timer2 = new Timeline(timerF2);
        timer2.setCycleCount(-1);
        timer2.play();
        
          KeyFrame timerF3 = new KeyFrame(Duration.millis(1000), e -> updatanum());
          timer3 = new Timeline(timerF3);
          timer3.setCycleCount(-1);
          timer3.play();


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


    //When the user presses P, it will pause the timelines, upon re-entry it will resume the timelines
    //While the timelines are paused the user will be able to save by pressing the ESC key
    public static void onPKeyPress(){
        if (isPaused == true) {
            timer1.play();
            timer2.play();
            //timer3.play();
            isPaused = false;
            System.out.println("Game is unpaused");
        }

        else if (isPaused == false) {
            timer1.pause();
            timer2.pause();
            //timer3.pause();
            isPaused = true;
            System.out.println("Game is paused");
        }
    }


    //while isPaused is true, the user can save by pressing the ESC key
    public static void onESCPress() throws Exception {
        if (isPaused == true) {
            start.save();
        }
    }

    public void updataDS(){
        for(AllObject a : start.getObjectStorage()){
            if (a instanceof Fishes){
                ((Fishes)a).ChangeSpeedAndDirection();
                System.out.println("X:"+ a.getX().get()+ " Y:" + a.getY().get() + " Speed:" + a.getSpeed() + " Direction" + a.getDirection());
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

    public void loseLife(){
        
    }

    @FXML
    public void imagePutting(AllObject a){
        ImageView image;
        if (a.getType() == Type.Food ){
            image = new ImageView(IMG_Food);
            image.setFitHeight(25);
            image.setFitWidth(25);
        }else if (a.getType() == Type.FishType1){
            image = new ImageView(IMG_Fish1l);
            image.setFitHeight(50);
            image.setFitWidth(50);
        }else if (a.getType() == Type.FishType2){
            image = new ImageView(IMG_Fish2l);
            image.setFitHeight(80);
            image.setFitWidth(80);
        }else if (a.getType() == Type.FishType3){
            image = new ImageView(IMG_Fish3l);
            image.setFitHeight(110);
            image.setFitWidth(110);
        }else if (a.getType() == Type.Mine){
            image = new ImageView(IMG_Mine);
            image.setFitHeight(35);
            image.setFitWidth(35);
        }else if (a.getType() == Type.PoisonFish){
            image = new ImageView(IMG_PoisonFish);
            image.setFitHeight(50);
            image.setFitWidth(50);
        }else{image = new ImageView();};
        image.layoutXProperty().bind(a.getX());
        image.layoutYProperty().bind(a.getY());
        image.setId(""+a.getId());
        pane.getChildren().add(image);
        System.out.println("X:" + a.getX().get() + " Y:" + a.getY().get() + " ID:"+ a.getId());
    }


    void updata(){
        start.getUser().updatePosition();
        start.updata();
        // for (int a : start.Fishmeet()){
        //      pane.getChildren().removeIf((i) -> Integer.parseInt(i.getId()) == a);
        // }



        int idNeedDelete = start.userfishcollision();
        if (idNeedDelete != 0){
            pane.getChildren().removeIf((e) -> Integer.parseInt(e.getId()) == idNeedDelete);
        }




        // if (start.userfishcollision().size() != 0){
        //     for (int i :start.userfishcollision()){
        //         pane.getChildren().removeIf((e) -> Integer.parseInt(e.getId()) == i);
        //     }
        // };
    }

    // void ImageChange(){
    //     ((ImageView) pane.getChildren().get(0)).setImage(swim_cycle1);
    // }
}
