package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfilePageController extends GeneralController implements Initializable {
    private User loggedInUser;

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    //The main pane for the Profile Page
    private AnchorPane rootPane;
    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel;
    @FXML
    private TextField searchField;

    public void initialize() {
    }

    void initializeLoggedInUserData(User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        firstnameLabel.setText(loggedInUser.getFirstName());
        lastnameLabel.setText(loggedInUser.getLastName());
        genderLabel.setText(loggedInUser.getGender());
        ageLabel.setText(loggedInUser.getAge());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    public void openGroupCreation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GroupCreationPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Group Creation");
        stage.showAndWait();
    }
}
