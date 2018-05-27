package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userProfiles.User;

import java.io.IOException;

import static userProfiles.User.listOfCreatedUsers;

public class FrontPageController {
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Hyperlink userCreationHPL;
    @FXML
    private Button loginButton, userCreationButton, allDestinationsButton;

    public void initialize() {
    }
    /*
     * This method contains the event handlers to switch to other scenes from the Front Page.
     * Each event handler contains a method from the LoginManager class.
     */
    void initManager(final LoginManager loginManager) {
        //Handles action for switching to Profile Creation Page
        userCreationButton.setOnAction(event -> loginManager.showProfileCreationPage());
        userCreationHPL.setOnAction(event -> loginManager.showProfileCreationPage());

        //Handles action for switching to All Destinations Page
        allDestinationsButton.setOnAction(event -> loginManager.showAllDestinationsPage());

        /*
         ' Handles the action for switching to Profile Page.
         * This requires a returned user from the userLoginCheck method.
         * The loggedInUser gets passed through to the LoginManager.
         */
        loginButton.setOnAction(event -> {
            User loggedInUser = userLoginCheck();
            if (loggedInUser != null) {
                loginManager.authenticated(loggedInUser);
            }
        });
        UsernameField.setOnAction(event -> {
            User loggedInUser = userLoginCheck();
            if (loggedInUser != null) {
                loginManager.authenticated(loggedInUser);
            }
        });
        PasswordField.setOnAction(event -> {
            User loggedInUser = userLoginCheck();
            if (loggedInUser != null) {
                loginManager.authenticated(loggedInUser);
            }
        });
    }

    //Checks if the entered username and password corresponds to any created users. Of so returns the selected
    private User userLoginCheck() {
        User loggedInUser = null;
        try {
            for (User user : listOfCreatedUsers()) {
                if (user.getUsernameID().equals(UsernameField.getText()) && user.getPassword().equals(PasswordField.getText())) {
                    //If correspondence is found the user is set as the returned user
                    loggedInUser = user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loggedInUser;
    }
}
