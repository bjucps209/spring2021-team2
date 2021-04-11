import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SetPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.AllObject;
import model.Direction;
import model.FishGame;
import model.Type;
import javafx.util.Duration;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.Action;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;


public class GameWindow {

    BooleanProperty Up = new SimpleBooleanProperty(false);
    BooleanProperty Right = new SimpleBooleanProperty(false);
    BooleanProperty Down = new SimpleBooleanProperty(false);
    BooleanProperty Left = new SimpleBooleanProperty(false);

    final Image IMG_Fish1 = new Image("/FishPicture/noback.png");
    final Image IMG_Fish2 = new Image("/FishPicture/noback2.png");
    final Image IMG_Fish3 = new Image("/FishPicture/noback3.png");
    final Image IMG_Food = new Image("/FishPicture/foodnoback.png");
    
    FishGame start;

    @FXML
    Pane pane;
    
    Timeline timer1;
    Timeline timer2;
    Timeline timer3;

    // main cord for action, becasue we first working on mousement user fish
    void initialize(){

        start = new FishGame(3, 5, 20, 15, 10, 5, 3);
        start.Fishmeet();
        imagePutting(start.getUser());
        for (AllObject a : start.getObjectStorage()){
            imagePutting(a);
        }
        start.getUser().getUp().bind(Up);
        start.getUser().getRight().bind(Right);
        start.getUser().getDown().bind(Down);
        start.getUser().getLeft().bind(Left);
        KeyFrame timerF1 = new KeyFrame(Duration.seconds(3), e -> updataDS());
        timer1 = new Timeline(timerF1);
        KeyFrame timerF2 = new KeyFrame(Duration.seconds(10), e -> updatanum());
        timer2 = new Timeline(timerF2);
        KeyFrame timerF3 = new KeyFrame(Duration.millis(100), e -> updataPos());
        timer3 = new Timeline(timerF3);
        timer1.setCycleCount(-1);
        timer2.setCycleCount(-1);
        timer3.setCycleCount(-1);
        timer1.play();
        timer2.play();
        timer3.play();
    }

    //will user press P key, timeline stoped
    //what happen after that? Press P agin keep play, press Esc quit?
    void onPKeyPress(){
        timer1.pause();
        timer2.pause();
        timer3.pause();
    }


    //Screen action again, if ESC click promp windows ask user to save
    void onESCPress(){

    }

    public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP :
            Up.setValue(true);
			break;
		case KeyEvent.VK_RIGHT :
            Right.setValue(true);
			break;
		case KeyEvent.VK_DOWN :
            Down.setValue(true);
			break;
		case KeyEvent.VK_LEFT :
            Left.setValue(true);
			break;
        }
		start.getUser().moveDirection();
	}

    public void keyRelease(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_W :
			Up.setValue(false);
			break;
		case KeyEvent.VK_D :
			Right.setValue(false);
			break;
		case KeyEvent.VK_S :
			Down.setValue(false);
			break;
		case KeyEvent.VK_A :
			Left.setValue(false);
			break;
		}
		start.getUser().moveDirection();
    }

    public void updataDS(){

    }

    public void updatanum(){

    }

    public void updataPos(){

    }

    public void fishchecking(){

    }

    public void imagePutting(AllObject a){
        if (a.getType() == Type.Food ){
            ImageView image = new ImageView(IMG_Food);
            image.relocate(a.getX().get(), a.getY().get());
            image.xProperty().bind(a.getX(), a.getY())
            pane.getChildren().add(image);
        }
    }
}
