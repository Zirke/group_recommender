import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class FrontPageController {

    @FXML
    private Hyperlink CreateProfileHyperlink;
    @FXML
    private Hyperlink SignInHyperlink;
    @FXML
    private Button ProfileButton;

    public void pressCreateProfile(ActionEvent event) {
        System.out.println("This hyperlink works!");
    }

    public void pressSignIn(ActionEvent event) {
        System.out.println("This hyperlink works too!");
    }
}
