package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import recommender.Recommender;
import userProfiles.User;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommenderTest {
    private final ArrayList<User> testUser = new ArrayList<>();
    private User testRecommendUser = new User();
    private ArrayList<Destination> usersDestination = new ArrayList<>();
    private HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
    private Recommender testRecommend = new Recommender(testRecommendUser, 1);

    //method is called before destinationMatrixCreator method.
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

    //method is used when creating a "recommendationUser".
    private User recommendationUserTest(){
        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Callao"));
        usersDestination.add(new Destination("Manaus"));
        usersDestination.add(new Destination("Asuncion"));
        User temp = new User("46",usersDestination);
        return temp;
    }

    //Test for "destinationMatrixCreator".
    @Test
    void destinationMatrixCreator01(){
        BeforeEachMatrixCreator();
        testRecommend.setTrainSet(testUser);
        matrix = testRecommend.destinationMatrixCreator();
        Destination temp = new Destination("Cuiaba");
        User tempUser = new User("50756");

        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        temp = new Destination("Callao");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }

    //Test for "destinationMatrixCreator".
    @Test
    void destinationMatrixCreator02(){
        BeforeEachMatrixCreator();
        testRecommend.setTrainSet(testUser);
        matrix = testRecommend.destinationMatrixCreator();
        Destination temp = new Destination("Coquimbo");
        User tempUser = new User("50756");
        matrix.get(tempUser).get(temp);

        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        temp = new Destination("Callao");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        tempUser = new User("12345");
        temp = new Destination("Manaus");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }

    //Test for "currentUserDestination()" method.
    @Test
    void currentUserDestination01(){
        testRecommendUser = recommendationUserTest();
        Recommender testRecommend = new Recommender(testRecommendUser, testUser, 1);
        HashMap<Destination, Integer> currentUserDestination = testRecommend.currentUserDestination();

        assertEquals(currentUserDestination.get(new Destination("Callao")).intValue(), 1);
        assertEquals(currentUserDestination.get(new Destination("Manaus")).intValue(), 1);
        assertEquals(currentUserDestination.get(new Destination("Asuncion")).intValue(), 1);
    }

    //Test for "currentUserDestination()" method.
    @Test
    void currentUserDestination02() {
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        testRecommend.setRecommendationUser(temp);
        HashMap<Destination, Integer> userDest = testRecommend.currentUserDestination();

        assertEquals(userDest.get(new Destination("Callao")).intValue(), 1);
    }

    //Test for "cosineSimilarity()" method
    @Test
    void cosineSimilarity01(){
        BeforeEachMatrixCreator();
        testRecommend.setTrainSet(testUser);
        matrix = testRecommend.destinationMatrixCreator();
        testRecommend.cosineSimilarity(matrix.get(new User("50756")), matrix.get(new User("12345")));

        assertEquals(0.28869, testRecommend.cosineSimilarity(matrix.get(new User("50756")), matrix.get(new User("12345"))), 0.01);
    }

    //Test for "similarityMatrix()" method.
    @Test
    void similarityMatrix01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        testRecommend.setTrainSet(testUser);
        testRecommend.setRecommendationUser(temp);
        Map<User, Double> similarity = testRecommend.similarityMatrix();

        assertEquals(similarity.get(new User("12345")).doubleValue(),1.0);
    }

    //Test for "recommendationForCurrent()" method.
    @Test
    void recommendationForCurrent01(){
        BeforeEachMatrixCreator();
        usersDestination = new ArrayList<>();
        usersDestination.add(new Destination("Cuiaba"));
        usersDestination.add(new Destination("Coquimbo"));
        usersDestination.add(new Destination("Rio Branco"));
        usersDestination.add(new Destination("Callao"));
        User test = new User("144",usersDestination);
        Recommender testRecommend = new Recommender(test,testUser,1);
        HashSet<User> kNNUser = testRecommend.recommendationForCurrent();

        assertEquals(kNNUser.size(), 1);
        assertTrue(kNNUser.contains(new User("50756")));

    }

    //Test for "recommendationDest" method.
    @Test
    void recommendationDest01() {
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("124555", destTemp);
        destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Manaus")));
        User test01 = new User("124565", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>();
        destTemp01.addAll(Arrays.asList(new Destination("Callao"), new Destination("Asuncion")));
        User test02 = new User("124575", destTemp01);
        Recommender testRecommend = new Recommender(temp, 2);
        ArrayList<User> trainTest = new ArrayList<>();
        trainTest.addAll(Arrays.asList(test01, test02));
        testRecommend.setTrainSet(trainTest);
        testRecommend.setRecommendationUser(temp);
        ArrayList<Destination> destRecommend = testRecommend.recommendationDest();

        assertTrue(destRecommend.isEmpty());
    }

    //Test for "recommendationDest" method.
    @Test
    void recommendationDest02() {
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("124555", destTemp);
        destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Manaus"), new Destination("London")));
        User test01 = new User("124565", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>();
        destTemp01.addAll(Arrays.asList(new Destination("Callao"), new Destination("Asuncion")));
        User test02 = new User("124575", destTemp01);
        Recommender testRecommend = new Recommender(temp, 2);
        ArrayList<User> trainTest = new ArrayList<>();
        trainTest.addAll(Arrays.asList(test01, test02));
        testRecommend.setTrainSet(trainTest);
        testRecommend.setRecommendationUser(temp);
        ArrayList<Destination> destRecommend = testRecommend.recommendationDest();

        assertEquals(new Destination("London"), destRecommend.get(0));
    }
}
