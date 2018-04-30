package UserInterface;


import destination.Destination;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import org.controlsfx.control.textfield.TextFields; //VIRKER AF EN ELLER ANDEN GRUND IKKE.
import userProfiles.Group;
import userProfiles.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static UserInterface.FrontPageController.listOfCreatedUsers;


public class GroupPageController extends GeneralController implements Initializable {

    @FXML
    private TextField writeGroupNameField;
    @FXML
    private TextField searchForUserField;
    @FXML
    private ListView addedUsersList;
    @FXML
    private Button createGroupButton;

    //Makes auto filling search bar for Usersnames
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> listOfUsersToSearchFor = null;
        try {
            listOfUsersToSearchFor = listOfCreatedUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> users = new ArrayList<>();
        for (int i = 0; i < listOfUsersToSearchFor.size(); i++) {
            users.add(listOfUsersToSearchFor.get(i).getUsernameID());
        }
        String[] options = users.toArray(new String[0]);
        //VIRKER AF EN ELLER ANDEN GRUND IKKE.
        //TextFields.bindAutoCompletion(searchForUserField, options);
    }

    public void addSelectedUserToListView() {
        addedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Makes it possible to select more users at once
        if (!searchForUserField.getText().isEmpty()) {
            addedUsersList.getItems().addAll(searchForUserField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any User to add");
        }
    }

    public void createNewGroupFromUserInput() {
        if (writeGroupNameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must give your Travel Group a nameID");
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/groupData.txt", true)))) {
                ObservableList selectedUsersFromListView = addedUsersList.getItems();
                out.print(writeGroupNameField.getText() + ",");
                for (Object item : selectedUsersFromListView) {
                    out.print(item + ",");
                }
                out.print("\n");

                //Confirmation alert box
                showAlertBox(Alert.AlertType.CONFIRMATION, "Success!", "You have successfully created a new Travel Group!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO not finished
    public void removeSelectedUserFromListView() {
        ObservableList selectedUsersToRemove = addedUsersList.getSelectionModel().getSelectedItems();
        //addedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //selectedUsersFromListView.removeAll(selectedUsersToRemove);
    }

    public static ArrayList<Group> listOfCreatedGroups() throws IOException {
        ArrayList<User> listOfUsers = listOfCreatedUsers();
        ArrayList<Group> listOfGroups = new ArrayList<>();
        ArrayList<User> tempUsers = new ArrayList<>();
        FileReader fr = new FileReader("src/groupData.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String line;

        int totalLine = Destination.linesInFile("src/groupData.txt"); //Bruger linesInFile metoden fra Destination class

        for (int i = 0; i < totalLine; i++) {
            line = bfr.readLine();
            String[] groupInfo = line.split(",");
            Group temp = new Group();
            temp.setGroupID(groupInfo[0]);

            for (int j = 1; j < groupInfo.length; j++) {
                for (User user : listOfUsers) {
                    if (groupInfo[j].equals(user.getUsernameID())) {
                        tempUsers.add(user);
                    }
                }

            }
            temp.setUsersInGroup(tempUsers);
            tempUsers = new ArrayList<>();
            listOfGroups.add(temp);
        }
        try {
            bfr.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfGroups;
    }
    /*
    public void loadGroupFromTextFile() {
        User loggedInUser = null;
        //Loop goes through all users in the ArrayList of Users
        try {
            for (User user : listOfCreatedUsers()) {
                if (user.getUsernameID().equals(UsernameField.getText()) && user.getPassword().equals(PasswordField.getText())) {
                    loggedInUser = user;
                    loadUserDataToProfilePage("ProfilePage", event, user);
                } else {
                    //showAlertBox(Alert.AlertType.ERROR,"Login Error", "Incorrect Username or Password!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public ArrayList<User> ArrayListOfUsersInGroups() throws IOException {
        ArrayList<Group> listOfGroups = new ArrayList<>();

        FileReader fr = new FileReader("src/groupData.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String line;

        int totalLine = Destination.linesInFile("src/userData.txt"); //Bruger linesInFile metoden fra Destination class

        for (int i = 0; i < totalLine; i++) {
            line = bfr.readLine();
            String[] string = line.split(",");
        }
        return null;
    }
}






