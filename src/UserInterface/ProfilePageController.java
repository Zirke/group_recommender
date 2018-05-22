package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfilePageController extends GeneralController implements Initializable {

    @FXML
    //The main pane for the Profile Page
    private AnchorPane rootPane;
    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel;
    @FXML
    private TextField searchField;

    public void initialize() {
    }

    //TODO make .this
    public User initializeLoggedInUserData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        firstnameLabel.setText(loggedInUser.getFirstName());
        lastnameLabel.setText(loggedInUser.getLastName());
        genderLabel.setText(loggedInUser.getGender());
        ageLabel.setText(loggedInUser.getAge());
        return loggedInUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        List<String> dest = new ArrayList<String>();
        try {
            dest = Destination.listDestNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] options = dest.toArray(new String[0]);

        TextFields.bindAutoCompletion(searchField, options);
    }

    @FXML
    public void pressManageTravelGroups() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
