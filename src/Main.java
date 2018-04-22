import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
/*
  Main Application. This class handles navigation and user interface.
*/

public class Main extends Application {

    public static void main(String[] args) throws IOException {
        /*
        try {
            System.out.println(listOfCreatedUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList test1 = listOfCreatedUsers();
        HashMap<String, String> test = userHashMap();
        System.out.println(test.size());
        */

        //prints out all the created users in userData.txt

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserInterface/FrontPage.fxml"));
            //Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            Scene scene = new Scene(root,1600, 900);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Test"); //TODO: Remember to change title
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
