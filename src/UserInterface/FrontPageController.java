package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class FrontPageController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Hyperlink CreateProfileHyperlink;
    @FXML
    private Hyperlink SignInHyperlink;
    @FXML
    private MenuButton ProfileMenuButton;
    @FXML
    private Button NextSceneButton;

    //Left side Vbox buttons (destinations)
    @FXML
    private Button AllDestinationsButton;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;

    //Method used to change stages
    public void LoadUI(String UI, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UI + ".fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Test"); //Give better title
        stage.show();
    }

    public void pressViewProfile(ActionEvent event) throws IOException {

    }

    //Top Bar event handeling
    public void pressCreateNewProfile(ActionEvent event) throws IOException {
        LoadUI("ProfileCreationPage", event);
    }

    //Left menu button handeling
    public void pressAllDestinations(ActionEvent event) throws IOException {
        LoadUI("FrontPageUI_AllDestinations", event);
    }

    public void pressButton1(ActionEvent event) throws IOException {
        LoadUI("FrontPageUI_UI1", event);
    }

    /*
    public void pressButton2(ActionEvent event) {
        LoadUI("FrontPageUI_UI2");
    }

    public void pressButton3(ActionEvent event) {
        LoadUI("FrontPageUI_UI3");
    }

    public void pressButton4(ActionEvent event) {
        LoadUI("FrontPageUI_UI4");
    }

    public void pressButton5(ActionEvent event) {
        LoadUI("FrontPageUI_UI5");
    }

    public void pressButton6(ActionEvent event) {
        LoadUI("FrontPageUI_UI6");
    }
    */

}
