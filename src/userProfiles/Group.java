
package userProfiles;

import destination.Destination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Group {
    private String groupID;
    private ArrayList<User> usersInGroup;

    public Group() {
    }

    public Group(String groupID, ArrayList<User> usersInGroup) {
        this.groupID = groupID;
        this.usersInGroup = usersInGroup;
    }

    public String getGroupID() {
        return groupID;
    }

    public ArrayList<User> getUsersInGroup() {
        return usersInGroup;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public void setUsersInGroup(ArrayList<User> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }

    public static ArrayList<Group> listOfCreatedGroups() throws IOException {
        ArrayList<Group> listOfGroups = new ArrayList<>();

        FileReader fr = new FileReader("src/groupData.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String line;

        int totalLine = Destination.linesInFile("src/groupData.txt"); //Bruger linesInFile metoden fra Destination class

        for (int i = 0; i < totalLine; i++) {
            line = bfr.readLine();
            String[] strings = line.split(",");
            Group temp = new Group();
            ArrayList<User> usersInGroup = new ArrayList<>();

            temp.setGroupID(strings[0]);
            for (String usernameID : strings) {
                for (User user : User.listOfCreatedUsers()) {
                    if (usernameID.equals(user.getUsernameID())) {
                        usersInGroup.add(user);
                    }
                }
            }
            temp.setUsersInGroup(usersInGroup);
            listOfGroups.add(temp);
        }
        try {
            bfr.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfGroups;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", usersInGroup=" + usersInGroup +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupID, group.groupID) &&
                Objects.equals(usersInGroup, group.usersInGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID, usersInGroup);
    }
}