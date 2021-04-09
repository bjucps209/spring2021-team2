import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GameLevel;


public class MainWindow {

    // put instance of Level Manager in MainWindow 


    







    // Borrowed idea from https://stackoverflow.com/questions/41079498/javafx-differentiate-between-click-and-click-dragged
    boolean dragging=false;
    
    
    
    Node grabbed; // for the grabbed node 






    //set on clicks of the buttons to the event handler and then do the if statements 
    // after view hook model up 

    @FXML Pane pane;
    @FXML HBox tools;
    @FXML VBox dropdown;
    @FXML 
    void initialize() {
        pane.setStyle("-fx-background-image: url('/images/ocean.png');");
        Thread update = new Thread( () -> updateState() );
        update.start();
     
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
       levelPhoto.setOnAction((ActionEvent e) -> LevelFileChooser(e));



       LevelSettings.getChildren().addAll(levelName,levelPhoto);
       

       



        VBox Obstacles =new VBox();
        Button obstacle1=new Button("Rock");
        Button obstacle2=new Button("Seaweed");
        Obstacles.getChildren().addAll(obstacle1,obstacle2);
       


        VBox FishTypes =new VBox();
        Button fishtype1=new Button("Fish Type 1");
        Button fishtype2=new Button("Fish Type 2");
        Label  sliderLabel=new Label("");
        Slider slider=new Slider();
       
        slider.setMin(1.0);
       sliderLabel.textProperty().bind(
Bindings.createStringBinding(
() ->( "Number of Enemy Fish: "+ String.valueOf((int) slider.getValue())),slider.valueProperty()));

        
    
        CheckBox bossFish=new CheckBox("Boss Fish");
        //Label  sliderLabel=new Label("Number of Enemy Fish");

        FishTypes.getChildren().addAll(fishtype1,fishtype2,sliderLabel,slider,bossFish);
       
 
     
        
        TitledPane settpane= new TitledPane("Level Settings",LevelSettings);
        TitledPane obspane = new TitledPane("Obstacles",Obstacles );
    
 VBox Food=new VBox();
 Button food1=new Button("Algae");
 Button food2=new Button("Seaweed");
 Food.getChildren().addAll(food1,food2);
 
 
 TitledPane foodpane = new TitledPane("Food", Food);
 TitledPane fishpane = new TitledPane("Fish Settings",FishTypes);
 Accordion accordion = new Accordion();
 accordion.getPanes().addAll(settpane,obspane, foodpane, fishpane);

        // This is the accordion of buttons that will be added to the screen
        
        dropdown.getChildren().addAll(accordion);
        
 
    }

    //Mouse Event Handler 
    @FXML
    void  LevelFileChooser(ActionEvent e) {
        Node node = (Node) e.getSource();
        //get the Stage the button is in 
        Stage thisStage = (Stage) node.getScene().getWindow();
        //Create a new file chooser
        FileChooser fileChooser = new FileChooser();
        //Set the Title Attribute of the fileChooser
        fileChooser.setTitle("Open Resource File");
        //Open the file chooser on the screen
        File file = fileChooser.showOpenDialog(thisStage);
        //Set a new photo per the user's choice
        /*
        try {
            
            ImageView view = new ImageView(file.getName());
    }
    */
    }

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
        System.out.print("M");
        System.out.print(value.getEventType());
             
        //do handling 

}


    public GameLevel updateState(){
        return null;
    };
}
