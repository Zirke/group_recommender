import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userProfiles.Group;

import java.io.IOException;
import java.util.ArrayList;

import static UserInterface.GroupPageController.listOfCreatedGroups;
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

       /* ArrayList<Destination> dest = Destination.listOfDestination();
        for(Destination destination : dest){
            System.out.println(destination.getDestinationName());
        }*/
        ArrayList<Group> test = listOfCreatedGroups();
        for (Group group : test) {
            System.out.println(group.getUsersInGroup().size());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserInterface/FrontPage.fxml"));
            //Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            Scene scene = new Scene(root,1600, 900);

            primaryStage.setScene(scene);
            primaryStage.setTitle("A400b P2");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
