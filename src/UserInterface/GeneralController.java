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
import userProfiles.User;

import java.io.IOException;

public class GeneralController {
    //Method used to change stages
    public void LoadUI(String UI, ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(UI + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, 1600, 900);

        //This line gets the stage information by casting window to a stage.
        //TODO: review stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        //stage.setTitle("Test"); //Give better title
        stage.show();
    }
    //Method to change to to logged in users profile page
    public void loadLoggedInUserProfile (String UI, ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(UI + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProfilePageController controller = loader.getController();
        //Makes call to method in ProfilePageController to show user data from logged in user
        controller.initializeLoggedInUserData(user);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);
        stage.show();
    }

    //Method to create Alert boxes
    public void showAlertBox(Alert.AlertType alertType, String title, String message) {
        Alert userCreationAlert = new Alert(alertType);
        userCreationAlert.setTitle(title);
        userCreationAlert.setHeaderText(null);
        userCreationAlert.setContentText(message);
        userCreationAlert.show();
    }
}
