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
    private Button FrontPageButton, showRecommendationsButton, showTravelGroupsButton;
    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel;

    public void initializeLoggedInUserData (User user) {
        usernameLabel.setText(user.getUsernameID());
        firstnameLabel.setText(user.getFirstName());
        lastnameLabel.setText(user.getLastName());
        genderLabel.setText(user.getGender());
        ageLabel.setText(user.getAge());
    }

    @FXML
    public void pressFrontPageButton (ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }

}
