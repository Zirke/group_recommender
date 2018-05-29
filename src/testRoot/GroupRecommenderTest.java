package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;
import recommender.GroupRecommender;
import recommender.Recommender;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static userProfiles.User.listDataset;

public class GroupRecommenderTest {


    //Test for "groupRecommendationDest()"
    @Test
    void groupRecommendationDest01(){
        ArrayList<User> groupMember = new ArrayList<>();
        ArrayList<Destination> allDest = new ArrayList<>();
        ArrayList<Destination> testUserRecommend = null;
        allDest.addAll(Arrays.asList(new Destination("London"), new Destination("Kyoto")));
        User test = new User("123", allDest);
        groupMember.add(test);
        allDest = new ArrayList<>();
        allDest.addAll(Arrays.asList(new Destination("London"), new Destination("California")));
        User test01 = new User("124", allDest);
        groupMember.add(test01);
        Group testGroup = new Group();
        testGroup.setUsersInGroup(groupMember);
        try {
            testUserRecommend = new Recommender(test, listDataset(),8).recommendationDest();
        } catch (IOException e) {
            System.out.println("test failed");;
        }
        assertEquals(testUserRecommend.get(0), new GroupRecommender(testGroup).groupRecommendationDest().get(0));
    }
}
