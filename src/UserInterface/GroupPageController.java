package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

import static UserInterface.FrontPageController.listOfCreatedUsers;


public class GroupPageController {

    @FXML
    private ListView<User> listOfUsers = new ListView<User>();

    public void addUsersToListView(User user) throws IOException {
        ArrayList<User> list = listOfCreatedUsers();

        for (User user : list) {
            listOfUsers.getItems().add(user);
            ObservableList<String> items = FXCollections.observableArrayList
                    ("Double", "Suite", "Family App");
            listOfUsers.setItems(items);
        }

    }


    public static Group createNewGroup(User user) {

    }
}
