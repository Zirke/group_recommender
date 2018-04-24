package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShowUsersTestController extends FrontPageController {
    @FXML
    private TextArea userDataTextArea;
    @FXML
    private Button showUserDataButton;
    @FXML
    private Button goToFrontPageButton;

    @FXML
    public void showUsersFromFile() {
        try {
            FileReader reader = new FileReader("src/userData.txt");
            BufferedReader br = new BufferedReader(reader);
            userDataTextArea.appendText(br.readLine());
            br.close();
            userDataTextArea.requestFocus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToFrontPage(ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }
}
