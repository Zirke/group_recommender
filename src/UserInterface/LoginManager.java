package UserInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import userProfiles.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {
    private Scene scene;
    private Parent parent;

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    void authenticated(User loggedInUser) {
        showProfilePage(loggedInUser);
    }

    void logout() {
        showFrontPage();
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
            controller.initializeLoggedInUserRecommendations(loggedInUser);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showGroupRecommendationPage(User loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupRecommendationPage.fxml"));
            scene.setRoot(loader.load());
            GroupRecommendationPageController controller = loader.getController();
            controller.initializeData(this, loggedInUser);
            controller.showGroupsForLoggedInUser(loggedInUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void showProfileCreationPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileCreationPage.fxml"));
            scene.setRoot(loader.load());
            ProfileCreationPageController controller = loader.getController();
            controller.gotoFrontPage(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showAllDestinationsPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AllDestinationsPage.fxml"));
            scene.setRoot(loader.load());
            AllDestinationsPageController controller = loader.getController();
            controller.initializeMostPopularDestinations();
            controller.gotoFrontPage(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

