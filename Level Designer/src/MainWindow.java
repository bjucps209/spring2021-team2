import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AllObject;
import model.GameLevel;
import model.LevelManager;


public class MainWindow {

    // put instance of Level Manager in MainWindow
    LevelManager manager=new LevelManager();
    


    //GameLevel currentState=new GameLevel("levelName","", "","", "");


    







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
        saveFile.setOnAction((ActionEvent e) -> {
            try {
                onSaveFileClicked(e);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        });
        loadFile.setOnAction((ActionEvent e) -> {
            try {
                onLoadFileClicked(e);
            } catch (IOException e1) {
            
                e1.printStackTrace();
            }
        });

       
     tools.getChildren().addAll(newFile,saveFile,loadFile);


       VBox LevelSettings=new VBox();
       TextField levelName=new TextField("Level Name");
       Button levelPhoto=new Button("Photo");
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
        
        try {
            String style=String.format("-fx-background-image: url('/images/%s');", file.getName());
            pane.setStyle(style);
      
        }catch(Exception exception){
            System.out.println("Exception with saving");
        }
    
        
    
    
    }

    @FXML
     void onLoadFileClicked(ActionEvent e) throws IOException {
   
        Node node = (Node) e.getSource();
        //get the Stage the button is in 
        Stage thisStage = (Stage) node.getScene().getWindow();
        //Create a new file chooser
        FileChooser fileChooser = new FileChooser();
        //Set the Title Attribute of the fileChooser
        fileChooser.setTitle("Open Save File");
        //Open the file chooser on the screen
        File file = fileChooser.showOpenDialog(thisStage);
        //Set a new photo per the user's choice
        
        try {
         manager.load(file.getName());
            
      
        }catch(Exception exception){
            exception.printStackTrace();
            System.out.println("Exception with saving");
        }
    
        
    
    
    

     }
      
    @FXML
     void onSaveFileClicked(ActionEvent e) throws FileNotFoundException {
         Accordion accord=(Accordion) dropdown.getChildren().get(0);
         TitledPane tp=(TitledPane) accord.getChildrenUnmodifiable().get(0);
         VBox vbox= (VBox) tp.getContent();
        
        
         
        TextField levelName =(TextField) vbox.getChildrenUnmodifiable().get(0);
         


         ArrayList<AllObject> obj=new ArrayList<AllObject>();
         AllObject object=new AllObject();
        //object attrs 
        
         obj.add(object);
         GameLevel level=new GameLevel(levelName.getText(),"/somepath", 2, false,obj);
         manager.save(level);

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
