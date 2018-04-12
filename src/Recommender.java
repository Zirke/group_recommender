import destination.Destination;
import userProfiles.User;
import static destination.Destination.listOfDestination;

import java.io.IOException;
import java.util.*;

/* In this class, recommendation for a single user will be made, and possible for a group.
* The class is constructed with (an arraylist of every destinations), an arraylist of every User in the training set,
* and a User, which the recommendation will made to*/

public class Recommender {
    private ArrayList<Destination> destinations;
    private ArrayList<User> users;
    User recommendationUser;

    HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();  // matrix for whether the user has been to a given destiantion.
    Map<Destination, Map<Destination,Double>> sim;  //for the similarity between two destinations

    public Recommender(User currentUser) {
        this.recommendationUser = currentUser;
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

    //the method creates a matrix for the arrayList "users". if a user has been to a destination then the Integer is 1 otherwise 0.
    public void destinationMatrixCreator(){
        Iterator iter = users.iterator();
        while(iter.hasNext()){
            User temp = (User) iter.next();
            HashMap<Destination,Integer> tempUserDestMap = new HashMap<>();

            int tempIndex = 0;
            try {
                ArrayList<Destination> destinationList = listOfDestination();
                for(int i = 0; i < destinationList.size();i++){
                    if(destinationList.get(i).equals(temp.getUsersDestination().get(tempIndex))){
                        tempUserDestMap.put(destinationList.get(i),1);
                        tempIndex++;
                    }else{
                        tempUserDestMap.put(destinationList.get(i),0);
                    }

                }
                matrix.put(temp,tempUserDestMap);
            } catch (IOException e) {
                    e.printStackTrace();
            }

        }
    }

    public void similarityMatrix(){

    }

    public void recommendationForCurrent(){
        
    }


}
