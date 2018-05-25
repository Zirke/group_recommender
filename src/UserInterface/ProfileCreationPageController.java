package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ChoiceBox genderBox;
    //Creating and initializing values for Gender choice box
    private ObservableList<String> genderChoice = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private void initialize() {
        genderBox.setItems(genderChoice);
        genderBox.setValue(""); //the string here have to meaning
    }

    @FXML
    private TextField ageField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Hyperlink frontPageHPL;

    public void createUserFromInput(ActionEvent event) {
        //Checking for input
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
            //Writing input from input fields to textfile "userData.txt"
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
                out.print(passwordField.getText() + "\n");

                //Confirmation alert box
                showAlertBox(Alert.AlertType.CONFIRMATION, "Welcome!",
                        "You have successfully created a profile!\n"
                                + "Press Sign In to go to your Profile");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    //Opens the User Agreement pop-up window as a new stage
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



