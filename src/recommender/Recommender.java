package recommender;

import destination.Destination;
import userProfiles.User;

import java.io.IOException;
import java.util.*;

import static destination.Destination.listOfDestination;

/* In this class, recommendation for a single user will be made, and possible for a group.
* The class is constructed with (an arraylist of every destinations), an arraylist of every User in the training set,
* and a User, which the recommendation will made to*/

public class Recommender {
    private ArrayList<Destination> destinations;
    private ArrayList<User> users;
    private User recommendationUser;

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
    public static HashMap<User, HashMap<Destination, Integer>> destinationMatrixCreator(ArrayList<User> users){
        HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
        Iterator iter = users.iterator();
        while(iter.hasNext()){
            User temp = (User) iter.next();
            HashMap<Destination,Integer> tempUserDestMap = new HashMap<>();
            Destination tempDest;

            int tempIndex = 0;
            try {
                ArrayList<Destination> destinationList = listOfDestination();
                for (Destination aDestinationList : destinationList) {
                    if (aDestinationList.equals(temp.getUsersDestination().get(tempIndex))) {
                        tempDest = (Destination) aDestinationList.clone();
                        tempUserDestMap.put(tempDest, 1);
                        if (tempIndex < (temp.getUsersDestination().size() - 1)) {
                            tempIndex++;
                        }

                    } else {
                        tempDest = (Destination) aDestinationList.clone();
                        tempUserDestMap.put(tempDest, 0);
                    }
                    matrix.put(temp, tempUserDestMap);
                }

            } catch (IOException e) {
                    e.printStackTrace();
            } catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
        }
        return matrix;
    }


    public HashMap<Destination,Integer> currentUserDestination(User currentUser){
        HashMap<Destination,Integer> tempUserDestMap = new HashMap<>();
        Destination tempDest;

        int tempIndex = 0;
        try {
            ArrayList<Destination> destinationList = listOfDestination();
            for (Destination aDestinationList : destinationList) {
                if (aDestinationList.equals(currentUser.getUsersDestination().get(tempIndex))) {
                    tempDest = (Destination) aDestinationList.clone();
                    tempUserDestMap.put(tempDest, 1);
                    if (tempIndex < (currentUser.getUsersDestination().size() - 1)) {
                        tempIndex++;
                    }
                } else {
                    tempDest = (Destination) aDestinationList.clone();
                    tempUserDestMap.put(tempDest, 0);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return tempUserDestMap;

    }

    public void similarityMatrix(HashMap<Destination,Integer> currentDestination,
                                 HashMap<User, HashMap<Destination, Integer>> trainSet){
        HashMap<Destination,Double> similarity = new HashMap<>();
        HashMap<User, HashMap<Destination, Integer>> simi01 = new HashMap<>();

        try {
            ArrayList<Destination> destList = listOfDestination();
        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }

    public double cosineSimilarity(HashMap<Destination,Integer> user01, HashMap<Destination,Integer> user02){
        double totalSum = 0;
        double squareUser01sum = 0;
        double squareUser02sum = 0;
        double result = 0;
        Set entrySet = user01.entrySet();
        Iterator iter = entrySet.iterator();

        while(iter.hasNext()){
            Map.Entry me = (Map.Entry) iter.next();
            totalSum += user01.get(me).intValue() * user02.get(me).intValue();
            squareUser01sum += user01.get(me).intValue() * user01.get(me).intValue();
            squareUser02sum += user02.get(me).intValue() * user02.get(me).intValue();
        }

        result = totalSum/(Math.sqrt(squareUser01sum) * Math.sqrt(squareUser02sum));
        return result;
    }

    public void recommendationForCurrent(){
        
    }


}
