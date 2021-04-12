import java.io.File;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
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
    final Image IMG_FISH1 = new Image("/images/fish.png");
    final Image IMG_FISH2 = new Image("/images/fish2.png");
    final Image IMG_ROCK= new Image("/images/rock.png");
    final Image IMG_CONCRETE= new Image("/images/concrete.png");
    final Image IMG_KELP=new Image("/images/kelp.png");
    final Image IMG_ALGAE=new Image("/images/algae.png");
   

   // private static final String IMG_FISH1 = null;





    // put instance of Level Manager in MainWindow
    LevelManager manager=new LevelManager();
    

   


    GameLevel currentState=new GameLevel("levelName","", 0,false, null);


    







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
        Button obstacle1=new Button("Concrete");
        obstacle1.setOnAction((ActionEvent e) -> {onobstacleType1(e);});
        Button obstacle2=new Button("Rock");
        obstacle2.setOnAction((ActionEvent e) -> {onobstacleType2(e);});
        Obstacles.getChildren().addAll(obstacle1,obstacle2);
       


        VBox FishTypes =new VBox();
        Button fishtype1=new Button("Fish Type 1");
        fishtype1.setOnAction((ActionEvent e) -> {onFishType1(e);});
        Button fishtype2=new Button("Fish Type 2");
        fishtype2.setOnAction((ActionEvent e) -> {onFishType2(e);});
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
 food1.setOnAction((ActionEvent e) -> {onfood1(e);});
 Button food2=new Button("Kelp");
 food2.setOnAction((ActionEvent e) -> {onfood2(e);});
 Food.getChildren().addAll(food1,food2);
 
 
 TitledPane foodpane = new TitledPane("Food", Food);
 TitledPane fishpane = new TitledPane("Fish Settings",FishTypes);
 Accordion accordion = new Accordion();
 accordion.getPanes().addAll(settpane,obspane, foodpane, fishpane);

        // This is the accordion of buttons that will be added to the screen
        
        dropdown.getChildren().addAll(accordion);
        
 
    }
    @FXML
    void onobstacleType2(ActionEvent e) {
        var img = new ImageView(IMG_ROCK);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        
        makeDraggable(img);
        pane.getChildren().add(img);
    }
    @FXML
    void onobstacleType1(ActionEvent e) {
        var img = new ImageView(IMG_CONCRETE);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        
        makeDraggable(img);
        pane.getChildren().add(img);

    }
    @FXML
     void onfood2(ActionEvent e) {
        var img = new ImageView(IMG_KELP);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        
        makeDraggable(img);
        pane.getChildren().add(img);

    }
    @FXML
     void onfood1(ActionEvent e) {
        var img = new ImageView(IMG_ALGAE);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        
        makeDraggable(img);
        pane.getChildren().add(img);

    }
    @FXML
    void onFishType1(ActionEvent e) {
       
        var img = new ImageView(IMG_FISH1);
       img.setPreserveRatio(true);
       img.setFitWidth(20);
       
       makeDraggable(img);
       pane.getChildren().add(img);
      // makeClickable(img);


    }
    @FXML
    void onFishType2(ActionEvent e) {
       
        var img = new ImageView(IMG_FISH2);
       img.setPreserveRatio(true);
       img.setFitWidth(20);
       
       makeDraggable(img);
       pane.getChildren().add(img);
      // makeClickable(img);


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
            var alert = new Alert(AlertType.WARNING, "Error in loading file!");
            alert.setHeaderText(null);
            alert.show();
            
        }
    
        
    
    
    

     }
      
    @FXML
     void onSaveFileClicked(ActionEvent e) throws FileNotFoundException {
         //Accordion accord=(Accordion) dropdown.getChildren().get(0);
        // TitledPane tp=(TitledPane) accord.getChildrenUnmodifiable().get(0);
        // VBox vbox= (VBox) tp.getContent();
        
        
         
       // TextField levelName =(TextField) vbox.getChildrenUnmodifiable().get(0);
         


        // ArrayList<AllObject> obj=new ArrayList<AllObject>();
         
       
         manager.save(currentState);

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
        for(Node n:pane.getChildren()){
            n.getUserData();

            

            
        }
        return currentState;

    };


     private void makeDraggable(Node node) {
        final Delta dragDelta = new Delta();

        node.setOnMouseEntered(me -> node.getScene().setCursor(Cursor.HAND) );
        node.setOnMouseExited(me -> node.getScene().setCursor(Cursor.DEFAULT) );
        node.setOnMousePressed(me -> {
            dragDelta.x = me.getX();
            dragDelta.y = me.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });
        node.setOnMouseDragged(me -> {
            node.setLayoutX(node.getLayoutX() + me.getX() - dragDelta.x);
            node.setLayoutY(node.getLayoutY() + me.getY() - dragDelta.y);
        });
        node.setOnMouseReleased(me -> node.getScene().setCursor(Cursor.HAND) );

        // Prevent mouse clicks on img from propagating to the pane and
        // resulting in creation of a new image
        node.setOnMouseClicked(me -> me.consume());
    }

    private class Delta {
        public double x;
        public double y;
    }
}


