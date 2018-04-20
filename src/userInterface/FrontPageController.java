package userInterface;

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

    //Methods for changing scenes
    public void chooseViewProfile(ActionEvent event) throws IOException {
        Parent Profile_root = FXMLLoader.load(getClass().getResource("userInterface/test01.fxml"));
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Scene profileViewScene = new Scene(Profile_root, visualBounds.getWidth(), visualBounds.getHeight());

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(profileViewScene);
        //stage.setMaximized(true);
        stage.setTitle("test");
        stage.show();
    }
    /*
    public void chooseTest(ActionEvent event) throws IOException {
        Parent Profile_root = FXMLLoader.load(getClass().getResource("test01.fxml"));
        Scene profileViewScene = new Scene(Profile_root);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(profileViewScene);
        stage.setMaximized(true);
        stage.setTitle("test");
        stage.show();
    }
    */

    //Methods for event handeling

    //Top Bar event handeling
    public void pressCreateProfile(ActionEvent event) {
        //System.out.println("This hyperlink works!");
    }

    public void pressSignIn(ActionEvent event) {
        //System.out.println("This hyperlink works too!");
    }

    //Scene changing method used for left menu buttons
    private void LoadUI(String UI) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource(UI + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Left menu button handeling
    public void pressAllDestinations(ActionEvent event) throws IOException {
        Parent AllDestinations_root = FXMLLoader.load(getClass().getResource("userInterface/FrontPageUI_AllDestinations.fxml"));
        Scene AllDestinationScene = new Scene(AllDestinations_root);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(AllDestinationScene);
        stage.setMaximized(true);
        stage.setTitle("Test");
        stage.show();
    }

    public void pressButton1(ActionEvent event) {
        LoadUI("FrontPageUI_UI1");
    }

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


}
