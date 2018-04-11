import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/*
  Main Application. This class handles navigation and user interface.
*/

public class Main /*extends Application*/ {

    public static void main(String[] args) {

        /*try {
            destinationVenues();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //launch(args);
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("userInterface/FrontPage.fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());

        primaryStage.setScene(scene);
       primaryStage.setMaximized(true); //sets size of stage to windowed fullscreen
        primaryStage.setTitle("Test");
        primaryStage.show();
    }*/
}
