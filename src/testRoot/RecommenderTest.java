package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import userProfiles.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static recommender.Recommender.*;

class RecommenderTest {
    private final ArrayList<User> testUser = new ArrayList<>();
    private ArrayList<Destination> usersDestination = new ArrayList<>();
    private HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();

    private void BeforeEachMatrixCreator(){
        usersDestination.add(new Destination("Cuiaba"));
        usersDestination.add(new Destination("Coquimbo"));
        usersDestination.add(new Destination("Rio Branco"));
        usersDestination.add(new Destination("Callao"));
        testUser.add(new User("50756",usersDestination));
        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        testUser.add(new User("12345",usersDestination));
    }

    @Test
    void destinationMatrixCreator01(){
        BeforeEachMatrixCreator();
        matrix = destinationMatrixCreator(testUser);
        Destination temp = new Destination("Cuiaba");
        User tempUser = new User("50756");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        temp = new Destination("Santiago");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),0);
        tempUser = new User("12345");
        temp = new Destination("Cuiaba");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),0);
        temp = new Destination("Callao");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }
    @Test
    void destinationMatrixCreator02(){
        BeforeEachMatrixCreator();
        matrix = destinationMatrixCreator(testUser);
        Destination temp = new Destination("Coquimbo");
        User tempUser = new User("50756");
        matrix.get(tempUser).get(temp);
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        temp = new Destination("Callao");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        tempUser = new User("12345");
        temp = new Destination("Coquimbo");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),0);
        temp = new Destination("Manaus");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }


    //måske deltaet skal ændres.
    @Test
    void cosineSimilarity01(){
        BeforeEachMatrixCreator();
        matrix = destinationMatrixCreator(testUser);
        cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345")));
        assertEquals(0.28869,cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345"))),0.01);
    }

    @Test
    void similarityMatrix01(){
        BeforeEachMatrixCreator();
        matrix = destinationMatrixCreator(testUser);

        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        User temp = new User("46",usersDestination);
        Map<User, Double> similarity = similarityMatrix(temp,matrix);
        assertEquals(similarity.get(new User("12345")).doubleValue(),1.0);
    }
}
