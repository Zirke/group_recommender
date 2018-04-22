package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;

public class ProfilePageController extends GeneralController {
    @FXML
    private Button FrontPageButton;

    @FXML
    public void pressFrontPageButton (ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }

}
