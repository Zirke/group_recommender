package testRoot;

import org.junit.jupiter.api.Test;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static userProfiles.User.listOfCreatedUsers;


class UserTest {

    private ArrayList<User> temp = new ArrayList<>();

    @Test
    void listOfCreatedUser01(){
        try {
            temp = listOfCreatedUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(temp.get(0).getUsernameID(),"Simon123");
    }

}
