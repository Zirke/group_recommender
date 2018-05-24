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
        Scene scene = new Scene(new AnchorPane());

        //Creates an instance of LoginManager to load the application
        LoginManager loginManager = new LoginManager(scene);
        loginManager.showFrontPage();

        primaryStage.setScene(scene);
        primaryStage.setTitle("A400b P2");
        primaryStage.show();
    }
}
