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
    private TextField searchForUserField;
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

        fillUsersToRemoveList();
    }

    @FXML
    private void pressCloseButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void addSelectedUserToListView() {
        selectedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Makes it possible to select more users at once
        if (!searchForUserField.getText().isEmpty()) {
            selectedUsersList.getItems().addAll(searchForUserField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to add");
        }
    }

    public void removeSelectedUserFromListView() {
        selectedUsersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (!selectedUsersList.getItems().isEmpty()) {
            selectedUsersList.getItems().removeAll(selectedUsersList.getSelectionModel().getSelectedItems());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to remove");
        }
    }

    private void fillUsersToRemoveList() {
        ArrayList<Group> listOfGroups = new ArrayList<>();
        try {
            listOfGroups = Group.listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Group g : listOfGroups) {
            if (g.getGroupID().equals(this.selectedGroup)) {
                selectedUsersToRemoveList.getItems().addAll(g.getUsersInGroup());
            }
        }
    }

    @FXML
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void editGroupName() {
        String groupID = chosenGroupButton.getText();
        ArrayList<Group> listOfGroups;
        Path outpath = Paths.get("src/groupData.txt");
        {
            try {
                listOfGroups = Group.listOfCreatedGroups();
                BufferedWriter writer = Files.newBufferedWriter(outpath);
                for (Group g : listOfGroups) {
                    if (g.getGroupID().equals(groupID)) {
                        writer.write(NewGroupNameTextField.getText() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    } else {
                        writer.write(g.getGroupID() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    @FXML
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