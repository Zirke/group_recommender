import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.application.Application.launch;

/*
  Main Application. This class handles navigation and user interface.
*/

public class Main /*extends Application*/ {

    public static void main(String[] args) {

        ArrayList<User> test = null;
        try {
            test = User.listDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < test.size(); ++i) {
            for (int j = 0; j < test.get(i).getUsersDestination().size(); ++j) {
                System.out.println("ID: " + test.get(i).getId() + " City: " + test.get(i).getUsersDestination().get(j).getDestinationName());
            }
        }
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
