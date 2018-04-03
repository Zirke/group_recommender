import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePageController {
    @FXML
    private Button FrontPageButton;

    public void pressFrontPageButton(ActionEvent event) throws IOException {
        Parent AllDestinations_root = FXMLLoader.load(getClass().getResource("FrontPage.fxml"));
        Scene AllDestinationScene = new Scene(AllDestinations_root);

        //This line gets the stage information by casting window to a stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(AllDestinationScene);
        stage.setFullScreen(true);
        stage.setTitle("Test");
        stage.show();
    }

}
