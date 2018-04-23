package UserInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import userProfiles.User;

public class ProfilePageController extends GeneralController {
    @FXML
    private Button FrontPageButton;
    @FXML
    private Label usernameLabel;

    public void initializeLoggedInUserData (User user) {
        usernameLabel.setText(user.getUsernameID());
    }

    @FXML
    public void pressFrontPageButton (ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }

}
