package UserInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static userProfiles.User.listOfCreatedUsers;

public class EditGroupPageController implements Initializable {
    @FXML
    private ListView selectedUsersList;
    @FXML
    private TextField searchForUserField;
    @FXML
    private Button selectUserButton, closeButton;

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
    }

    public void addGroupMemeber() {
        String groupID = chosenGroupButton.getText();
        ArrayList<Group> listOfGroups;
        Path outpath = Paths.get("src/groupData.txt");
        {
            try {
                listOfGroups = Group.listOfCreatedGroups();
                BufferedWriter writer = Files.newBufferedWriter(outpath);
                HashSet<User> noDuplicates = new HashSet<>();

                for (Group g : listOfGroups) {
                    if (g.getGroupID().equals(groupID)) {
                        writer.write(groupID + ",");
                        noDuplicates.addAll(g.getUsersInGroup());
                        noDuplicates.addAll(ListViewBois);
                        for (User u : noDuplicates) {
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
    }
    */
}