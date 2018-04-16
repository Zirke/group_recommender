package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import userProfiles.User;

import java.util.ArrayList;
import java.util.HashMap;

import static recommender.Recommender.*;

public class RecommenderTest {
    ArrayList<User> testUser = new ArrayList<>();
    ArrayList<Destination> usersDestination = new ArrayList<>();
    HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();

    public void BeforeEach(){
        usersDestination.add(new Destination("Cuiaba"));
        usersDestination.add(new Destination("Coquimbo"));
        usersDestination.add(new Destination("Rio Branco"));
        testUser.add(new User("50756",usersDestination));
        usersDestination.clear();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        testUser.add(new User("12345",usersDestination));
    }

    @Test
    public void destinationMatrixCreator01(){
        BeforeEach();
        matrix = destinationMatrixCreator(testUser);
        Destination temp = new Destination("Callao");
        User tempUser = new User("50756");
        matrix.get(tempUser).get(temp).intValue();
    }



}
