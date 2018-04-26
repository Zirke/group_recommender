package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

import static UserInterface.FrontPageController.listOfCreatedUsers;


public class GroupPageController extends GeneralController {

    private ObservableList<User> usersForList = FXCollections.observableArrayList();
    @FXML
    private ListView<User> listOfUsers = new ListView<User>(usersForList);
    @FXML
    private ListView<User> listOfSelectedUsers = new ListView<User>(usersForList);

    @FXML
    public void showAllUsersInListView() {
        ArrayList<User> list;
        {
            try {
                list = listOfCreatedUsers();
                listOfUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                for (User user : list) {
                    listOfUsers.getItems().addAll(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(User user) {

    }

    public void createGroup() {

    }


}
