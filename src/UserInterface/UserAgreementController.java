package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserAgreementController {
    @FXML
    private TextArea textArea;
    @FXML
    private Button iUnderstandButton;

    @FXML
    void readUserAgreement() {
        Path inpath = Paths.get("src/UserInterface/user_agreement.txt");
        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(inpath))) {
            ArrayList<String> lines = new ArrayList<>();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            //Adds the text from user_agreement.txt to text area
            for (String line : lines) {
                textArea.setEditable(false);
                textArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
    }

    @FXML
    private void pressIUnderstand() {
        Stage stage = (Stage) iUnderstandButton.getScene().getWindow();
        stage.close();
    }
}
