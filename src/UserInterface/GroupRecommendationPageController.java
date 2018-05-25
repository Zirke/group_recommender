package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class GroupRecommendationPageController {

    @FXML
    private Label usernameLabel, groupIDLabel;
    @FXML
    private VBox shownGroupsBox;
    @FXML
    private Button goBackButton;
    @FXML
    private VBox groupMemberBox;

    void initializeData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        goBackButton.setOnAction(event -> loginManager.authenticated(loggedInUser));
    }

    // Goes through all the groups and creates a label with each group ID
    void showGroupsForLoggedInUser(User loggedInUser) {
        try {
            ArrayList<Group> listOfGroups = Group.listOfCreatedGroups();
            for (Group group : listOfGroups) {
                for (User user : group.getUsersInGroup()) {
                    if (loggedInUser.getUsernameID().equals(user.getUsernameID())) {
                        Button foo = new Button();
                        foo.setText(group.getGroupID());
                        //CSS styling created buttons
                        foo.setStyle("-fx-background-color: #8B0000; -fx-font-size: 20; -fx-text-fill: #FFFFFF; -fx-font-weight: bold");
                        //Adds event handler to each of the generated "group buttons"
                        foo.setOnAction(event -> {
                                    //removes the added members from vbox if some are already added
                                    if (!groupMemberBox.getChildren().isEmpty()) {
                                        groupMemberBox.getChildren().clear();
                                    }
                                    showSelectedGroupInformation(event);
                                }
                        );

                        shownGroupsBox.getChildren().add(foo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Reads the text of clicked button and shows information about the corresponding group
    private void showSelectedGroupInformation(ActionEvent event) {
        Button chosenGroupButton = (Button) event.getSource();
        String groupID = chosenGroupButton.getText();
        try {
            ArrayList<Group> listOfCreatedGroups = Group.listOfCreatedGroups();
            for (Group temp : listOfCreatedGroups) {
                if (temp.getGroupID().equals(groupID)) {
                    groupIDLabel.setText(temp.getGroupID());
                    for (User user : temp.getUsersInGroup()) {
                        Label userInGroup = new Label();
                        userInGroup.setText(user.getUsernameID());
                        groupMemberBox.getChildren().add(userInGroup);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
