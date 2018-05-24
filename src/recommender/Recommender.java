package recommender;

import destination.Destination;
import userProfiles.User;

import java.io.IOException;
import java.util.*;

import static destination.Destination.listOfDestination;
import static java.util.List.*;

/* In this class, recommendation for a single user will be made, and possible for a group.
 * The class is constructed with (an arraylist of every destinations), an arraylist of every User in the training set,
 * and a User, which the recommendation will made to*/

public class Recommender {
    private ArrayList<Destination> destinations;
    private ArrayList<User> trainSet;
    private User recommendationUser;
    private int k;

    HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();  // matrix for whether the user has been to a given destiantion.
    Map<User, Double> sim; //similarity.
    HashSet<Destination> recommendationForCurrent;
    //Map<Destination, Map<Destination,Double>> sim;  for the similarity between two destinations
    ArrayList<Destination> destinationList;
    {
        try {
            destinationList = listOfDestination();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Recommender(User recommendationUser, int k) {
        this.recommendationUser = recommendationUser;
        this.k = k;
    }

    public Recommender( User recommendationUser, ArrayList<User> trainSet, int k) {
        this.trainSet = trainSet;
        this.recommendationUser = recommendationUser;
        this.k = k;
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Destination> destinations) {
        this.destinations = destinations;
    }

    public ArrayList<User> getTrainSet() {
        return trainSet;
    }

    public void setTrainSet(ArrayList<User> trainSet) {
        this.trainSet = trainSet;
    }

    public User getRecommendationUser() {
        return recommendationUser;
    }

    //Setter for recommenderdationUser.
    public void setRecommendationUser(User recommendationUser) {
        this.recommendationUser = recommendationUser;
    }

    //Getter for matrix.
    public HashMap<User, HashMap<Destination, Integer>> getMatrix() {
        return matrix;
    }

    public Map<User, Double> getSim() {
        return sim;
    }

    public void setSim(Map<User, Double> sim) {
        this.sim = sim;
    }

    public HashSet<Destination> getRecommendationForCurrent() {
        return recommendationForCurrent;
    }

    public void setRecommendationForCurrent(HashSet<Destination> recommendationForCurrent) {
        this.recommendationForCurrent = recommendationForCurrent;
    }

    //Creation of destination for a User ArrayList. The destination a User has been to is represented with 1 and otherwise 0.
    public void userDestinationMapCreation(Destination a, ArrayList<Destination> tempDest, HashMap<Destination, Integer> tempUserDestMap) throws CloneNotSupportedException {
        if (tempDest.contains(a)){
            tempUserDestMap.put((Destination) a.clone(), 1);
        }
    }


    //the method creates a matrix for the arrayList "users". if a user has been to a destination then the Integer is 1 otherwise 0.
    public HashMap<User, HashMap<Destination, Integer>> destinationMatrixCreator() {
        HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
        Iterator iter = trainSet.iterator();;

        while (iter.hasNext()) {
            User temp = (User) iter.next();
            HashMap<Destination, Integer> tempUserDestMap = new HashMap<>();

                for (Destination aDestinationList : temp.getUsersDestination()) {
                    tempUserDestMap.put(aDestinationList, 1);
                    matrix.put(temp, tempUserDestMap);
                }
        }
        return matrix;
    }


    public HashMap<Destination, Integer> currentUserDestination() {
        HashMap<Destination, Integer> tempUserDestMap = new HashMap<>();
        try {
            for (Destination aDestinationList : recommendationUser.getUsersDestination()) {
                userDestinationMapCreation( aDestinationList, destinationList, tempUserDestMap);
                matrix.put(recommendationUser, tempUserDestMap);
            }
        }catch (CloneNotSupportedException e) {
            System.out.println("Clone is not Supported");
        }
        return tempUserDestMap;
    }

    public Map<User, Double> similarityMatrix() {
        HashMap<User, HashMap<Destination, Integer>> destinationData = destinationMatrixCreator();
        Map<User, Double> similarity = new HashMap<>();
        HashSet<User> keySet = new HashSet<>(destinationData.keySet());
        for (User i : keySet) {
            similarity.put(i, cosineSimilarity(destinationData.get(i), currentUserDestination()));
        }

        return similarity;
    }

    //calculates the cosine similarity between two users A and B.
    public double cosineSimilarity(HashMap<Destination, Integer> A, HashMap<Destination, Integer> B) {
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;
        HashSet<Destination> keySet = new HashSet<>(A.keySet());
        keySet.addAll(B.keySet());


        //Dot product, Magnitude for A and Magnitude for B.
        for (Destination i : keySet) {
            if(A.containsKey(i) && B.containsKey(i)) {
                dotProduct += A.get(i) * B.get(i);
            }
            if(A.containsKey(i)){
                magnitudeA += Math.pow(A.get(i), 2);
            }
            if(B.containsKey(i)){
                magnitudeB += Math.pow(B.get(i), 2);
            }
        }
        return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
    }


    public HashSet<User> recommendationForCurrent() {
        HashSet<User> userSet = new HashSet<>();
        int userAmount = 0;
        Map<User, Double> similarity = similarityMatrix();
        Set<User> similarityKey = similarity.keySet();
        User temp = new User();
        for (User i : similarityKey) {
            if (userAmount == 0) {
                userSet.add(i);
                userAmount++;
                temp = i;
            } else if (0 < userAmount && userAmount < k) {
                userSet.add(i);
                userAmount++;
                if (similarity.get(i) < similarity.get(temp)) {
                    temp = i;
                }
            } else {
                if (similarity.get(i) > similarity.get(temp)) {
                    userSet.remove(temp);
                    temp = i;
                    userSet.add(temp);
                }
            }

        }
        return userSet;
    }

    //Matrix of similarity of destinations based on the User's similarity.
    public HashMap<Destination, Double> destinationSimilarity() {

        HashSet<User> knnUsers = recommendationForCurrent();

        HashMap<Destination, Double> similarity = new HashMap<>();

        Map<User, Double> sim = similarityMatrix();
        for (User i : knnUsers) {
            for (Destination x : i.getUsersDestination()) {
                if (!similarity.keySet().contains(x)) {
                    similarity.put(x, sim.get(i));

                } else {
                    Double tempVal = similarity.get(x) + sim.get(i);
                    similarity.put(x, tempVal);
                }
            }
        }
        return similarity;
    }

    //ArrayList of ranked destination with biggest first
    public ArrayList<Destination> recommendationDest() {

        ArrayList<Destination> rankedDest = new ArrayList<>();
        HashMap<Destination, Double> similarityDest = destinationSimilarity();
        Set<Map.Entry<Destination, Double>> mapentries = similarityDest.entrySet();

        List<Map.Entry<Destination, Double>> rankDest = new LinkedList<>(mapentries);


        // sorting the List
        rankDest.sort(new Comparator<Map.Entry<Destination, Double>>() {

            @Override
            public int compare(Map.Entry<Destination, Double> ele1,
                               Map.Entry<Destination, Double> ele2) {

                return ele2.getValue().compareTo(ele1.getValue());
            }
        });
        for (Map.Entry<Destination, Double> entry : rankDest) {
            rankedDest.add(entry.getKey());
        }

        return rankedDest;
    }
}