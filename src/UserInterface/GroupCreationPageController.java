package UserInterface;


import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.ResourceBundle;

import static userProfiles.User.listOfCreatedUsers;

public class GroupCreationPageController extends GeneralController implements Initializable {
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

    public void createNewGroupFromUserInput() {
        Stage stage;
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






