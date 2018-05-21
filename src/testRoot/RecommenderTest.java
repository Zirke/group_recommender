package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import recommender.Recommender;
import userProfiles.User;

import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static recommender.Recommender.*;

class RecommenderTest {
    private final ArrayList<User> testUser = new ArrayList<>();
    private User testRecommendUser = new User();
    private ArrayList<Destination> usersDestination = new ArrayList<>();
    private HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
    private Recommender testRecommend = new Recommender(testRecommendUser);

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
        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        testUser.add(new User("52433",usersDestination));
    }
    private User recommendationUserTest(){
        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        User temp = new User("46",usersDestination);
        return temp;
    }

    @Test
    void destinationMatrixCreator01(){
        BeforeEachMatrixCreator();
        matrix = testRecommend.destinationMatrixCreator(testUser);
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
        matrix = testRecommend.destinationMatrixCreator(testUser);
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
        matrix = testRecommend.destinationMatrixCreator(testUser);
        testRecommend.cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345")));
        assertEquals(0.28869,testRecommend.cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345"))),0.01);
    }

    @Test
    void currentUserDest01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        HashMap<Destination,Integer> userDest = testRecommend.currentUserDestination(temp);
        assertEquals(userDest.get(new Destination("Callao")).intValue(),1);
    }

    @Test
    void similarityMatrix01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        Map<User, Double> similarity = testRecommend.similarityMatrix(temp,testUser);
        assertEquals(similarity.get(new User("12345")).doubleValue(),1.0);
    }



    @Test
    void recommendationDest01(){
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("124555", destTemp);

        destTemp = new ArrayList<>(); destTemp.addAll(Arrays.asList( new Destination("Manaus")));
        User test01 = new User("124565", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>(); destTemp01.addAll(Arrays.asList(new Destination("Callao"),new Destination("Asuncion")));
        User test02 = new User("124575", destTemp01);

        ArrayList<User> trainTest = new ArrayList<>(); trainTest.addAll(Arrays.asList(test01,test02));
        ArrayList<Destination> destRecommend = testRecommend.recommendationDest(2,temp,trainTest);

        assertEquals(destRecommend.get(0).getDestinationName(),"Asuncion");
        assertEquals(destRecommend.get(1).getDestinationName(),"Callao");
    }

    @Test
    void recommendationForCurrent01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        HashSet<User> userSet = testRecommend.recommendationForCurrent(1, temp, testUser);
        assertEquals(userSet.size(),1);
    }

    @Test
    void recommendationForCurrent02(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        HashSet<User> userSet = testRecommend.recommendationForCurrent(2, temp, testUser);
        assertEquals(userSet.size(),2);
    }

    @Test
    void recommendationForCurrent03(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        HashSet<User> userSet = testRecommend.recommendationForCurrent(3, temp, testUser);
        assertEquals(userSet.size(),3);
    }

    @Test
    void destinationRecommendation01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        HashSet<Destination> destSet = testRecommend.destinationRecommendation(1, temp, testUser);
        assertTrue(destSet.isEmpty());
    }
}
