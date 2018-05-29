package UserInterface;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import static userProfiles.User.listOfCreatedUsers;

public class GroupCreationPageController extends GeneralController implements Initializable {
    private User loggedInUser;

    void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @FXML
    private TextField writeGroupNameField;
    @FXML
    private TextField searchForUserField;
    @FXML
    private ListView addedUsersList;
    @FXML
    private Button createGroupButton, cancelButton;

    //Makes auto filling search bar for usernames
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> listOfUsersToSearchFor = null;
        try {
            listOfUsersToSearchFor = listOfCreatedUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> users = new ArrayList<>();
        assert listOfUsersToSearchFor != null;
        for (User aListOfUsersToSearchFor : listOfUsersToSearchFor) {
            users.add(aListOfUsersToSearchFor.getUsernameID());
        }
        String[] options = users.toArray(new String[0]);
        TextFields.bindAutoCompletion(searchForUserField, options);
    }

    public void addSelectedUserToListView() {
        addedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Makes it possible to select more users at once
        if (!searchForUserField.getText().isEmpty()) {
            addedUsersList.getItems().addAll(searchForUserField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any User to add");
        }
    }

    //Takes input from text field and ListView to write into groupData.txt
    public void createNewGroupFromUserInput() {
        Stage stage;
        if (writeGroupNameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must give your Travel Group a nameID");
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/groupData.txt", true)))) {
                //HashSet ensures no duplicates of Users.
                HashSet<String> noDuplicates = new HashSet<>();
                for (Object o : addedUsersList.getItems()) {
                    noDuplicates.add(o.toString());
                }
                noDuplicates.add(this.loggedInUser.getUsernameID());
                out.print(writeGroupNameField.getText() + ",");
                //Writes the content of noDuplicates into groupData.txt
                for (String s : noDuplicates) {
                    out.print(s + ",");
                }
                out.print("\n");

                //Confirmation alert box
                showAlertBox(Alert.AlertType.CONFIRMATION, "Success!", "You have successfully created a new Travel Group!");
                stage = (Stage) createGroupButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeSelectedUserFromListView() {
        if (addedUsersList.getSelectionModel().getSelectedItems() != null) {
            addedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            addedUsersList.getItems().removeAll(addedUsersList.getSelectionModel().getSelectedItems());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any users to remove");
        }
    }

    public void closeGroupCreation() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}






