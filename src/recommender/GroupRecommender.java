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
        int pos = 0, maxElements = 0;
        Random rand = new Random();
        ArrayList<User> listofdata = null;
        try {
            listofdata = listDataset();
        } catch (IOException e) {
            e.printStackTrace(); // ændre til noget andet
        }
        for(User i : groupToRecommend.getUsersInGroup()){
            /*try {
                Recommender test = new Recommender(i,2);
                destForGroup.addAll(test.recommendationDest(i,listDataset()));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //dårlig apprach

            Recommender test = new Recommender(i, listofdata, 8);
                ArrayList<Destination> recommendationDest = test.recommendationDest();
                allUserRecommend.put(i,recommendationDest);
                if(allUserRecommend.get(i).size() >= maxElements){
                    maxElements = recommendationDest.size();
                }
        }

        //Fejl i tildang til brugers destination.
        while(pos <= maxElements) {
            for (int i = 0; i < groupToRecommend.getUsersInGroup().size(); i++) {
                if (allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).size() > pos) {
                    destForGroup.add(allUserRecommend.get(groupToRecommend.getUsersInGroup().get(i)).get(pos));
                }
            }
            pos++;
        }
        HashSet<Destination> noDubDest = new HashSet<>();
        Iterator<Destination> iter = destForGroup.iterator();

        while (iter.hasNext()) {
            Destination temp = iter.next();
            if (!noDubDest.add(temp)) {
                iter.remove();
            }
        }


        /*for(Destination i : destForGroup){
            if(!noDubDest.add(i)){
                destForGroup.remove(i);
            }
        }*/
        return destForGroup;
    }
}