package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userProfiles.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FrontPageController extends GeneralController {

    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;

    public static ArrayList<User> listOfCreatedUsers() throws IOException {
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

    //Checks if the entered username and password corresponds to any users and returns the selected
    public User userLoginCheck(ActionEvent event) {
        User loggedInUser = null;
        //Loop goes through all users in the ArrayList of Users
        try {
            for (User user : listOfCreatedUsers()) {
                if (user.getUsernameID().equals(UsernameField.getText()) && user.getPassword().equals(PasswordField.getText())) {
                    loggedInUser = user;
                    loadUserDataToProfilePage("ProfilePage", event, user);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loggedInUser;
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
