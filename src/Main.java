import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static userProfiles.User.listOfCreatedUsers;

/*
  Main Application. This class handles navigation and user interface.
*/

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);


        try {
            listOfCreatedUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserInterface/FrontPage.fxml"));
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();


            Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Test"); //Remember to change title
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
