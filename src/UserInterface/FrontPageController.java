package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import userProfiles.User;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FrontPageController extends GeneralController {

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

    private static ArrayList<User> listOfCreatedUsers() throws IOException {
        ArrayList<User> listOfUsers = new ArrayList<>();

        FileReader fr = new FileReader("src/userData.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String line;

        int totalLine = Destination.linesInFile("src/userData.txt"); //Bruger linesInFile metoden fra Destination class

        for (int i = 0; i < totalLine; i++) {
            line = bfr.readLine();
            String[] strings = line.split("\\t", 6);
            User temp = new User();

            temp.setFirstName(strings[0]);
            temp.setLastName(strings[1]);
            temp.setGender(strings[2]);
            temp.setAge(strings[3]);
            temp.setUsernameID(strings[4]);
            temp.setPassword(strings[5]);
            /*
            System.out.println("First Name: " + temp.getFirstName() +
                    "   Last Name:" + temp.getLastName() +
                    "   Gender:" + temp.getGender() +
                    "   Age:" + temp.getAge() +
                    "   Username:" + temp.getUsernameID() +
                    "   Password:" + temp.getPassword());
            */
            listOfUsers.add(temp);
        }
        try {
            bfr.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }


    @FXML
    public void userLoginCheck (ActionEvent event){
        try {
            //Loop goes through all users in the ArrayList of Users
            for (User user : listOfCreatedUsers()) {
                if (user.getUsernameID().equals(UsernameField.getText()) && user.getPassword().equals(PasswordField.getText())) {
                    loadLoggedInUserProfile("ProfilePage", event, user);
                } else {
                    //showAlertBox(Alert.AlertType.ERROR,"Login Error", "Incorrect Username or Password!");
                }
            }
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

    public void pressCreateNewProfile(ActionEvent event) throws IOException {
        LoadUI("ProfileCreationPage", event);
    }

    public void pressAllDestinations(ActionEvent event) throws IOException {
        LoadUI("AllDestinationsPage", event);
    }

    public void pressShowUsers(ActionEvent event) throws IOException {
        LoadUI("ShowUsersTest", event);
    }
}
