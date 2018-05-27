package UserInterface;

import destination.Destination;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditProfilePageController extends GeneralController implements Initializable {
    @FXML
    private TextField destinationField, firstNameField, lastNameField, ageField, usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button closeButton, saveDetailsButton, addDestinationsButton;
    @FXML
    private ListView selectedDestinations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> dest = new ArrayList<>();
        try {
            dest = Destination.listDestNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] options = dest.toArray(new String[0]);

        TextFields.bindAutoCompletion(destinationField, options);
    }

    void initializeUserData(User loggedInUser) {
        firstNameField.setText(loggedInUser.getFirstName());
        lastNameField.setText(loggedInUser.getLastName());
        ageField.setText(loggedInUser.getAge());
        usernameField.setText(loggedInUser.getUsernameID());
        passwordField.setText(loggedInUser.getPassword());

        addSelectedDestinationsToUser(loggedInUser);
    }

    public void addSelectedDestinationToListView() {
        selectedDestinations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Makes it possible to select more users at once
        if (!destinationField.getText().isEmpty()) {
            selectedDestinations.getItems().addAll(destinationField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any User to add");
        }
    }

    public void addSelectedDestinationsToUser(User loggedInUser) {
        addDestinationsButton.setOnAction(event -> {
            if (destinationField.getText().isEmpty()) {
                showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You have not chosen any destinations to add");
            } else {
                ObservableList selectedDestinationsFromListView = selectedDestinations.getItems();
                ArrayList<Destination> listOfSelectedDestinations = new ArrayList<>();

                for (Object object : selectedDestinationsFromListView) {
                    try {
                        for (Destination foo : Destination.listOfDestination()) {
                            if (object.toString().equals(foo.getDestinationName())) {
                                listOfSelectedDestinations.add(foo);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Adding the list of entered destinations to the logged in User
                loggedInUser.getUsersDestination().addAll(listOfSelectedDestinations);

                overwriteUserData();
            }
        });
    }

    private void overwriteUserData() {
        try {
            ArrayList<User> usersInSystem = User.listOfCreatedUsers();
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/userData.txt", false)))) {
                for (User user : usersInSystem) {
                    //ArrayList<Destination> foo = user.getUsersDestination();
                    out.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getGender() + "\t" + user.getAge() + "\t" + user.getUsernameID() + "\t" + user.getPassword());
                    if (!user.getUsersDestination().isEmpty()) {
                        out.write(user.getUsersDestination().get(0).getDestinationName());
                    }
                    /*
                    for (Destination dest : foo) {
                        out.write(dest.getDestinationName() + "\t");
                    }
                    */
                    //out.write(user.getUsersDestination().get(0).getDestinationName() + "\t");

                    out.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeEditProfile() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
