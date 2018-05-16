package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import userProfiles.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager extends GeneralController {
    private Scene scene;

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    public void authenticated(User loggedInUser) {
        showProfilePage(loggedInUser);
    }

    public void logout(ActionEvent event) {
        //showFrontPage(event);
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("FrontPage.fxml")
            );
            scene.setRoot((Parent) loader.load());
            FrontPageController controller =
                    loader.<FrontPageController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    public void showFrontPage(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
        LoadUI("FrontPage", event);
        FrontPageController controller = loader.<FrontPageController>getController();
    }
    */
    /*
    public void showProfilePage(User loggedInUser) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LoadUI("ProfilePage", event);
        ProfilePageController controller = loader.<ProfilePageController>getController();
        controller.initializeLoggedInUserData(this, loggedInUser);
    }
    */
    private void showProfilePage(User loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            scene.setRoot((Parent) loader.load());
            ProfilePageController controller =
                    loader.<ProfilePageController>getController();
            controller.initializeLoggedInUserData(this, loggedInUser);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

