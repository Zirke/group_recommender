package UserInterface;

import userProfiles.Group;
import userProfiles.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class EditGroupPageController {

    public void editGroupName() {
        String groupID = chosenGroupButton.getText();
        ArrayList<Group> listOfGroups;
        Path outpath = Paths.get("src/groupData.txt");
        {
            try {
                listOfGroups = Group.listOfCreatedGroups();
                BufferedWriter writer = Files.newBufferedWriter(outpath);
                for (Group g : listOfGroups) {
                    if (g.getGroupID().equals(groupID)) {
                        writer.write(NewGroupNameTextField.getText() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    } else {
                        writer.write(g.getGroupID() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addGroupMemeber() {
        String groupID = chosenGroupButton.getText();
        ArrayList<Group> listOfGroups;
        Path outpath = Paths.get("src/groupData.txt");
        {
            try {
                listOfGroups = Group.listOfCreatedGroups();
                BufferedWriter writer = Files.newBufferedWriter(outpath);
                HashSet<User> noDuplicates = new HashSet<>();

                for (Group g : listOfGroups) {
                    if (g.getGroupID().equals(groupID)) {
                        writer.write(groupID + ",");
                        noDuplicates.addAll(g.getUsersInGroup());
                        noDuplicates.addAll(ListViewBois);
                        for (User u : noDuplicates) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    } else {
                        writer.write(g.getGroupID() + ",");
                        for (User u : g.getUsersInGroup()) {
                            writer.write(u.getUsernameID() + ",");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}