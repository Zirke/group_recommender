import destination.Destination;
import userProfiles.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recommender {
    private ArrayList<Destination> destinations;
    private ArrayList<User> users;
    User currentUser;

    Map<User, List<Destination>> matrix;  // matrix for whether the user has been to a given destiantion.
    Map<Destination, Map<Destination,Integer>> sim;  //for the similarity between two destinations

    public Recommender(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Destination> destinations) {
        this.destinations = destinations;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


}
