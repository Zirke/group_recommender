package UserInterface;

import destination.Destination;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView Istanbul;
    @FXML
    private ImageView KualaLumpur;
    @FXML
    private ImageView Izmir;
    @FXML
    private ImageView Jakartal;
    @FXML
    private ImageView Tokyo;
    @FXML
    private ImageView Ankara;

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

    public void showSearchedForDestination() {
        ProfilePageController controller = new ProfilePageController();
        try {
            ArrayList<Destination> listOfDestinations = Destination.listOfDestination();
            searchField.setOnAction(event -> {
                for (Destination temp : listOfDestinations) {
                    String enteredDestination = searchField.getText();
                    if (temp.getDestinationName().equals(enteredDestination)) {
                        controller.openDestinationInformation(temp);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void gotoFrontPage(final LoginManager loginManager) {
        frontPageButton.setOnAction(event -> loginManager.logout());
    }
}
