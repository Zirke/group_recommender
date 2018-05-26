package UserInterface;

import destination.Activity;
import destination.Destination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DestinationInformationController {
    @FXML
    private Button closeButton;
    @FXML
    private Label destinationName;
    @FXML
    private Label countryName;
    @FXML
    private Label cityType;
    @FXML
    private VBox activityType, activityName;

    void initializeChosenRecommendation(Destination chosenRecommendation) {
        chosenRecommendation.fillActivities();
        ArrayList<Activity> activities = chosenRecommendation.getActivities();

        destinationName.setText(chosenRecommendation.getDestinationName());
        countryName.setText(chosenRecommendation.getCountryName());
        cityType.setText(chosenRecommendation.getCityType());

        for (Activity activity : activities) {
            Label type = new Label();
            Label name = new Label();

            type.setText((String) activity.getType());
            type.setFont(new Font(16));
            activityType.getChildren().add(type);

            name.setText((String) activity.getName());
            name.setFont(new Font(16));
            activityName.getChildren().add(name);

            //venueArea.setEditable(false);
            //venueArea.appendText(activity.getType() + "\t\t" + activity.getName() + "\n");
        }
    }

    public void closeDestinationInformation() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
