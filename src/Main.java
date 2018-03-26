import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

        //Roots
        BorderPane first_menu_root = new BorderPane();
        //first_menu_root.setLeft(add_leftside());

        //Scenes
        scene_main_menu = new Scene (first_menu_root);



        Main_menu.setMaximized(true);
        Main_menu.setTitle("Big recommender system A400b (Liz)");
        Main_menu.show();

    }

    public VBox add_leftside() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Label overview[] = new Label[]{
                new Label("Sales"),
                new Label("Marketing"),
                new Label("Distribution"),
                new Label("Costs")
        };

        for (int i = 0; i < 4; i++) {
            VBox.setMargin(overview[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(overview[i]);
        }
        return add_leftside();
    }


}
