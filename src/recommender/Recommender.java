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
    Map<User, Map<Destination,Double>> sim;
    //Map<Destination, Map<Destination,Double>> sim;  for the similarity between two destinations

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


    public static HashMap<Destination,Integer> currentUserDestination(User currentUser){
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
    //calculates the cosine similairyt between two users A and B.
    public static double cosineSimilarity(HashMap<Destination,Integer> A, HashMap<Destination,Integer> B){
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;
        double result = 0;
        HashSet<Destination> keySet = new HashSet<>(A.keySet());


        //Dot product, Magnitude for A and Magnitude for B.
        for (Destination i : keySet) {
            dotProduct += A.get(i) * B.get(i);
            magnitudeA += Math.pow(A.get(i), 2);
            magnitudeB += Math.pow(B.get(i), 2);
        }

        result = dotProduct / Math.sqrt(magnitudeA * magnitudeB);
        return result;
    }

    public static Map<User, Double> similarityMatrix(User recommendationUser, HashMap<User, HashMap<Destination, Integer>> destinationData){
        Map<User, Double> similarity = new HashMap<>();
        HashSet<User> keySet = new HashSet<>(destinationData.keySet());
        for(User i : keySet){
            similarity.put(i,cosineSimilarity(destinationData.get(i),currentUserDestination(recommendationUser)));
        }

        return similarity;
    }

    public static HashSet<User> recommendationForCurrent(Map<User, Double> similarityMatrix, int k,
                                                  User recommendationUser, HashMap<User, HashMap<Destination, Integer>> destinationData){
        HashSet<User> userSet = new HashSet<>();
        int userAmount = 0;
        Map<User, Double> similarity = similarityMatrix(recommendationUser, destinationData);
        Set<User> similarityKey = similarity.keySet();
        User temp = new User();

        for(User i : similarityKey){
            if(userAmount == 0) {
                userSet.add(i);
                userAmount++;
                temp = i;
            }else if(0 < userAmount && userAmount < k){
                userSet.add(i);
                userAmount++;
                if(similarity.get(i) < similarity.get(temp)){
                    temp = i;
                }
            }else{
                if(similarity.get(i) > similarity.get(temp)){
                    userSet.remove(temp);
                    temp = i;
                    userSet.add(temp);
                }
            }

        }
        return userSet;
    }
    public static HashSet<Destination> destinationRecommendation(HashSet<User> knnUsers){
        HashSet<Destination> recommendationDest = new HashSet<>();
        Set<Destination> tempUserDest;
        for(User i : knnUsers){
            tempUserDest = new HashSet<>(i.getUsersDestination());
            recommendationDest.addAll(tempUserDest);
        }

        return recommendationDest;
    }

}
