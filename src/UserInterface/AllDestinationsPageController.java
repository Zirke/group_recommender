package UserInterface;

import destination.Destination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label mostPopularLabel1;
    @FXML
    private Label mostPopularLabel2;
    @FXML
    private Label mostPopularLabel3;
    @FXML
    private Label mostPopularLabel4;
    @FXML
    private Label mostPopularLabel5;
    @FXML
    private Label mostPopularLabel6;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> dest = new ArrayList<>();
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

    void initializeMostPopularDestinations() {
        ArrayList<Destination> listOfMostPopularDestinations = Destination.mostPopularDestinations();
        mostPopularLabel1.setText(listOfMostPopularDestinations.get(0).getDestinationName());
        mostPopularLabel2.setText(listOfMostPopularDestinations.get(1).getDestinationName());
        mostPopularLabel3.setText(listOfMostPopularDestinations.get(2).getDestinationName());
        mostPopularLabel4.setText(listOfMostPopularDestinations.get(3).getDestinationName());
        mostPopularLabel5.setText(listOfMostPopularDestinations.get(4).getDestinationName());
        mostPopularLabel6.setText(listOfMostPopularDestinations.get(5).getDestinationName());
    }

    //TODO Correct
    public void showClickedDestination(ActionEvent event) {
        ProfilePageController controller = new ProfilePageController();
        Label chosenDestinationLabel = (Label) event.getSource();
        String labelID = chosenDestinationLabel.getText();
        try {
            ArrayList<Destination> listOfDestinations = Destination.listOfDestination();
            for (Destination temp : listOfDestinations) {
                if (temp.getDestinationName().equals(labelID)) {
                    controller.openDestinationInformation(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void gotoFrontPage(final LoginManager loginManager) {
        frontPageButton.setOnAction(event -> loginManager.logout());
    }
}
