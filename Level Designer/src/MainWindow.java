import java.io.File;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Objects;

import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AllObject;
import model.Fish;
import model.Food;
import model.GameLevel;
import model.LevelManager;
import model.Obstacle;
import java.nio.file.*;
import java.util.stream.Stream;



public class MainWindow {
   
   

 




    // put instance of Level Manager in MainWindow
    LevelManager manager=new LevelManager();
    

   
    ArrayList<AllObject> levelObjects = new ArrayList<AllObject>();

    GameLevel currentState=new GameLevel("levelName","/images/ocean.png", 0,false, levelObjects);


    ArrayList<String> fishTypes=new ArrayList<String>();







    // Borrowed idea from https://stackoverflow.com/questions/41079498/javafx-differentiate-between-click-and-click-dragged
    boolean dragging=false;
    //if dragging is true, the person cannot place something else 
    
    
    
    
    Node grabbed; 
    Node selected;
   





    //set on clicks of the buttons to the event handler and then do the if statements 
    // after view hook model up 

    @FXML Pane pane;
    @FXML HBox tools;
    @FXML VBox dropdown;
    @FXML Button flip;
    @FXML Button copy;
    @FXML Button delete;
    @FXML Label LblSelected;
    @FXML VBox Screen;
    
 







   






    private void makeClickable(Node node) {
    
      
      

    
       
            
        node.setOnMouseClicked((me-> {node.getStyleClass().add("current"); 

        if(Objects.isNull(selected)){ 
            //System.out.println("A");
            selected=node;
            //updateSelectedGui();
        }else if(selected!=node){
            if (!Objects.isNull(selected.getUserData())){
            
                //System.out.println("------");
           // System.out.println("B");
            //System.out.println(selected);
            
            selected.getStyleClass().clear();
           // System.out.println("------");
            selected=node;
            //updateSelectedGui();
            //System.out.println(selected);
            node.getStyleClass().add("current");
            }
        }else{
           if(selected.getStyleClass().size()==3){
            selected.getStyleClass().clear();
           }else{
            selected.getStyleClass().add("current");
           }
           


        }
    
            
            
        }));
        
     

  
   
}
   
   
    @FXML 
    void initialize() throws IOException {
         
    
      


     flip.setOnAction((ActionEvent flipevent) -> onFlipClicked(flipevent));
     copy.setOnAction((ActionEvent copyevent) -> onCopyClicked(copyevent));
     delete.setOnAction((ActionEvent delevent) -> onDeleteClicked(delevent));
       
        AudioClip music = new AudioClip(
            Paths.get("music.mp3").toUri().toString());
        
           music.setCycleCount(Timeline.INDEFINITE);


         //UNCOMMENT FOR MUSIC 
      music.play();
       
        

        Image flipimg = new Image("images/flip.jpg");
        ImageView flipview = new ImageView(flipimg);
        flip.setMaxHeight(20);
        flip.setMaxWidth(10);
        flipview.setFitHeight(20);
        flipview.setFitWidth(10);
        flip.setGraphic(flipview);
        Image copyimg = new Image("images/copy.png");
        ImageView copyview = new ImageView(copyimg);
        copy.setMaxHeight(20);
        copy.setMaxWidth(20);
        copyview.setFitHeight(20);
        copyview.setFitWidth(20);
        copy.setGraphic(copyview);
        Image deleteimg = new Image("images/trash.png");
        ImageView deleteview = new ImageView(deleteimg);
        delete.setMaxHeight(20);
        delete.setMaxWidth(10);
        deleteview.setFitHeight(20);
        deleteview.setFitWidth(10);
        delete.setGraphic(deleteview);

        
       
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
        obstacle1.setOnAction((ActionEvent e) -> {onobstacle("Concrete",0,0,false);});
        Button obstacle2=new Button("Rock");
        obstacle2.setOnAction((ActionEvent e) -> {onobstacle("Rock",0,0,false);});
        Obstacles.getChildren().addAll(obstacle1,obstacle2);
       


        VBox FishTypes =new VBox();
    
    
        
            
    
        
        try (Stream<Path> filepath
        = Files.walk(Paths.get("C:/Users/gfund/Documents/GitHub/spring2021-team2/Level Designer/src/images/Fish"))) 
       
   {
      // print the name of directories and files with
      // entire path
     
       filepath.forEach(e->{if(Files.isDirectory(e)==false){
           if(e.getFileName().toString().contains("flip")==false){
           String filenamewithextension=e.getFileName().toString();
           String filenamewithoutextension=filenamewithextension.substring(0,filenamewithextension.lastIndexOf("."));
           String filenamewithnumber=filenamewithoutextension.substring(filenamewithoutextension.lastIndexOf(".png")+4,filenamewithoutextension.length());
           int filenumber;
          
           try{
         
           filenumber=Integer.parseInt(filenamewithnumber.substring(0,1));
           }catch(Exception except){ 
             
            filenumber= Integer.decode("0x"+filenamewithnumber.substring(0,1));
             
           }
         
         
           String filename=filenamewithnumber.substring(1,2).toUpperCase()+filenamewithnumber.substring(2,filenamewithnumber.length()).toLowerCase();
         //  System.out.println(filenumber);
          // System.out.println(filename);
           fishTypes.add(filename);
  
     
          // final int fishfinal=filenumber;
          // final String filenamefinal=filename;
           
           
           
           








       }}});
           
           
       
   }
  
   
   // if no such directory exists throw an exception.
   catch (IOException e) 
   {
       throw new IOException("Directory Not Present!");
   }
   ChoiceBox c = new ChoiceBox(FXCollections.observableArrayList(fishTypes));
   c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
  
   
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
       
        onFish(newValue.intValue(),fishTypes.get(newValue.intValue()),0,0,false);
     
    }
  
  });
    
