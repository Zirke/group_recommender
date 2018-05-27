package UserInterface;

import destination.Destination;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import userProfiles.User;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    void initializeUserData(User loggedInUser) throws IOException {
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

    public void addSelectedDestinationsToUser(User loggedInUser) throws IOException {

        ArrayList<User> listOfUsers = User.listOfCreatedUsers();

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
                                loggedInUser.getUsersDestination().add(foo);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(User user : listOfUsers){
                        if(user.getUsernameID().equals(loggedInUser.getUsernameID())){
                            user.getUsersDestination().addAll(loggedInUser.getUsersDestination());
                        }
                    }
                }
                //Adding the list of entered destinations to the logged in User
//                loggedInUser.getUsersDestination().addAll(listOfSelectedDestinations);

                overwriteUserData(listOfUsers);
            }
        });
    }

    private void overwriteUserData(ArrayList<User> userList) {

            Path inpath = Paths.get("src/userData.txt");
            try (BufferedWriter writer = Files.newBufferedWriter(inpath)) {
                for (User user : userList) {
                    //ArrayList<Destination> foo = user.getUsersDestination();
                    writer.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getGender() + "\t" + user.getAge() + "\t" + user.getUsernameID() + "\t" + user.getPassword() + "\t");
                    if (!user.getUsersDestination().isEmpty()) {
                        writer.write(user.getUsersDestination().get(0).getDestinationName());
                    }
                    /*
                    for (Destination dest : foo) {
                        out.write(dest.getDestinationName() + "\t");
                    }
                    */
                    //out.write(user.getUsersDestination().get(0).getDestinationName() + "\t");

                    writer.newLine();
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
