package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import recommender.Recommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfilePageController extends GeneralController implements Initializable {
    User loggedInUser;

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    //The main pane for the Profile Page
    private AnchorPane rootPane;
    @FXML
    private Label usernameLabel, firstnameLabel, lastnameLabel, genderLabel, ageLabel;
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
    private TextField searchField;
    @FXML
    private Button createNewGroupButton, createGroupButton;
    @FXML
    private VBox groupVbox;

    public void initialize() {
    }

    //TODO make .this
    void initializeLoggedInUserData(final LoginManager loginManager, User loggedInUser) {
        usernameLabel.setText(loggedInUser.getUsernameID());
        firstnameLabel.setText(loggedInUser.getFirstName());
        lastnameLabel.setText(loggedInUser.getLastName());
        genderLabel.setText(loggedInUser.getGender());
        ageLabel.setText(loggedInUser.getAge());
    }

    private ArrayList<Destination> mostPopularDestinations() {
        Path inpath = Paths.get("src\\destination\\mostpopular.txt");
        ArrayList<Destination> destList = new ArrayList<>();
        int j = 0;

        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(inpath))) {

            String currentLine = null;

            while ((currentLine = reader.readLine()) != null && j <= 6) {
                String[] tempArr = currentLine.split("\t");
                String first = tempArr[0];
                int second = Integer.parseInt(tempArr[1]);
                ++j;
                destList.add(new Destination(first));
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
        System.out.println(destList.size());
        return destList;
    }

    void initializeLoggedInUserRecommendations(User loggedInUser) {
        ArrayList<Destination> foo = null;
        if (!loggedInUser.getUsersDestination().isEmpty()) {
            try {
                Recommender hah = new Recommender(loggedInUser, User.listDataset(), 6);
                foo = hah.recommendationDest();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            foo = mostPopularDestinations();
        }
        if (foo.size() > 0) {
            recButton1.setText(foo.get(0).getDestinationName());
        }
        if (foo.size() > 1) {
            recButton2.setText(foo.get(1).getDestinationName());
        }
        if (foo.size() > 2) {
            recButton3.setText(foo.get(2).getDestinationName());
        }
        if (foo.size() > 3) {
            recButton4.setText(foo.get(3).getDestinationName());
        }
        if (foo.size() > 4) {
            recButton5.setText(foo.get(4).getDestinationName());
        }
        if (foo.size() > 5) {
            recButton6.setText(foo.get(5).getDestinationName());
        }
    }

    public void showSelectedRecommendation(ActionEvent event) {
        Button chosenRecBtn = (Button) event.getSource();
        String recID = chosenRecBtn.getText();
        try {
            ArrayList<Destination> listOfDestinations = Destination.listOfDestination();
            for (Destination temp : listOfDestinations) {
                if (temp.getDestinationName().equals(recID)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DestinationInformation.fxml"));
                    Parent root = FXMLLoader.load(getClass().getResource("DestinationInformation.fxml"));
                    DestinationInformationController controller = loader.getController();
                    controller.initializeChosenRecommendation(temp);
                    //System.out.println(foo.toString());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Recommended Destination");
                    stage.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> dest = new ArrayList<String>();
        try {
            dest = Destination.listDestNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] options = dest.toArray(new String[0]);

        TextFields.bindAutoCompletion(searchField, options);
    }

    @FXML
    public void openGroupCreation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GroupCreationPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
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
