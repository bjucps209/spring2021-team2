import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class MainWindow {
    // Borrowed idea from https://stackoverflow.com/questions/41079498/javafx-differentiate-between-click-and-click-dragged
    boolean dragging=false;
    //set on clicks of the buttons to the event handler and then do the if statements 
    // after view hook model up 

    @FXML Pane pane;
    @FXML HBox tools;
    @FXML VBox dropdown;
    @FXML 
    void initialize() {
     
        Button newFile=new Button("New");
        Button saveFile= new Button("Save");
        Button loadFile= new Button("Load");
        newFile.setOnAction((ActionEvent e) -> onNewFileClicked(e));
        saveFile.setOnAction((ActionEvent e) -> onSaveFileClicked(e));
        loadFile.setOnAction((ActionEvent e) -> onLoadFileClicked(e));

       
     tools.getChildren().addAll(newFile,saveFile,loadFile);


       VBox LevelSettings=new VBox();
       TextField levelName=new TextField("Level Name");
       Button levelPhoto=new Button("Photo Path");



       LevelSettings.getChildren().addAll(levelName,levelPhoto);
       

       



        VBox Obstacles =new VBox();
        Button obstacle1=new Button("Rock");
        Button obstacle2=new Button("Seaweed");
        Obstacles.getChildren().addAll(obstacle1,obstacle2);
        Button Experiment=new Button("I SHOULD DISSAPEAR!");
        pane.getChildren().add(Experiment);




        VBox FishTypes =new VBox();
        Button fishtype1=new Button("Fish Type 1");
        Button fishtype2=new Button("Fish Type 2");
        Label  sliderLabel=new Label("Number of Enemy Fish");
        Slider slider=new Slider();
        CheckBox bossFish=new CheckBox("Boss Fish");
        //Label  sliderLabel=new Label("Number of Enemy Fish");

        FishTypes.getChildren().addAll(fishtype1,fishtype2,sliderLabel,slider,bossFish);
       
 
     
        
        TitledPane ls= new TitledPane("Level Settings",LevelSettings);
        TitledPane t1 = new TitledPane("Obstacles",Obstacles );
       // t1.getChildren().add (new Button("b1"));
 VBox Food=new VBox();
 
 
 TitledPane t2 = new TitledPane("Food", Food);
 TitledPane t3 = new TitledPane("Fish Settings",FishTypes);
 Accordion accordion = new Accordion();
 accordion.getPanes().addAll(ls,t1, t2, t3);

        // This is the accordion of buttons that will be added to the screen
        
        dropdown.getChildren().addAll(accordion);
        
 
    }

    //Mouse Event Handler 

    @FXML
     void onLoadFileClicked(ActionEvent e) {
        System.out.println("L");
     }
      
    @FXML
     void onSaveFileClicked(ActionEvent e) {

        //if its what's on the screen reflects whats on the pane then disable the save button
        
         System.out.println("S");
    }
    @FXML
    void onNewFileClicked(ActionEvent e) {
        System.out.println("N");
        pane.getChildren().clear();
    
    }

    @FXML
    void onMouseClicked(MouseEvent value) {
        System.out.print(value.getEventType());
             
        //do handling 

}
}
