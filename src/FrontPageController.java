import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitMenuButton;

public class FrontPageController {

    @FXML
    private Hyperlink CreateProfileHyperlink;
    @FXML
    private Hyperlink SignInHyperlink;
    @FXML
    private SplitMenuButton ProfileButton;

    public void pressCreateProfile(ActionEvent event) {
        System.out.println("This hyperlink works!");
    }

    public void pressSignIn(ActionEvent event) {
        System.out.println("This hyperlink works too!");
    }
}
