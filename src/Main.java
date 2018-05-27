import UserInterface.LoginManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*
  Main Application. This class handles navigation and user interface.
*/

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene primaryScene = new Scene(new AnchorPane());

        //Creates an instance of LoginManager which gets the scene instance to access
        LoginManager loginManager = new LoginManager(primaryScene);
        loginManager.showFrontPage();

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("A400b P2");
        primaryStage.show();
    }
}
