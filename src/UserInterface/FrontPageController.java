package UserInterface;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FrontPageController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Hyperlink CreateProfileHyperlink;
    @FXML
    private Hyperlink SignInHyperlink;
    @FXML
    private MenuButton ProfileMenuButton;
    @FXML
    private Button NextSceneButton;

    //Left side Vbox buttons (destinations)
    @FXML
    private Button AllDestinationsButton;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;

    //Sign In Hbox
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LogInButton;

    private void LogInAlert(Alert.AlertType alertType, String title, String message) {
        Alert userCreationAlert = new Alert(alertType);
        userCreationAlert.setTitle(title);
        userCreationAlert.setHeaderText(null);
        userCreationAlert.setContentText(message);
        userCreationAlert.show();
    }

    //Method used to change stages
    public void LoadUI(String UI, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UI + ".fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Test"); //Give better title
        stage.show();
    }

    //Laver Arraylist af users om til hashset i stedet med username som keyword.
    public HashMap<String, String> createHashSet(ArrayList<User> ListOfUsers) {
        HashMap<String, String> HashSetUsers = new HashMap<>();
        for (User I : ListOfUsers) {
            HashSetUsers.put(I.getUsernameID(), I.getPassword());
        }
        return HashSetUsers;
    }

    @FXML
    public void userLogin(HashMap<String, String> HashSetUsers, ActionEvent event) {
        Set<String> Usernames = HashSetUsers.keySet();
        for (String i : Usernames) {
            if (i.equals(UsernameField.getText()) && HashSetUsers.get(i).equals(PasswordField.getText())) {
                try {
                    LoadUI("ProfilePage", event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                LogInAlert(Alert.AlertType.ERROR, "Input Error!", "Username or Password is incorrect!");
            }
        }
        /*
        LogInButton.setOnAction(e -> {
            System.out.println("hello world");
            HashMap<String, String> userlogins = new HashMap<>();
            try {
                userlogins = createHashSet(listOfCreatedUsers());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            userLogin(userlogins, e);
        });
        */

        /*
        for (User user : ListOfUsers) {
            if (UsernameField.getText().equals(user.getFirstName())) {

            }
        }
        for (int i = 0; i <= totalLine; i++) {
            if (UsernameField.getText().equals())
        }
        */
    }

    public void pressViewProfile(ActionEvent event) throws IOException {

    }

    //Top Bar event handeling
    public void pressCreateNewProfile(ActionEvent event) throws IOException {
        LoadUI("ProfileCreationPage", event);
    }

    //Left menu button handeling
    public void pressAllDestinations(ActionEvent event) throws IOException {
        LoadUI("AllDestinationsPage", event);
    }

    public void pressShowUsers(ActionEvent event) throws IOException {
        LoadUI("ShowUsersTest", event);
    }
    /*
    public void pressButton3(ActionEvent event) {
        LoadUI("FrontPageUI_UI3");
    }

    public void pressButton4(ActionEvent event) {
        LoadUI("FrontPageUI_UI4");
    }

    public void pressButton5(ActionEvent event) {
        LoadUI("FrontPageUI_UI5");
    }

    public void pressButton6(ActionEvent event) {
        LoadUI("FrontPageUI_UI6");
    }
    */

}
