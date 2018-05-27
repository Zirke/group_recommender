package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import recommender.GroupRecommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class GroupRecommendationPageController {
    @FXML
    private Label usernameLabel, groupIDLabel;
    @FXML
    private VBox shownGroupsBox, groupMemberBox;
    @FXML
    private Button goBackButton, recButton1, recButton2, recButton3, recButton4, recButton5, recButton6, recButton7, recButton8, recButton9, recButton10;

    void initializeData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        goBackButton.setOnAction(event -> loginManager.showProfilePage(loggedInUser));
    }

    //For each group the "loggedInUser" is a part of a button with the group's ID is created in the left side menu
    void showGroupsForLoggedInUser(User loggedInUser) {
        try {
            ArrayList<Group> listOfGroups = Group.listOfCreatedGroups();
            for (Group group : listOfGroups) {
                for (User user : group.getUsersInGroup()) {
                    if (loggedInUser.getUsernameID().equals(user.getUsernameID())) {
                        Button foo = new Button();
                        foo.setText(group.getGroupID());
                        //CSS styling created buttons
                        foo.setStyle("-fx-background-color: #8B0000; -fx-font-size: 18; -fx-text-fill: #FFFFFF; -fx-font-weight: bold");
                        //Adds event handler to each of the generated "group buttons"
                        foo.setOnAction(event -> {
                            //removes the group members from vbox if some are already are shown
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

    //Shows the information corresponding to the clicked group button (Group name and members)
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
                        showRecommendationsForSelectedGroup(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *Creates an instance of GroupRecommender for the selected group and finds up to 10 recommendations.
     *Each recommendation gets shown on the predefined buttons on the page.
     */
    private void showRecommendationsForSelectedGroup(Group selectedGroup) {
        GroupRecommender groupRecommender = new GroupRecommender(selectedGroup);
        ArrayList<Destination> listOfGroupRecommendations = groupRecommender.groupRecommendationDest();

        if (listOfGroupRecommendations.size() > 0) {
            recButton1.setText(listOfGroupRecommendations.get(0).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 1) {
            recButton2.setText(listOfGroupRecommendations.get(1).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 2) {
            recButton3.setText(listOfGroupRecommendations.get(2).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 3) {
            recButton4.setText(listOfGroupRecommendations.get(3).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 4) {
            recButton5.setText(listOfGroupRecommendations.get(4).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 5) {
            recButton6.setText(listOfGroupRecommendations.get(5).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 6) {
            recButton7.setText(listOfGroupRecommendations.get(6).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 7) {
            recButton8.setText(listOfGroupRecommendations.get(7).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 8) {
            recButton9.setText(listOfGroupRecommendations.get(8).getDestinationName());
        }
        if (listOfGroupRecommendations.size() > 9) {
            recButton10.setText(listOfGroupRecommendations.get(9).getDestinationName());
        }
    }
}
