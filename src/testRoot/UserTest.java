package testRoot;

import org.junit.jupiter.api.Test;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void listDatasetTest() throws IOException {
        ArrayList<User> testList;
        testList = User.listDataset();

        assertEquals("2",testList.get(0).getUsernameID());
        assertEquals(4,testList.get(0).getUsersDestination().size());
        assertEquals("Kawasaki",testList.get(0).getUsersDestination().get(0).getDestinationName());
        assertEquals("Kyoto",testList.get(0).getUsersDestination().get(1).getDestinationName());
        assertEquals("Tokyo",testList.get(0).getUsersDestination().get(2).getDestinationName());
        assertEquals("Yokohama",testList.get(0).getUsersDestination().get(3).getDestinationName());

        assertEquals("3",testList.get(1).getUsernameID());
        assertEquals(5,testList.get(1).getUsersDestination().size());
        assertEquals("Gifu",testList.get(1).getUsersDestination().get(0).getDestinationName());
        assertEquals("Kawasaki",testList.get(1).getUsersDestination().get(1).getDestinationName());
        assertEquals("Nagoya",testList.get(1).getUsersDestination().get(2).getDestinationName());
        assertEquals("Tokyo",testList.get(1).getUsersDestination().get(3).getDestinationName());
        assertEquals("Yokohama",testList.get(1).getUsersDestination().get(4).getDestinationName());

        assertEquals("6",testList.get(4).getUsernameID());
        assertEquals(10,testList.get(4).getUsersDestination().size());
        assertEquals("Baltimore",testList.get(4).getUsersDestination().get(0).getDestinationName());
        assertEquals("Brooklyn",testList.get(4).getUsersDestination().get(1).getDestinationName());
        assertEquals("Chester",testList.get(4).getUsersDestination().get(2).getDestinationName());
        assertEquals("Houston",testList.get(4).getUsersDestination().get(3).getDestinationName());
        assertEquals("Miami",testList.get(4).getUsersDestination().get(4).getDestinationName());
        assertEquals("New York",testList.get(4).getUsersDestination().get(5).getDestinationName());
        assertEquals("Newark",testList.get(4).getUsersDestination().get(6).getDestinationName());
        assertEquals("Philadelphia",testList.get(4).getUsersDestination().get(7).getDestinationName());
        assertEquals("Trenton",testList.get(4).getUsersDestination().get(8).getDestinationName());
        assertEquals("Washington D.C.",testList.get(4).getUsersDestination().get(9).getDestinationName());

    }
}
