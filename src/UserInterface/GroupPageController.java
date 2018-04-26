package UserInterface;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

import static UserInterface.FrontPageController.listOfCreatedUsers;

public class GroupPageController extends GeneralController implements Initializable {

    @FXML
    private TextField writeGroupNameField;
    @FXML
    private TextField searchForUserField;
    @FXML
    private Button createGroupButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> listOfUsersToSearchFor = null;
        try {
            listOfUsersToSearchFor = listOfCreatedUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> users = new ArrayList<>();
        for (int i = 0; i < listOfUsersToSearchFor.size(); i++) {
            users.add(listOfUsersToSearchFor.get(i).getUsernameID());
        }
        String[] options = users.toArray(new String[0]);

        TextFields.bindAutoCompletion(searchForUserField, options);
    }

    public void createGroupFromUserInput() {
        if (writeGroupNameField.getText().isEmpty()) {
            showAlertBox(Alert.AlertType.ERROR, "Input Error!", "You must give your Travel Group a nameID");
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/groupData.txt", true)))) {
                out.print(writeGroupNameField.getText() + "\t");
                out.print(searchForUserField.getText() + "\n");

                //Confirmation alert box
                showAlertBox(Alert.AlertType.CONFIRMATION, "Success!", "You have successfully created a new Travel Group!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
