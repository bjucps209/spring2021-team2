import javafx.animation.Timeline;

public class GameWindow {
    
    Timeline timer;

    // main cord for action, becasue we first working on mousement user fish
    void initialize(){

    }

    //will user press P key, timeline stoped
    //what happen after that? Press P agin keep play, press Esc quit?
    void onPKeyPress(){
        timer.stop();
    }

    //Screen action again, if ESC click promp windows ask user to save
    void onESCPress(){

    }


}
