package UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AllDestinationsPageController extends GeneralController {

    @FXML
    private Button HomeButton;

    public void pressHomeButton(ActionEvent event) throws IOException {
        LoadUI("FrontPage", event);
    }


}
