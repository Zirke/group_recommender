package UserInterface;

import destination.Destination;
import javafx.event.Event;
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
    private Label mostPopularLabel1, mostPopularLabel2, mostPopularLabel3, mostPopularLabel4, mostPopularLabel5, mostPopularLabel6;
    @FXML
    private Label checkinLabel1, checkinLabel2, checkinLabel3, checkinLabel4, checkinLabel5, checkinLabel6;

    //Makes the search bar for Destinations automatic completing
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
        mostPopularLabel1.setOnMouseClicked(this::showClickedDestination);
        mostPopularLabel2.setText(listOfMostPopularDestinations.get(1).getDestinationName());
        mostPopularLabel2.setOnMouseClicked(this::showClickedDestination);
        mostPopularLabel3.setText(listOfMostPopularDestinations.get(2).getDestinationName());
        mostPopularLabel3.setOnMouseClicked(this::showClickedDestination);
        mostPopularLabel4.setText(listOfMostPopularDestinations.get(3).getDestinationName());
        mostPopularLabel4.setOnMouseClicked(this::showClickedDestination);
        mostPopularLabel5.setText(listOfMostPopularDestinations.get(4).getDestinationName());
        mostPopularLabel5.setOnMouseClicked(this::showClickedDestination);
        mostPopularLabel6.setText(listOfMostPopularDestinations.get(5).getDestinationName());
        mostPopularLabel6.setOnMouseClicked(this::showClickedDestination);

        checkinLabel1.setText("Check-ins " + listOfMostPopularDestinations.get(0).getCheckins());
        checkinLabel2.setText("Check-ins " + listOfMostPopularDestinations.get(1).getCheckins());
        checkinLabel3.setText("Check-ins " + listOfMostPopularDestinations.get(2).getCheckins());
        checkinLabel4.setText("Check-ins " + listOfMostPopularDestinations.get(3).getCheckins());
        checkinLabel5.setText("Check-ins " + listOfMostPopularDestinations.get(4).getCheckins());
        checkinLabel6.setText("Check-ins " + listOfMostPopularDestinations.get(5).getCheckins());
    }

    private void showClickedDestination(Event event) {
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
