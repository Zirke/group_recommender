package UserInterface;/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import destination.Destination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DestinationInformationController {
    @FXML
    private Button closeButton;
    @FXML
    private Label destinationName;
    @FXML
    private Label countryName;
    @FXML
    private Label cityType;

    public void initializeChosenRecommendation(Destination chosenRecommendation) {
        destinationName.setText(chosenRecommendation.getDestinationName());
        countryName.setText(chosenRecommendation.getCountryName());
        cityType.setText(chosenRecommendation.getCityType());
    }

    public void closeDestinationInformation() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
