package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfilePageController extends GeneralController implements Initializable {
    User loggedInUser;

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
    @FXML
    private Button createNewGroupButton, createGroupButton;
    @FXML
    private VBox groupVbox;

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
        Parent root = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Group Creation");
        stage.showAndWait();
    }

    // Goes through all the groups and creates a label with each group ID
    void showGroupsForLoggedInUser() {
        try {
            ArrayList<Group> listOfGroups = Group.listOfCreatedGroups();
            for (Group group : listOfGroups) {
                for (User user : group.getUsersInGroup()) {
                    if (loggedInUser.getUsernameID().equals(user.getUsernameID())) {
                        Label foo = new Label();
                        foo.setText(group.getGroupID());
                        groupVbox.getChildren().add(foo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
