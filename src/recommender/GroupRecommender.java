package recommender;

import destination.Destination;
import userProfiles.Group;
import userProfiles.User;

import java.io.IOException;
import java.util.*;

import static userProfiles.User.listDataset;

public class GroupRecommender {
    private Group groupToRecommend;

    public GroupRecommender(Group groupToRecommend) {
        this.groupToRecommend = groupToRecommend;
    }

    public Group getGroupToRecommend() {
        return groupToRecommend;
    }

    //returns recommendations for a group.
    public ArrayList<Destination> groupRecommendationDest() {
        ArrayList<Destination> destForGroup = new ArrayList<>();
        HashMap<User, ArrayList<Destination>> allUserRecommend = new HashMap<>();

        int pos = 0, maxElements = 0;
        ArrayList<User> listofdata = null;
        try {
            listofdata = listDataset();
        } catch (IOException e) {
            System.out.println("Input/Output exception for groupRecommendationDest method");
        }

        for (User i : groupToRecommend.getUsersInGroup()) {
            if (!i.getUsersDestination().isEmpty()) {
                Recommender test = new Recommender(i, listofdata, 8); //k has been chosen to 8, see report for more info
                ArrayList<Destination> recommendationDest = test.recommendationDest();
                allUserRecommend.put(i, recommendationDest);
                if (allUserRecommend.get(i).size() >= maxElements) {
                    maxElements = recommendationDest.size();
                }
            }
        }


        while (pos <= maxElements) {
            for (int i = 0; i < allUserRecommend.keySet().size(); i++) {
                if (!groupToRecommend.getUsersInGroup().get(i).getUsersDestination().isEmpty()) {
                    if (allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).size() > pos) {
                        destForGroup.add(allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).get(pos));
                    }
                }
            }
            pos++;
        }

        //duplicate destinations are removed.
        HashSet<Destination> noDubDest = new HashSet<>();
        Iterator<Destination> iter2 = destForGroup.iterator();
        while (iter2.hasNext()) {
            Destination temp = iter2.next();
            if (!noDubDest.add(temp)) {
                iter2.remove();
            }
        }

        return destForGroup;
    }
}