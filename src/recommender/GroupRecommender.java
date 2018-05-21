package recommender;

import destination.Destination;
import userProfiles.Group;
import userProfiles.User;

import java.util.ArrayList;

public class GroupRecommender {
    private Group groupToRecommend;

    public Group getGroupToRecommend() {
        return groupToRecommend;
    }

    public void setGroupToRecommend(Group groupToRecommend) {
        this.groupToRecommend = groupToRecommend;
    }

    public ArrayList<Destination> groupRecommendationDest(){
        ArrayList<Destination> destForGroup = new ArrayList<>();


        return destForGroup;
    }

}
