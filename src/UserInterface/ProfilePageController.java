package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import recommender.Recommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class ProfilePageController extends GeneralController {
    private User loggedInUser;
    private Parent parent;

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel, recLabel;
    @FXML
    private Button recButton1;
    @FXML
    private Button recButton2;
    @FXML
    private Button recButton3;
    @FXML
    private Button recButton4;
    @FXML
    private Button recButton5;
    @FXML
    private Button recButton6;
    @FXML
    private Button logoutButton;
    @FXML
    private VBox groupVbox;

    public void initialize() {
    }

    //Gets data from the passed user and sets the correct label text
    void initializeLoggedInUserData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        firstnameLabel.setText(loggedInUser.getFirstName());
        lastnameLabel.setText(loggedInUser.getLastName());
        genderLabel.setText(loggedInUser.getGender());
        ageLabel.setText(loggedInUser.getAge());
        //Switces to Front Page
        logoutButton.setOnAction(event -> loginManager.logout());
    }

    void initializeLoggedInUserRecommendations(User loggedInUser) {
        ArrayList<Destination> listOfRecommendedDestinations = null;
        if (!loggedInUser.getUsersDestination().isEmpty()) {
            try {
                Recommender recommender = new Recommender(loggedInUser, User.listDataset(), 6);
                listOfRecommendedDestinations = recommender.recommendationDest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            listOfRecommendedDestinations = Destination.mostPopularDestinations();
            recLabel.setText("We have no detected check-ins on your Profile and therefore\n"
                    + "no valid data to recommend from.\n\n"
                    + "We have instead given you the most popular destinations");
        }
        if (listOfRecommendedDestinations.size() > 0) {
            recButton1.setText(listOfRecommendedDestinations.get(0).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 1) {
            recButton2.setText(listOfRecommendedDestinations.get(1).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 2) {
            recButton3.setText(listOfRecommendedDestinations.get(2).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 3) {
            recButton4.setText(listOfRecommendedDestinations.get(3).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 4) {
            recButton5.setText(listOfRecommendedDestinations.get(4).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 5) {
            recButton6.setText(listOfRecommendedDestinations.get(5).getDestinationName());
        }
    }

    public void showClickedRecommendation(ActionEvent event) {
        Button chosenRecBtn = (Button) event.getSource();
        String recID = chosenRecBtn.getText();
        try {
            ArrayList<Destination> listOfDestinations = Destination.listOfDestination();
            for (Destination temp : listOfDestinations) {
                if (temp.getDestinationName().equals(recID)) {
                    openDestinationInformation(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDestinationInformation(Destination chosenDest) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DestinationInformation.fxml"));
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (parent != null) {
            DestinationInformationController controller = loader.getController();
            controller.initializeChosenRecommendation(chosenDest);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Recommended Destination");
        stage.showAndWait();
    }

    @FXML
    public void openGroupCreation() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GroupCreationPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Group Creation");
        stage.showAndWait();
    }

    // Goes through all the groups and creates a label with each group ID
    void showGroupsForLoggedInUser() {
        try {
            ArrayList<Group> listOfGroups = Group.listOfCreatedGroups();
            for (Group group : listOfGroups) {
                for (User user : group.getUsersInGroup()) {
                    if (loggedInUser.getUsernameID().equals(user.getUsernameID())) {
                        Label foo = new Label();
                        foo.setText(group.getGroupID());
                        groupVbox.getChildren().add(foo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
