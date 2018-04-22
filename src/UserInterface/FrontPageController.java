package UserInterface;


import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import userProfiles.User;

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

    public static ArrayList<User> listOfCreatedUsers() throws IOException {
        ArrayList<User> listOfUsers = new ArrayList<>();

        FileReader fr = new FileReader("src/userData.txt");
        ;
        BufferedReader bfr = new BufferedReader(fr);
        String line;

        int totalLine = Destination.linesInFile("src/userData.txt");

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

            System.out.println("First Name: " + temp.getFirstName() +
                    "   Last Name:" + temp.getLastName() +
                    "   Gender:" + temp.getGender() +
                    "   Age:" + temp.getAge() +
                    "   Username:" + temp.getUsernameID() +
                    "   Password:" + temp.getPassword());
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

    // Uses the ArrayList of users to make a HashMap with 'Username' as keyword and 'Password' as value
    public static HashMap<String, String> userHashMap() throws IOException {
        ArrayList<User> inputList = listOfCreatedUsers();
        System.out.println(listOfCreatedUsers());
        HashMap<String, String> HashSetUsers = new HashMap<>();
        for (User userFromList : inputList) {
            HashSetUsers.put(userFromList.getUsernameID(), userFromList.getPassword());
        }
        //System.out.println(HashSetUsers.size());
        return HashSetUsers;
    }

    public void userLogin( ActionEvent event) {
        HashMap<String, String> HashSetUsers = null;
        try {
            HashSetUsers = userHashMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(HashSetUsers.size());
        Set<String> Usernames = HashSetUsers.keySet();
        for (String i : Usernames) {
            if (i.equals(UsernameField.getText()) && HashSetUsers.get(i).equals(PasswordField.getText())) {
                try {
                    LoadUI("ProfilePage", event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlertBox(Alert.AlertType.ERROR, "Input Error!", "Username or Password is incorrect!");
            }
        }
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


}




        /*
        for (User user : ListOfUsers) {
            if (UsernameField.getText().equals(user.getFirstName())) {

            }
        }
        for (int i = 0; i <= totalLine; i++) {
            if (UsernameField.getText().equals())
        }
*/