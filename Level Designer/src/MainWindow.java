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
import model.Fish;
import model.Food;
import model.GameLevel;
import model.LevelManager;
import model.Obstacle;


public class MainWindow {
   
   

 




    // put instance of Level Manager in MainWindow
    LevelManager manager=new LevelManager();
    

   
    ArrayList<AllObject> levelObjects = new ArrayList<AllObject>();

    GameLevel currentState=new GameLevel("levelName","/images/ocean.png", 0,false, levelObjects);


    







    // Borrowed idea from https://stackoverflow.com/questions/41079498/javafx-differentiate-between-click-and-click-dragged
    boolean dragging=false;
    //if dragging is true, the person cannot place something else 
    
    
    
    
    Node grabbed; 






    //set on clicks of the buttons to the event handler and then do the if statements 
    // after view hook model up 

    @FXML Pane pane;
    @FXML HBox tools;
    @FXML VBox dropdown;






     /**
     * Unsets the draggable attribute from the critter
     */
   
   
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
    final Image IMG_FISH1 = new Image("/images/fish.png");
    final Image IMG_FISH2 = new Image("/images/fish2.png");
    final Image IMG_ROCK= new Image("/images/rock.png");
    final Image IMG_CONCRETE= new Image("/images/concrete.png");
    final Image IMG_KELP=new Image("/images/kelp.png");
    final Image IMG_ALGAE=new Image("/images/algae.png");
   
  
    @FXML
    void onobstacleType2(ActionEvent e) {
      
       
      
        if(!dragging){
            Obstacle rock=new Obstacle("Rock");
        
            currentState.getObjects().add(rock);
        var img = new ImageView();
        img.setImage(IMG_ROCK);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        img.setUserData(rock);

       // 
        makeDraggable(img);
      
        pane.getChildren().add(img);
       
            dragging=true;
        }

        
    }
    @FXML
    void onobstacleType1(ActionEvent e) {
       
        if(!dragging){
            Obstacle rock=new Obstacle("Concrete");
        
            currentState.getObjects().add(rock);
        var img = new ImageView(IMG_CONCRETE);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        img.setUserData(rock);
        
        makeDraggable(img);
        pane.getChildren().add(img);
      
            dragging=true;
        }
        

    }
    @FXML
     void onfood2(ActionEvent e) {
        
        System.out.println(currentState.getObjects());
        if(!dragging){
        Food food=new Food("Kelp");
        
        currentState.getObjects().add(food);
        var img = new ImageView(IMG_KELP);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        img.setOnMouseReleased(event -> onMouseReleased(event));
        img.setUserData(food);
        
        
        makeDraggable(img);
        pane.getChildren().add(img);
        dragging=true;
        }
  
    }
    @FXML
     void onfood1(ActionEvent e) {
    if(!dragging){
        Food food=new Food("algae");
        
        currentState.getObjects().add(food);
        var img = new ImageView(IMG_ALGAE);
        img.setPreserveRatio(true);
        img.setFitWidth(20);
        img.setOnMouseReleased(event -> onMouseReleased(event));
        img.setUserData(food);
        
        makeDraggable(img);
        pane.getChildren().add(img);
        dragging=true;
    }

    }
    @FXML
    void onFishType1(ActionEvent e) {
        
      if(!dragging){
        Fish fish=new Fish("Type 1");
        
        currentState.getObjects().add(fish);
       
       
        var img = new ImageView(IMG_FISH1);
       img.setPreserveRatio(true);
       img.setFitWidth(20);
       img.setOnMouseReleased(event -> onMouseReleased(event));
       img.setUserData(fish);
        
        
       
       makeDraggable(img);
       pane.getChildren().add(img);
      // makeClickable(img);
      dragging=true;
    }



}

    
    @FXML
    void onFishType2(ActionEvent e) {
        
       
        if(!dragging){
      
         Fish fish =new Fish("Type 2");
        
        currentState.getObjects().add(fish);
       
        var img = new ImageView(IMG_FISH2);
        
      
        
        
       img.setPreserveRatio(true);
       img.setFitWidth(20);
       img.setUserData(fish);
        
       
       makeDraggable(img);
       pane.getChildren().add(img);
  
      dragging=true;
    }
    }

    //Mouse Event Handle'
    @FXML
    void onMouseClicked(MouseEvent value){
        System.out.println("Mouse Clicked");
        if(dragging){
            dragging=false;
        }

    }
    
    
    @FXML
    void onMouseReleased(MouseEvent event) {
      
        System.out.print("M");
       
             
        dragging=false;

}
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
            //check if the image exists
            Image img= new Image(String.format("/images/%s", file.getName()));
            String style=String.format("-fx-background-image: url('/images/%s');", file.getName());
            pane.setStyle(style);
      
        }catch(Exception exception){
            var alert = new Alert(AlertType.WARNING, "Error: This file cannot be set as it cannot be found.");
            alert.setHeaderText(null);
            alert.show();
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
            var alert = new Alert(AlertType.WARNING, "Error: You did not select a file!");
            alert.setHeaderText(null);
            alert.show();
            
        }
    
        
    
    
    

     }
      
    @FXML
     void onSaveFileClicked(ActionEvent e) throws FileNotFoundException {
        System.out.println(pane.getStyle());
        String[] arrOfStr = pane.getStyle().split("'" ,3);
        String imagepath=arrOfStr[1];
        currentState.setLevelPhotoPath(imagepath);
        Accordion accord=(Accordion) dropdown.getChildren().get(0);
        TitledPane levelPane=(TitledPane) accord.getChildrenUnmodifiable().get(0);
        VBox lvlvbox= (VBox) levelPane.getContent();


       TextField levelName =(TextField) lvlvbox.getChildrenUnmodifiable().get(0);
     currentState.setLevelName(levelName.getText());


     TitledPane fishPane=(TitledPane) accord.getChildrenUnmodifiable().get(3);
     VBox fishvbox= (VBox) fishPane.getContent(); 
     Slider enemyFish =(Slider) fishvbox.getChildrenUnmodifiable().get(3);
     CheckBox bossFish =(CheckBox) fishvbox.getChildrenUnmodifiable().get(4);
     currentState.setNumFish((int)enemyFish.getValue());
     currentState.setBossFish(bossFish.isSelected());




       // TextField levelName =(TextField) vbox.getChildrenUnmodifiable().get(0);
        if (currentState.getObjects().size()>0){
        manager.save(currentState);
        }else{
            var alert = new Alert(AlertType.WARNING, "Error: No objects found to save.");
            alert.setHeaderText(null);
            alert.show();
        }
            

        }
       
       

      
        
        
    
    @FXML
    void onNewFileClicked(ActionEvent e) {
        System.out.println("N");
        pane.getChildren().clear();
    
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
            AllObject object=(AllObject) node.getUserData();
            object.setX((int)node.getLayoutX());
            object.setY((int)node.getLayoutY());
        });
        node.setOnMouseReleased(me -> onMouseReleased(me));

        // Prevent mouse clicks on img from propagating to the pane and
        // resulting in creation of a new image
        node.setOnMouseClicked(me -> me.consume());
    }

    private class Delta {
        public double x;
        public double y;
    }
}


