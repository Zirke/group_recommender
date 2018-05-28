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


    public ArrayList<Destination> groupRecommendationDest(){
        ArrayList<Destination> destForGroup = new ArrayList<>();
        HashMap<User, ArrayList<Destination>> allUserRecommend = new HashMap<>();
        //groupMembers.addAll();

        int pos = 0, maxElements = 0;
        Random rand = new Random();
        ArrayList<User> listofdata = null;
        try {
            listofdata = listDataset();
        } catch (IOException e) {
            e.printStackTrace(); // ændre til noget andet
        }


        /*Iterator<User> iter = groupToRecommend.getUsersInGroup().iterator();
        while (iter.hasNext()) {
            User temp = iter.next();
            if (!temp.getUsersDestination().isEmpty()) {
                Recommender test = new Recommender(temp, listofdata, 8);
                ArrayList<Destination> recommendationDest = test.recommendationDest();
                allUserRecommend.put(temp, recommendationDest);
                if (allUserRecommend.get(temp).size() >= maxElements) {
                    maxElements = recommendationDest.size();
                }
            } else {
                groupMembers.remove(temp);
            }

        }*/

        for(User i : groupToRecommend.getUsersInGroup()){
            if(!i.getUsersDestination().isEmpty()) {
                Recommender test = new Recommender(i, listofdata, 8);
                ArrayList<Destination> recommendationDest = test.recommendationDest();
                allUserRecommend.put(i, recommendationDest);
                if (allUserRecommend.get(i).size() >= maxElements) {
                    maxElements = recommendationDest.size();
                }
            }
        }

        //Fejl i tildang til brugers destination.
        while(pos <= maxElements) {
            for (int i = 0; i < allUserRecommend.keySet().size(); i++) {
                if (allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).size() > pos) {
                    destForGroup.add(allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).get(pos));
                }
            }
            pos++;
        }

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