import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelpWindow {
    @FXML
    VBox help;

    @FXML
    Label helpLabel;

    @FXML 
    Label one;

    @FXML 
    Label two;

    @FXML 
    Label three;
    
    @FXML 
    Label four;

    @FXML 
    Label five;

    public void initialize() {
        help.setAlignment(Pos.BASELINE_LEFT);
        help.setPadding(new Insets(10, 10, 10, 10));


        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.web("0x3b596d"));
       
        helpLabel.setEffect(innerShadow);
        helpLabel.setText("How to Play");
        helpLabel.setTextFill(Color.color(0.5, .8, 1));
        helpLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 55));

        one.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        two.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        three.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        four.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        five.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
    }
}
