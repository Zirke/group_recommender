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

        first_menu_root.getChildren().addAll(add_leftside());
        first_menu_root.setLeft(add_leftside());

        //Scenes
        scene_main_menu = new Scene (first_menu_root);
        Main_menu.setMaximized(true);
        Main_menu.setScene(scene_main_menu);
        Main_menu.setTitle("Big recommender system A400b (Liz)");
        Main_menu.show();
    }

    private VBox add_leftside() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Test");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Label overview[] = new Label[]{
                new Label("Whatever"),
                new Label("Whatever"),
                new Label("Whatever"),
                new Label("Whatever")
        };

        for (int i = 0; i < 4; i++) {
            VBox.setMargin(overview[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(overview[i]);
        }
        return vbox;
    }
}
