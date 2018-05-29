package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import recommender.GroupRecommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class GroupRecommendationPageController {
    private String chosenGroup;

    public void setChosenGroup(String chosenGroup) {
        this.chosenGroup = chosenGroup;
    }

    @FXML
    private Label usernameLabel, groupIDLabel;
    @FXML
    private VBox shownGroupsBox, groupMemberBox;
    @FXML
    private Button openEditGroupButton, goBackButton, recButton1, recButton2, recButton3, recButton4, recButton5, recButton6, recButton7, recButton8, recButton9, recButton10;

    void initializeData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        goBackButton.setOnAction(event -> loginManager.showProfilePage(loggedInUser));
        openEditGroupButton.setOnAction(this::openEditGroupPage);
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
        openEditGroupButton.setText(groupID);
        setChosenGroup(groupID);
        try {
            ArrayList<Group> listOfCreatedGroups = Group.listOfCreatedGroups();
            for (Group temp : listOfCreatedGroups) {
                if (temp.getGroupID().equals(groupID)) {
                    groupIDLabel.setText(temp.getGroupID());
                    for (User user : temp.getUsersInGroup()) {
                        Label userInGroup = new Label();
                        userInGroup.setText(user.getUsernameID());
                        userInGroup.setFont(new Font(16));
                        groupMemberBox.getChildren().add(userInGroup);
                        showRecommendationsForSelectedGroup(temp);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Input/output exception for showSelectedGroupInformation");;
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
            recButton1.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 1) {
            recButton2.setText(listOfGroupRecommendations.get(1).getDestinationName());
            recButton2.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 2) {
            recButton3.setText(listOfGroupRecommendations.get(2).getDestinationName());
            recButton3.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 3) {
            recButton4.setText(listOfGroupRecommendations.get(3).getDestinationName());
            recButton4.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 4) {
            recButton5.setText(listOfGroupRecommendations.get(4).getDestinationName());
            recButton5.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 5) {
            recButton6.setText(listOfGroupRecommendations.get(5).getDestinationName());
            recButton6.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 6) {
            recButton7.setText(listOfGroupRecommendations.get(6).getDestinationName());
            recButton7.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 7) {
            recButton8.setText(listOfGroupRecommendations.get(7).getDestinationName());
            recButton8.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 8) {
            recButton9.setText(listOfGroupRecommendations.get(8).getDestinationName());
            recButton9.setOnAction(this::showClickedDestination);
        }
        if (listOfGroupRecommendations.size() > 9) {
            recButton10.setText(listOfGroupRecommendations.get(9).getDestinationName());
            recButton10.setOnAction(this::showClickedDestination);
        }
    }

    //Opens the EditGroupPage as a pop-up window in a new Stage
    @FXML
    private void openEditGroupPage(Event event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditGroupPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button openEditGroupButton = (Button) event.getSource();
        String ButtonID = openEditGroupButton.getText();
        EditGroupPageController controller = loader.getController();
        controller.setSelectedGroup(ButtonID);
        controller.fillUsersToRemoveList(ButtonID);
        controller.setGroupNameFieldText(ButtonID);

        if (root != null) {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    //Gets the text value of the clicked button to show Destination Information from
    private void showClickedDestination(Event event) {
        ProfilePageController controller = new ProfilePageController();
        Button chosenDestinationButton = (Button) event.getSource();
        String ButtonID = chosenDestinationButton.getText();
        try {
            ArrayList<Destination> listOfDestinations = Destination.listOfDestination();
            for (Destination temp : listOfDestinations) {
                if (temp.getDestinationName().equals(ButtonID)) {
                    controller.openDestinationInformation(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
