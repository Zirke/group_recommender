package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController extends GeneralController implements Initializable {
    @FXML
    private Button FrontPageButton, showRecommendationsButton, managetravelGroupsButton;
    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel;
    @FXML
    private TextField searchField;

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
    public void pressManageTravelGroups(ActionEvent event) {
        LoadUI("GroupPage", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        String[] options = {"hej", "hello", "fuck"};

        TextFields.bindAutoCompletion(searchField, options);
    }

    @FXML
    public void pressMageTravelGroupsButton(String UI, ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(UI + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupPageController controller = loader.getController();
        //Makes call to method in ProfilePageController to show user data from logged in user in objects
        controller.initialize(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);
        stage.show();

    }

}