c.setOnMouseClicked(e->{
   
 c.setValue(0);
 try{
    if(Objects.isNull(c.getValue())==false ){
     
        onFish(fishTypes.indexOf(c.getValue()),String.valueOf(c.getValue()),0,0,false);
   }}catch (Exception except){
    
  
      
  }
});
 
   FishTypes.getChildren().add(c);
   //c.getSelectionModel().selectedIndexProperty().addListener(event->onFish(event));
  



        /*
        Button fishtype1=new Button("Fish Type 1");
        fishtype1.setOnAction((ActionEvent e) -> {onFishType1(e);});
        Button fishtype2=new Button("Fish Type 2");
        fishtype2.setOnAction((ActionEvent e) -> {onFishType2(e);});
        */
        Label  sliderLabel=new Label("");
        Slider slider=new Slider();
       
        slider.setMin(1.0);
       sliderLabel.textProperty().bind(
Bindings.createStringBinding(
() ->( "Number of Enemy Fish: "+ String.valueOf((int) slider.getValue())),slider.valueProperty()));

        
    
        CheckBox bossFish=new CheckBox("Boss Fish");
      

        FishTypes.getChildren().addAll(sliderLabel,slider,bossFish);
       
 
     
        
        TitledPane settpane= new TitledPane("Level Settings",LevelSettings);
        TitledPane obspane = new TitledPane("Obstacles",Obstacles );
    
 VBox Food=new VBox();
 Button food1=new Button("Algae");
 food1.setOnAction((ActionEvent e)->onfood("Algae",0,0,false));

 Button food2=new Button("Kelp");
 food2.setOnAction((ActionEvent e)->onfood("Kelp",0,0,false));
 
 Food.getChildren().addAll(food1,food2);
 
 
 TitledPane foodpane = new TitledPane("Food", Food);
 TitledPane fishpane = new TitledPane("Fish",FishTypes);
 Accordion accordion = new Accordion();
 accordion.getPanes().addAll(settpane,obspane, foodpane, fishpane);

        // This is the accordion of buttons that will be added to the screen
        
        dropdown.getChildren().addAll(accordion);

      

        
 
}

    void onCopyClicked(ActionEvent copyevent) {
        if(Objects.isNull(selected)==false){
            
            ImageView img=(ImageView) selected;
            Image copyImage=img.getImage();
            ImageView duplicate=new ImageView();
            duplicate.setImage(copyImage);
            duplicate.setTranslateX(selected.getTranslateX()+10);
            duplicate.setTranslateY(selected.getTranslateY()+10);
            if(copyImage.getUrl().contains("Fish")){
            duplicate.setFitHeight(100);
            duplicate.setFitWidth(100);
            Fish selectedFish=(Fish)selected.getUserData();
            Fish duplicatefish =new Fish(selectedFish.type,selectedFish.getflipped(),selectedFish.getX(),selectedFish.getY());
            duplicate.setUserData(duplicatefish);
          
           
         

         
           
          makeClickable(duplicate);
          makeDraggable(duplicate);
            pane.getChildren().add(duplicate);


        }
    }
    }


    void onDeleteClicked(ActionEvent delevent) {
        if(Objects.isNull(selected)==false){
            pane.getChildren().remove(selected);
            
        }
        
    }


    void onFlipClicked(ActionEvent e) {
        if(Objects.isNull(selected)==false){
          ImageView img=(ImageView) selected;
        //add if fish logic to put the flip is true 
       
          String filename=img.getImage().getUrl();

          if(filename.contains("flip")==false){
          String filewithoutExtension=filename.substring(0,filename.lastIndexOf(".png"));
      
           Image flippedimage=new Image(filewithoutExtension+"flip.png");
          img.setImage(flippedimage);
           
        }else{
        String filewithoutflip=filename.substring(0,filename.lastIndexOf("flip.png"));
      
           Image flippedimage=new Image(filewithoutflip+".png");
          img.setImage(flippedimage);
           
    }
}
       
    }



  


    void onFish(int filenumber,String filename,int x,int y, boolean b) {
       

         String filenumberString=String.valueOf(filenumber);

        
        if(!dragging|| b){
            if(filenumber>=10){
               filenumberString=Integer.toHexString(filenumber);

            }
      
            Fish fish =new Fish("Fish Type "+String.valueOf(filenumber),false,x,y);
          
            final Image fishImage=new Image(String.format("/images/Fish/Fishhudimage.png%s%s.png",filenumberString,filename));
           
           currentState.getObjects().add(fish);
          
           var img = new ImageView();
           img.setImage(fishImage);
           img.setLayoutX(x);
           img.setLayoutY(y);
           img.setFitHeight(100);
           img.setFitWidth(100);
           
         
           
           
          //img.setPreserveRatio(true);
        
          img.setUserData(fish);
           
          makeClickable(img);
          makeDraggable(img);
          pane.getChildren().add(img);
     
         dragging=true;
       }
       
   
    }
   
    final Image IMG_ROCK= new Image("/images/rock.png");
 
    final Image IMG_CONCRETE= new Image("/images/concrete.png");
    final Image IMG_KELP=new Image("/images/kelp.png");
    final Image IMG_ALGAE=new Image("/images/algae.png");
   
  
        void onobstacle(String type,int x,int y, boolean b){
        ImageView img=new ImageView();
     
        if(!dragging || b){
            Image imgtouse=IMG_ROCK;
            Obstacle obstacle=new Obstacle(type,false,x,y);
            
            currentState.getObjects().add(obstacle);
            if(type.contains("Concrete")){
                 imgtouse= IMG_CONCRETE;
            }else if(type.contains("Rock")){
                imgtouse= IMG_ROCK;

            }
            
            img.setImage(imgtouse);
            img.setPreserveRatio(true);
            img.setLayoutX(x);
            img.setLayoutY(y);
            img.setFitHeight(100);
            img.setFitWidth(100);
            img.setOnMouseReleased(event -> onMouseReleased(event));
            img.setUserData(obstacle);
            makeClickable(img);
            makeDraggable(img);
            pane.getChildren().add(img);
            dragging=true;
        }

    }

    
    void onfood(String type, int x, int y, boolean b){
        ImageView img=new ImageView();
       // Image imgtouse;
        if(!dragging || b){
            Image imgtouse=IMG_KELP;
            Food food=new Food(type,false,x,y);
            
            currentState.getObjects().add(food);
            if(type.contains("Algae")){
                System.out.println("A");
                 imgtouse= IMG_ALGAE;
            }else if(type.contains("Kelp")){
                imgtouse= IMG_KELP;

            }
            
            img.setImage(imgtouse);
            img.setPreserveRatio(true);
            img.setFitHeight(100);
            img.setFitWidth(100);
            img.setOnMouseReleased(event -> onMouseReleased(event));
            img.setUserData(food);
            makeClickable(img);
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
        pane.getChildren().clear();
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
         currentState= manager.load(file.getName());
      
         
     for(int i=0;i<currentState.getObjects().size();i++){

            
             AllObject object=currentState.getObjects().get(i);
             System.out.println(object.getType());
             if(object.getType().contains("Fish")){
                String numberOnly= object.getType().replaceAll("[^0-9]", "");
                 onFish(Integer.parseInt(numberOnly),fishTypes.get(Integer.parseInt(numberOnly)),object.getX(),object.getY(),true);



             }else if(object.getType().contains("Rock")){
                 

               
                onobstacle("Rock",object.getX(),object.getY(),true);



             
            }else if(object.getType().contains("Concrete")){
   
                 

               
                onobstacle("Concrete",object.getX(),object.getY(),true);



             
            }else if(object.getType().contains("Algae")){
                 

               
                onfood("Algae",object.getX(),object.getY(),true);



             
            }else if(object.getType().contains("Kelp")){
                 

               
                onfood("Kelp",object.getX(),object.getY(),true);



             }
            }
             
            
         
            
      
        }catch(Exception exception){
            exception.printStackTrace();
            var alert = new Alert(AlertType.WARNING, "Error with Loading!");
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
     Slider enemyFish =(Slider) fishvbox.getChildrenUnmodifiable().get(2);
     CheckBox bossFish =(CheckBox) fishvbox.getChildrenUnmodifiable().get(3);
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
            if(node.getLayoutX()<750 && node.getLayoutY()<450 && node.getLayoutY()>-20 && node.getLayoutX()>0 ){
            
            node.setLayoutX(node.getLayoutX() + me.getX() - dragDelta.x);
            node.setLayoutY(node.getLayoutY() + me.getY() - dragDelta.y);
            
           }else{
               
           
            node.setLayoutX(  (int) ((Math.random() * (700 - 10)) +10));
            node.setLayoutY( (int) ((Math.random() * (450 +20)) -20));
            

           }
         
       //   System.out.println(node.getLayoutX());
        //    System.out.println(node.getLayoutY());
           
            AllObject object=(AllObject) node.getUserData();
            object.setX((int)node.getLayoutX());
            object.setY((int)node.getLayoutY());
        });
        node.setOnMouseReleased(me -> onMouseReleased(me));

        // Prevent mouse clicks on img from propagating to the pane and
        // resulting in creation of a new image
        //node.setOnMouseClicked(me -> me.consume());
    }

    private class Delta {
        public double x;
        public double y;
    }


}


