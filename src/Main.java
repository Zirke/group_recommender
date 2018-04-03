import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
  Main Application. This class handles navigation and user interface.
*/

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FrontPage.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true); //sets size of stage to windowed fullscreen
        primaryStage.setTitle("Test");
        primaryStage.show();
    }
}
