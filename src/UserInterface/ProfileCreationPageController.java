package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileCreationPageController extends FrontPageController {

    @FXML
    private Button HomeButton;

    public void pressHomeButton(ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }

}
