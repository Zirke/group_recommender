package UserInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.Group;
import userProfiles.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import static userProfiles.User.listOfCreatedUsers;

public class EditGroupPageController extends GeneralController implements Initializable {
    private String selectedGroup;

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    @FXML
    private ListView selectedUsersList, selectedUsersToRemoveList;
    @FXML
    private TextField searchForUserField, groupNameField;
    @FXML
    private Button closeButton, addUsersToGroupButton;


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

    @FXML
    private void pressCloseButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Method for adding user in TextField to ListView
    public void addSelectedUserToListView() {
        selectedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Makes it possible to select more users at once
        if (!searchForUserField.getText().isEmpty()) {
            selectedUsersList.getItems().addAll(searchForUserField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to add");
        }
    }

    //Method to remove selected user(s) in ListView
    public void removeSelectedUserFromListView() {
        selectedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (!selectedUsersList.getItems().isEmpty()) {
            selectedUsersList.getItems().removeAll(selectedUsersList.getSelectionModel().getSelectedItems());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to remove");
        }
    }

    //Setter for groupNameField
    void setGroupNameFieldText(String groupname) {
        groupNameField.setText(groupname);
    }

    //Method to fill ListView of user(s) possible to remove
    void fillUsersToRemoveList(String selectedGroup) {
        ArrayList<Group> listOfGroups = new ArrayList<>();
        try {
            listOfGroups = Group.listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Group g : listOfGroups) {
            if (g.getGroupID().equals(selectedGroup)) {
                for (User u : g.getUsersInGroup())
                    selectedUsersToRemoveList.getItems().add(u.getUsernameID());
            }
        }
    }


    @FXML //Method to add users to a group
    private void addUsersToGroup() {

        Path outpath = Paths.get("src/groupData.txt");
        ArrayList<Group> listOfGroups = new ArrayList<>();
        try {
            listOfGroups = Group.listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter writer = Files.newBufferedWriter(outpath)) {

            HashSet<User> noDuplicates = new HashSet<>();

            for (Group g : listOfGroups) {
                if (g.getGroupID().equals(this.selectedGroup)) {
                    writer.write(this.selectedGroup + ",");
                    noDuplicates.addAll(g.getUsersInGroup());
                    for(Object o : selectedUsersList.getItems()){
                        for(User u : listOfCreatedUsers()){
                            if(o.toString().equals(u.getUsernameID())){
                                noDuplicates.add(u);
                            }
                        }
                    }
                    for (User u : noDuplicates) {
                        writer.write(u.getUsernameID() + ",");
                    }
                    g.getUsersInGroup().clear();
                    g.getUsersInGroup().addAll(noDuplicates);
                    writer.newLine();
                } else {
                    writer.write(g.getGroupID() + ",");
                    for (User u : g.getUsersInGroup()) {
                        writer.write(u.getUsernameID() + ",");
                    }
                    writer.newLine();
                }
            }
            selectedUsersToRemoveList.getItems().clear();
            for (User u : noDuplicates) {
                selectedUsersToRemoveList.getItems().add(u.getUsernameID());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //Method to edit a groups name
    public void editGroupName() {
        ArrayList<Group> listOfGroups = null;
        try {
            listOfGroups = Group.listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path outpath = Paths.get("src/groupData.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(outpath)) {

                for (Group g : listOfGroups) {
                    if (g.getGroupID().equals(this.selectedGroup)) {
                        writer.write(groupNameField.getText() + ",");
                        setSelectedGroup(groupNameField.getText());
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                        writer.newLine();
                    } else {
                        writer.write(g.getGroupID() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @FXML //Method to remove users from a group
    private void removeUserFromGroup(){
        Path outpath = Paths.get("src/groupData.txt");
        ArrayList<Group> listOfGroups = new ArrayList<>();
        try {
            listOfGroups = Group.listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter writer = Files.newBufferedWriter(outpath)) {

            for (Group g : listOfGroups) {
                if (g.getGroupID().equals(this.selectedGroup)) {
                    writer.write(this.selectedGroup + ",");
                    for (Object o : selectedUsersToRemoveList.getSelectionModel().getSelectedItems()) {
                        for(User u : listOfCreatedUsers()){
                            if (o.toString().equals(u.getUsernameID()) && g.getUsersInGroup().contains(u)) {
                                g.getUsersInGroup().remove(u);
                                selectedUsersToRemoveList.getItems().remove(u.getUsernameID());
                            }
                        }
                    }
                    for (User u : g.getUsersInGroup()) {
                        writer.write(u.getUsernameID() + ",");
                    }
                    writer.newLine();
                } else {
                    writer.write(g.getGroupID() + ",");
                    for (User u : g.getUsersInGroup()) {
                        writer.write(u.getUsernameID() + ",");
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}