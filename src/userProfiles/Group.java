package userProfiles;

import java.util.ArrayList;

public class Group {
    private String groupID;
    private ArrayList<User> usersInGroup;
    //

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public void setUsersInGroup(ArrayList<User> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    public String getGroupID() {
        return groupID;
    }

    public ArrayList<User> getUsersInGroup() {
        return usersInGroup;
    }


}
