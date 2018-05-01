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
        assertEquals("Awesomegroup", temp.get(0).getGroupID());
        assertEquals("test", temp.get(0).getUsersInGroup().get(0).getUsernameID());
        assertEquals("Cryptbreaker", temp.get(0).getUsersInGroup().get(1).getUsernameID());

        assertEquals("hejsa123group", temp.get(1).getGroupID());
        assertEquals("Cryptbreaker", temp.get(1).getUsersInGroup().get(0).getUsernameID());
        assertEquals("flotfyr14", temp.get(1).getUsersInGroup().get(1).getUsernameID());
    }
}
