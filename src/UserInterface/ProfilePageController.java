package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import recommender.Recommender;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

public class ProfilePageController extends GeneralController {
    private User loggedInUser;
    private Parent parent;

    void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    private Label usernameLabel, usernameLabel2, firstnameLabel, lastnameLabel, genderLabel, ageLabel;
    @FXML
    private Button recButton1, recButton2, recButton3, recButton4, recButton5, recButton6, recButton7, recButton8;
    @FXML
    private Button logoutButton, groupRecommendationsButton, editProfileButton;

    public void initialize() {
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

    //Gets data from the passed user and sets the correct label text
    void initializeLoggedInUserData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        usernameLabel2.setText(loggedInUser.getUsernameID());
        firstnameLabel.setText(loggedInUser.getFirstName());
        lastnameLabel.setText(loggedInUser.getLastName());
        genderLabel.setText(loggedInUser.getGender());
        ageLabel.setText(loggedInUser.getAge());
        //Switches to Manage Group Page
        groupRecommendationsButton.setOnAction(event -> loginManager.showGroupRecommendationPage(loggedInUser));
        //Switces to Front Page
        logoutButton.setOnAction(event -> loginManager.logout());

        editProfileButton.setOnAction(event -> {
            openEditProfilePage(loggedInUser);
        });
    }

    void initializeLoggedInUserRecommendations(User loggedInUser) {
        ArrayList<Destination> listOfRecommendedDestinations = null;
        //Accesses the stored information about the user to recommend from
        if (!loggedInUser.getUsersDestination().isEmpty()) {
            try {
                Recommender recommender = new Recommender(loggedInUser, User.listDataset(), 8);
                listOfRecommendedDestinations = recommender.recommendationDest();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //If a user has no information (cold-start) the most popular destinations will be shown
        } else {
            listOfRecommendedDestinations = Destination.mostPopularDestinations();
            showAlertBox(Alert.AlertType.INFORMATION, "No data found",
                    "We have no detected check-ins on your Profile and therefore\n"
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
        if (listOfRecommendedDestinations.size() > 6) {
            recButton7.setText(listOfRecommendedDestinations.get(6).getDestinationName());
        }
        if (listOfRecommendedDestinations.size() > 7) {
            recButton8.setText(listOfRecommendedDestinations.get(7).getDestinationName());
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

    void openDestinationInformation(Destination chosenDest) {
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Recommended Destination");
        stage.showAndWait();
    }

    private void openEditProfilePage(User loggedInUser) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfilePage.fxml"));
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (parent != null) {
            EditProfilePageController controller = loader.getController();
            controller.initializeUserData(loggedInUser);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Edit Profile");
        stage.showAndWait();
    }
}
