package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllDestinationsPageController extends GeneralController implements Initializable {
    @FXML
    private Button frontPageButton;
    @FXML
    private TextField searchField;

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

    void gotoFrontPage(final LoginManager loginManager) {
        frontPageButton.setOnAction(event -> loginManager.logout());
    }
}
