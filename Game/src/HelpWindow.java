import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HelpWindow {
    @FXML
    VBox help;

    public void initialize() {
        help.setAlignment(Pos.BASELINE_LEFT);
        help.setPadding(new Insets(10, 10, 10, 10));
    }
}
