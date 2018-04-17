package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileCreationPageController extends FrontPageController {

    @FXML
    private Button goBackButton;

    //Controls for User Creation
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ChoiceBox genderBox;
    //Values for Gender choice box
    private ObservableList<String> genderChoice = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private void initialize() {
        genderBox.setItems(genderChoice);
        genderBox.setValue("anything");
    }

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button CreateAccountButton;

    public void pressGoBackButton(ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }

    public void getUserInput() {

        if (firstNameField.getText().isEmpty()) {
            System.out.println("First Name cannot be empty");
        } else if (lastNameField.getText().isEmpty()) {
            System.out.println("Last Name cannot be empty");
        } else if (usernameField.getText().isEmpty()) {
            System.out.println("Username cannot be empty");
        } else if (passwordField.getText().isEmpty()) {
            System.out.println("Password cannot be empty");
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/userData.txt", true)))) {
                out.print(firstNameField.getText() + "\t");
                out.print(lastNameField.getText() + "\t");
                out.print(genderBox.getValue() + "\t");
                out.print(usernameField.getText() + "\t");
                out.print(passwordField.getText() + "\n");

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}



