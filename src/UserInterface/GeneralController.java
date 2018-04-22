package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralController {
    //Method used to change stages
    public void LoadUI(String UI, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UI + ".fxml"));
        //Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, 1600, 900);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        //stage.setTitle("Test"); //Give better title
        stage.show();
    }

    //Method for creating Alert boxes for User Creation
    public void showAlertBox(Alert.AlertType alertType, String title, String message) {
        Alert userCreationAlert = new Alert(alertType);
        userCreationAlert.setTitle(title);
        userCreationAlert.setHeaderText(null);
        userCreationAlert.setContentText(message);
        userCreationAlert.show();
    }
}
