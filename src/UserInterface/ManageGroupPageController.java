package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class ManageGroupPageController {

    @FXML
    private VBox shownGroupsBox;

    // Goes through all the groups and creates a label with each group ID
    void showGroupsForLoggedInUser(User loggedInUser) {
        try {
            ArrayList<Group> listOfGroups = Group.listOfCreatedGroups();
            for (Group group : listOfGroups) {
                for (User user : group.getUsersInGroup()) {
                    if (loggedInUser.getUsernameID().equals(user.getUsernameID())) {
                        Label foo = new Label();
                        foo.setText(group.getGroupID());
                        shownGroupsBox.getChildren().add(foo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
