package UserInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import userProfiles.User;

import java.io.IOException;

import static userProfiles.User.listOfCreatedUsers;

public class FrontPageController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button userCreationButton;
    @FXML
    private Button allDestinationsButton;

    public void initialize() {
    }

    /*
     * This method basically handles the mechanic of switching to other scenes from the Front Page.
     * the instance of LoginManager is passed from LoginManager in the "showFrontPage" method.
     */
    void initManager(final LoginManager loginManager) {
        //Handles action for switching to Profile Creation Page
        userCreationButton.setOnAction(event -> loginManager.showProfileCreationPage());

        //Handles action for switching to All Destinations Page
        allDestinationsButton.setOnAction(event -> loginManager.showAllDestinationsPage());

        //Handles the action for switching to Profile Page
        loginButton.setOnAction(event -> {
            User loggedInUser = userLoginCheck();
            if (loggedInUser != null) {
                loginManager.authenticated(loggedInUser);
            }
        });
    }

    //Checks if the entered username and password corresponds to any created users and returns the selected
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

    public void pressAllDestinations() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AllDestinationsPage.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    // Uses the ArrayList of users to make a HashMap with 'Username' as keyword and 'Password' as value
    private static HashMap<String, String> userHashMap() throws IOException {
        ArrayList<User> inputList = listOfCreatedUsers();
        HashMap<String, String> mapOfUsers = new HashMap<>();
        for (User userFromList : inputList) {
            mapOfUsers.put(userFromList.getUsernameID(), userFromList.getPassword());
        }
        return mapOfUsers;
    }

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        HashMap<String, String> HashSetUsers = userHashMap();
        Set<String> Usernames = HashSetUsers.keySet();
        for (String i : Usernames) {
            if (i.equals(UsernameField.getText()) && HashSetUsers.get(i).equals(PasswordField.getText())) {
                LoadUI("ProfilePage", event);
            } else {
                //showAlertBox(Alert.AlertType.ERROR,"Login Error", "Incorrect Username or Password!");
            }
        }
    }
    */
}
