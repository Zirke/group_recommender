package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

    void authenticated(User loggedInUser) {
        showProfilePage(loggedInUser);
    }

    public void logout(ActionEvent event) {
        //showFrontPage(event);
    }

    public void showFrontPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
            scene.setRoot(loader.load());
            FrontPageController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showProfilePage(User loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            scene.setRoot(loader.load());
            ProfilePageController controller = loader.getController();
            controller.initializeLoggedInUserData(this, loggedInUser);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

