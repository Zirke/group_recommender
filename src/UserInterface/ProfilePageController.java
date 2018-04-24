package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import userProfiles.User;

import java.io.IOException;

public class ProfilePageController extends GeneralController {
    @FXML
    private Button FrontPageButton, showRecommendationsButton, managetravelGroupsButton;
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

    @FXML
    public void pressMageTravelGroupsButton(ActionEvent event) {
        LoadUI("GroupPage", event);
    }

}
