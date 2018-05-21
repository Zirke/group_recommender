package testRoot;/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import org.junit.jupiter.api.Test;
import userProfiles.Group;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupTest {

    @Test
    void listOfCreatedGroups01() {
        try {
            ArrayList<Group> listOfCreatedGroups = Group.listOfCreatedGroups();

            assertEquals("Awesomegroup", listOfCreatedGroups.get(0).getGroupID());
            assertEquals("hejsa123group", listOfCreatedGroups.get(1).getGroupID());
            assertEquals("Magaluf2018", listOfCreatedGroups.get(2).getGroupID());

            assertEquals("test", listOfCreatedGroups.get(0).getUsersInGroup().get(0).getUsernameID());
            assertEquals("Cryptbreaker", listOfCreatedGroups.get(0).getUsersInGroup().get(1).getUsernameID());

            assertEquals("Cryptbreaker", listOfCreatedGroups.get(1).getUsersInGroup().get(0).getUsernameID());
            assertEquals("flotfyr14", listOfCreatedGroups.get(1).getUsersInGroup().get(1).getUsernameID());
            assertEquals("usernamewastaken", listOfCreatedGroups.get(1).getUsersInGroup().get(2).getUsernameID());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
