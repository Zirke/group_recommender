package UserInterface;

import javafx.scene.control.Alert;

class GeneralController {
    //Method to create Alert boxes
    void showAlertBox(Alert.AlertType alertType, String title, String message) {
        Alert userCreationAlert = new Alert(alertType);
        userCreationAlert.setTitle(title);
        userCreationAlert.setHeaderText(null);
        userCreationAlert.setContentText(message);
        userCreationAlert.show();
    }
}
