package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditProfilePageController implements Initializable {
    @FXML
    private TextField destinationField, firstNameField, lastNameField, ageField, usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button closeButton, saveDetailsButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> dest = new ArrayList<>();
        try {
            dest = Destination.listDestNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] options = dest.toArray(new String[0]);

        TextFields.bindAutoCompletion(destinationField, options);
    }

    public void initializeUserData(User loggedInUser) {
        firstNameField.setText(loggedInUser.getFirstName());
        lastNameField.setText(loggedInUser.getLastName());
        ageField.setText(loggedInUser.getAge());
        usernameField.setText(loggedInUser.getUsernameID());
        passwordField.setText(loggedInUser.getPassword());
    }


}
