package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import userProfiles.User;

import java.io.IOException;

public class GeneralController {
    //Method used to switch between stages
    public void LoadUI(String fileName, ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fileName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1600, 900);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("A400b P2");
        stage.show();
    }

    //Method to change to logged in users profile page
    public void loadUserDataToProfilePage(String UI, ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(UI + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProfilePageController controllerProfilePage = loader.getController();
        //Makes call to method in ProfilePageController to show user data from logged in user in objects
        controllerProfilePage.initializeLoggedInUserData(user);


        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);
        stage.show();
    }

    public void getGroupPageController(String UI, ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(UI + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupPageController controller = loader.getController();
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
