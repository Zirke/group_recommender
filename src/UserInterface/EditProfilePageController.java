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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import static userProfiles.User.listOfCreatedUsers;

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
        editProfileData(loggedInUser);
    }

    public void addSelectedDestinationToListView() {
        selectedDestinations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (!destinationField.getText().isEmpty()) {
            selectedDestinations.getItems().addAll(destinationField.getText());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to add");
        }
    }

    public void removeSelectedDestinationFromListView() {
        if (selectedDestinations.getSelectionModel().getSelectedItems() != null) {
            selectedDestinations.getItems().removeAll(selectedDestinations.getSelectionModel().getSelectedItems());
        } else {
            showAlertBox(Alert.AlertType.ERROR, "Error!", "You have not selected any destination to remove");
        }
    }
    private void addSelectedDestinationsToUser(User loggedInUser) throws IOException {
        HashSet<Destination> listOfVisitedDestinations = new HashSet<>();
        listOfVisitedDestinations.addAll(loggedInUser.getUsersDestination());
        loggedInUser.getUsersDestination().clear();

        addDestinationsButton.setOnAction(event -> {
            ArrayList<User> listOfUsers = null;
            try {
                listOfUsers = listOfCreatedUsers();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (destinationField.getText().isEmpty()) {
                showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You have not chosen any destinations to add");
            } else {
                ObservableList selectedDestinationsFromListView = selectedDestinations.getItems();
                for (Object object : selectedDestinationsFromListView) {
                    try {
                        for (Destination foo : Destination.listOfDestination()) {
                            if (object.toString().equals(foo.getDestinationName())) {
                                listOfVisitedDestinations.add(foo);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (User user : listOfUsers) {
                    if (user.getUsernameID().equals(loggedInUser.getUsernameID())) {
                        loggedInUser.getUsersDestination().addAll(listOfVisitedDestinations);
                        user.getUsersDestination().clear();
                        user.getUsersDestination().addAll(listOfVisitedDestinations);
                    }
                }
                overwriteUserData(listOfUsers);
            }
        });
    }

    private void overwriteUserData(ArrayList<User> userList) {
        Path outpath = Paths.get("src/userData.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(outpath)) {
            for (User user : userList) {
                //ArrayList<Destination> foo = user.getUsersDestination();
                writer.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getGender() + "\t" + user.getAge() + "\t" + user.getUsernameID() + "\t" + user.getPassword() + "\t");
                if (!user.getUsersDestination().isEmpty()) {
                    for (Destination dest : user.getUsersDestination()) {
                        writer.write(dest.getDestinationName() + "\t");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void editProfileData(User loggedInUser) {

        Path outpath = Paths.get("src/userData.txt");

        saveDetailsButton.setOnAction(event -> {
            ArrayList<User> listOfUsers = null;
            try {
                listOfUsers = listOfCreatedUsers();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = Files.newBufferedWriter(outpath)) {
                for (User user : listOfUsers) {
                    if (!user.getUsernameID().equals(loggedInUser.getUsernameID())) {
                        writer.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getGender() + "\t" + user.getAge() + "\t" + user.getUsernameID() + "\t" + user.getPassword() + "\t");
                        if (!user.getUsersDestination().isEmpty()) {
                            for (Destination dest : user.getUsersDestination()) {
                                writer.write(dest.getDestinationName() + "\t");
                            }
                        }
                        writer.newLine();
                    } else {
                        writer.write(firstNameField.getText() + "\t" + lastNameField.getText() + "\t" + loggedInUser.getGender() + "\t" + ageField.getText() + "\t" + usernameField.getText() + "\t" + passwordField.getText() + "\t");
                        loggedInUser.setFirstName(firstNameField.getText());
                        loggedInUser.setLastName(lastNameField.getText());
                        loggedInUser.setAge(ageField.getText());
                        loggedInUser.setUsernameID(usernameField.getText());
                        loggedInUser.setPassword(passwordField.getText());
                        HashSet<Destination> hashSet = new HashSet<>();
                        hashSet.addAll(user.getUsersDestination());
                        hashSet.addAll(loggedInUser.getUsersDestination());
                        loggedInUser.getUsersDestination().clear();
                        loggedInUser.getUsersDestination().addAll(hashSet);
                        if (!loggedInUser.getUsersDestination().isEmpty()) {
                            for (Destination dest : loggedInUser.getUsersDestination()) {
                                writer.write(dest.getDestinationName() + "\t");
                            }
                        }
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        });
    }


    public void closeEditProfile() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
