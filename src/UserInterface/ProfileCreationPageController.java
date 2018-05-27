package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileCreationPageController extends GeneralController {
    @FXML
    private TextField firstNameField, lastNameField, ageField, usernameField;
    @FXML
    private ChoiceBox genderBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Hyperlink frontPageHPL;
    //Creating and initializing values for Gender choice box
    private ObservableList<String> genderChoice = FXCollections.observableArrayList("Male", "Female", "Neutral");

    @FXML
    private void initialize() {
        genderBox.setItems(genderChoice);
        genderBox.setValue(""); //the string here have to meaning
    }

    //Getting values from all the controllers and writing the data to text file userData.txt
    public void createUserFromInput() {
        //If one of the controllers are empty, an alert box is shown to ensure correct profile creation
        if (firstNameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must enter your First Name");
        } else if (lastNameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must enter your Last Name");
        } else if (genderBox.getSelectionModel().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must choose your Gender");
        } else if (ageField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must enter your Age");
        } else if (usernameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must enter a Username");
        } else if (passwordField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must enter a Password");
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                    "src/userData.txt", true)))) {
                out.print(firstNameField.getText() + "\t");
                out.print(lastNameField.getText() + "\t");
                out.print(genderBox.getValue() + "\t");
                out.print(ageField.getText() + "\t");
                //Ensure that input from age-field is only numbers and not characters
                ageField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        ageField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
                out.print(usernameField.getText() + "\t");
                out.print(passwordField.getText() + "\t");
                out.print("\n");

                //Confirmation alert box when a profile is successfully created
                showAlertBox(Alert.AlertType.CONFIRMATION, "Welcome!",
                        "You have successfully created a profile!\n"
                                + "Sign in to go to your Profile Page");

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    //Opens the User Agreement text file in a pop-up window (a new stage)
    @FXML
    public void pressUserAgreement() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAgreement.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            UserAgreementController controller = loader.getController();
            controller.readUserAgreement();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("User Agreement");
        stage.showAndWait();
    }

    void gotoFrontPage(final LoginManager loginManager) {
        frontPageHPL.setOnAction(event -> loginManager.logout());
    }
}



