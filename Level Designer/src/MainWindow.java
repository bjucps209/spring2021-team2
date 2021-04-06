import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class MainWindow {
    // Borrowed idea from https://stackoverflow.com/questions/41079498/javafx-differentiate-between-click-and-click-dragged
    boolean dragging=false;
    //set on clicks of the buttons to the event handler and then do the if statements 
    // after view hook model up 

    @FXML Pane pane;
    @FXML VBox dropdown;
    @FXML 
    void initialize() {
        VBox Obstacles =new VBox();
        Button obstacle1=new Button("Rock");
        Button obstacle2=new Button("Seaweed");
        Obstacles.getChildren().addAll(obstacle1,obstacle2);





        VBox FishTypes =new VBox();
        Button fishtype1=new Button("Fish Type 1");
        Button fishtype2=new Button("Fish Type 2");
        Label  sliderLabel=new Label("Number of Enemy Fish");
        Slider slider=new Slider();
        CheckBox cb=new CheckBox("Boss Fish");
        //Label  sliderLabel=new Label("Number of Enemy Fish");

        FishTypes.getChildren().add(fishtype1);
        FishTypes.getChildren().add(fishtype2);
        FishTypes.getChildren().add(sliderLabel);
        FishTypes.getChildren().add(slider);
        FishTypes.getChildren().add(cb);
 
     
        

        TitledPane t1 = new TitledPane("Obstacles",Obstacles );
       // t1.getChildren().add (new Button("b1"));
 TitledPane t2 = new TitledPane("Food", new Button("B2"));
 TitledPane t3 = new TitledPane("Fish Settings",FishTypes);
 Accordion accordion = new Accordion();
 accordion.getPanes().addAll(t1, t2, t3);

        // This is the accordion of buttons that will be added to the screen
        
        dropdown.getChildren().addAll(accordion);
        
 
    }

    //Mouse Event Handler 

  
    @FXML
    void onMouseClicked(MouseEvent value) {
        System.out.print(value.getEventType());
             
        //do handling 

}
}
