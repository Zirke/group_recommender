import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userProfiles.User;
import java.util.ArrayList;
import static userProfiles.User.*;

public class Main extends Application {

    Stage Main_menu;
    Scene scene_main_menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main_menu = primaryStage;
    }


}
