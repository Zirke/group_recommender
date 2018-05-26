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
    private VBox activityType, activityName, typespecific, activityAdress;

    void initializeChosenRecommendation(Destination chosenRecommendation) {
        chosenRecommendation.fillActivities();
        ArrayList<Activity> activities = chosenRecommendation.getActivities();

        destinationName.setText(chosenRecommendation.getDestinationName());
        countryName.setText(chosenRecommendation.getCountryName());
        cityType.setText(chosenRecommendation.getCityType());

        for (Activity activity : activities) {
            Label type = new Label();
            Label name = new Label();
            Label specific = new Label();
            Label adress = new Label();

            type.setText((String) activity.getType());
            type.setFont(new Font(14));
            activityType.getChildren().add(type);

            name.setText((String) activity.getName());
            name.setFont(new Font(14));
            activityName.getChildren().add(name);

            specific.setText(activity.getTypeSpecific().toString());
            specific.setFont(new Font(14));
            typespecific.getChildren().add(specific);

            adress.setText(activity.getLocation().toString());
            adress.setFont(new Font(14));
            activityAdress.getChildren().add(adress);
        }
    }

    public void closeDestinationInformation() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
