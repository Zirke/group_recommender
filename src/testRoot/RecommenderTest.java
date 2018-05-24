package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import recommender.GroupRecommender;
import recommender.Recommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static recommender.Recommender.*;
import static userProfiles.User.listDataset;

class RecommenderTest {
    private final ArrayList<User> testUser = new ArrayList<>();
    private User testRecommendUser = new User();
    private ArrayList<Destination> usersDestination = new ArrayList<>();
    private HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
    private Recommender testRecommend = new Recommender(testRecommendUser,1);

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
        testRecommend.setTrainSet(testUser);
        matrix = testRecommend.destinationMatrixCreator();
        Destination temp = new Destination("Cuiaba");
        User tempUser = new User("50756");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);
        temp = new Destination("Santiago");
/*        assertEquals(matrix.get(tempUser).get(temp).intValue(),0);
        tempUser = new User("12345");
        temp = new Destination("Cuiaba");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),0);*/
        temp = new Destination("Callao");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }
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
        temp = new Destination("Coquimbo");
        //assertEquals(matrix.get(tempUser).get(temp).intValue(),0);
        temp = new Destination("Manaus");
        assertEquals(matrix.get(tempUser).get(temp).intValue(),1);

    }


    //måske deltaet skal ændres.
    @Test
    void cosineSimilarity01(){
        BeforeEachMatrixCreator();
        testRecommend.setTrainSet(testUser);
        matrix = testRecommend.destinationMatrixCreator();
        testRecommend.cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345")));
        assertEquals(0.28869,testRecommend.cosineSimilarity(matrix.get(new User("50756")),matrix.get(new User("12345"))),0.01);
    }

    @Test
    void currentUserDest01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        testRecommend.setRecommendationUser(temp);
        HashMap<Destination,Integer> userDest = testRecommend.currentUserDestination();
        assertEquals(userDest.get(new Destination("Callao")).intValue(),1);
    }

    @Test
    void similarityMatrix01(){
        BeforeEachMatrixCreator();
        User temp = recommendationUserTest();
        testRecommend.setTrainSet(testUser);
        testRecommend.setRecommendationUser(temp);
        Map<User, Double> similarity = testRecommend.similarityMatrix();
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
        Recommender testRecommend = new Recommender(temp, 2);
        ArrayList<User> trainTest = new ArrayList<>(); trainTest.addAll(Arrays.asList(test01,test02));

        testRecommend.setTrainSet(trainTest);

        testRecommend.setRecommendationUser(temp);
        ArrayList<Destination> destRecommend = testRecommend.recommendationDest();

        assertEquals(destRecommend.get(0).getDestinationName(),"Asuncion");
        assertEquals(destRecommend.get(1).getDestinationName(),"Callao");
    }



   @Test
    void groupTest(){
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("124555", destTemp);

        destTemp = new ArrayList<>(); destTemp.addAll(Arrays.asList( new Destination("Manaus")));
        User test01 = new User("124565", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>(); destTemp01.addAll(Arrays.asList(new Destination("Callao"),new Destination("Asuncion")));
        User test02 = new User("124575", destTemp01);

        ArrayList<User> trainTest = new ArrayList<>(); trainTest.addAll(Arrays.asList(test01,test02));
        Group group = new Group();
        group.setUsersInGroup(trainTest);
        GroupRecommender recom = new GroupRecommender(group);
        if(!recom.groupRecommendationDest().isEmpty()) {
            for (Destination a : recom.groupRecommendationDest()) {
                System.out.println(a.getDestinationName());
            }
        }
    }

    @Test
    void hah(){
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("k", destTemp);

        destTemp = new ArrayList<>(); destTemp.addAll(Arrays.asList( new Destination("Manaus")));
        User test01 = new User("1", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>(); destTemp01.addAll(Arrays.asList(new Destination("Callao"),new Destination("Asuncion")));
        User test02 = new User("h", destTemp01);
        ArrayList<User> trainTest = new ArrayList<>(); trainTest.addAll(Arrays.asList(test01,test02));


        try {
            ArrayList<User> re = listDataset();
            Recommender testRecommend = new Recommender(temp, re,2);
            System.out.println("hh");

            //testRecommend.setRecommendationUser(temp);
            ArrayList<Destination> destRecommend = testRecommend.recommendationDest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void groupTest01(){
        ArrayList<Destination> destTemp = new ArrayList<>();
        destTemp.addAll(Arrays.asList(new Destination("Asuncion"),
                new Destination("Callao"), new Destination("Manaus")));
        User temp = new User("124555", destTemp);

        destTemp = new ArrayList<>(); destTemp.addAll(Arrays.asList( new Destination("Manaus")));
        User test01 = new User("124565", destTemp);
        ArrayList<Destination> destTemp01 = new ArrayList<>(); destTemp01.addAll(Arrays.asList(new Destination("Callao"),new Destination("Asuncion")));
        User test02 = new User("124575", destTemp01);

        ArrayList<User> trainTest = new ArrayList<>(); trainTest.addAll(Arrays.asList(test01,test02, temp));
        Group group = new Group();
        group.setUsersInGroup(trainTest);
        GroupRecommender recom = new GroupRecommender(group);
        if(!recom.groupRecommendationDest().isEmpty()) {
            for (Destination a : recom.groupRecommendationDest()) {
                System.out.println(a.getDestinationName());
            }
        }
    }

    @Test
    void groupTest02(){
        ArrayList<User> dataSet = new ArrayList<>();
        try {
            dataSet = listDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        User testUser = dataSet.get(0);
        System.out.println("User's original:");
        for(Destination i: testUser.getUsersDestination()){
            System.out.println(i.getDestinationName());
        }
        dataSet.remove(testUser);
        System.out.println("\n\n User's recommendation:");
        Recommender test = new Recommender(testUser, dataSet,2);
        for(Destination i: test.recommendationDest()){
            System.out.println(i.getDestinationName());
        }
    }
}
