package testRoot;

import org.junit.jupiter.api.Test;
import userProfiles.Group;

import java.io.IOException;
import java.util.ArrayList;

import static UserInterface.GroupPageController.listOfCreatedGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupPageControllerTest {

    ArrayList<Group> temp = new ArrayList<>();

    @Test
    void listOfCreatedGroups01() {
        try {
            temp = listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(temp.get(0).getGroupID(), "Awesomegroup12");

    }

    @Test
    void listOfCreatedGroups02() {
        try {
            temp = listOfCreatedGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //assertEquals(;
        assertEquals(temp.get(0).getUsersInGroup().get(0).getUsernameID(), "test");
    }
}
