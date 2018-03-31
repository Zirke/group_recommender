import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontPageController {

    @FXML
    private Hyperlink CreateProfileHyperlink;
    @FXML
    private Hyperlink SignInHyperlink;
    @FXML
    private MenuButton ProfileMenuButton;
    @FXML
    private Button NextSceneButton;


    public void chooseViewProfile(ActionEvent event) throws IOException {
        Parent Profile_root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        Scene profileViewScene = new Scene(Profile_root);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(profileViewScene);
        stage.setMaximized(true);
        stage.setTitle("test2");
        stage.show();
    }

    public void pressCreateProfile(ActionEvent event) {
        System.out.println("This hyperlink works!");
    }

    public void pressSignIn(ActionEvent event) {
        System.out.println("This hyperlink works too!");
    }
}
