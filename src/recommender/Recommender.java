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
    private ArrayList<User> users;
    private User recommendationUser;
    private ArrayList<User> groupMembers;
    private int k;

    HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();  // matrix for whether the user has been to a given destiantion.
    Map<User, Double> sim; //similarity.
    HashSet<Destination> recommendationForCurrent;
    //Map<Destination, Map<Destination,Double>> sim;  for the similarity between two destinations


    public Recommender(User recommendationUser, int k) {
        this.recommendationUser = recommendationUser;
        this.k = k;
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

    //Getter for group members.
    public ArrayList<User> getGroupMembers() {
        return groupMembers;
    }

    //Setter for Group members.
    public void setGroupMembers(ArrayList<User> groupMembers) {
        this.groupMembers = groupMembers;
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
    public int userDestinationMapCreation(Destination aDestinationList, User temp, Destination tempDest,
                                          int tempIndex, HashMap<Destination, Integer> tempUserDestMap) throws CloneNotSupportedException {
        if(temp.getUsersDestination().size()<= tempIndex){
            System.out.println( "hh");
        }
        if(temp.getUsersDestination().isEmpty()){
            System.out.println("halo");
        }
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


        return tempIndex;
    }


    //the method creates a matrix for the arrayList "users". if a user has been to a destination then the Integer is 1 otherwise 0.
    public HashMap<User, HashMap<Destination, Integer>> destinationMatrixCreator(ArrayList<User> users) {
        HashMap<User, HashMap<Destination, Integer>> matrix = new HashMap<>();
        Iterator iter = users.iterator();
        System.out.println("ha4");
        int counter = 0;
        while (iter.hasNext()) {
            User temp = (User) iter.next();
            HashMap<Destination, Integer> tempUserDestMap = new HashMap<>();
            Destination tempDest = new Destination();

            int tempIndex = 0;

            try {
                ArrayList<Destination> destinationList = listOfDestination();
                for (Destination aDestinationList : destinationList) {
                    tempIndex = userDestinationMapCreation(aDestinationList, temp, tempDest, tempIndex, tempUserDestMap);
                    System.out.println(counter);

                    matrix.put(temp, tempUserDestMap);
                }
                counter++;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                System.out.println("Clone is not Supported");
            }
        }

        return matrix;
    }


    public HashMap<Destination, Integer> currentUserDestination(User currentUser) {
        HashMap<Destination, Integer> tempUserDestMap = new HashMap<>();
        Destination tempDest = new Destination();

        int tempIndex = 0;
        try {
            ArrayList<Destination> destinationList = listOfDestination();
            for (Destination aDestinationList : destinationList) {
                tempIndex = userDestinationMapCreation(aDestinationList, currentUser, tempDest, tempIndex, tempUserDestMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone is not Supported");
        }
        return tempUserDestMap;

    }

    public Map<User, Double> similarityMatrix(User recommendationUser, ArrayList<User> users) {
        HashMap<User, HashMap<Destination, Integer>> destinationData = destinationMatrixCreator(users);
        Map<User, Double> similarity = new HashMap<>();
        HashSet<User> keySet = new HashSet<>(destinationData.keySet());
        for (User i : keySet) {
            similarity.put(i, cosineSimilarity(destinationData.get(i), currentUserDestination(recommendationUser)));
        }
        this.setSim(similarity);
        return similarity;
    }

    //calculates the cosine similarity between two users A and B.
    public double cosineSimilarity(HashMap<Destination, Integer> A, HashMap<Destination, Integer> B) {
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;
        double result = 0;
        HashSet<Destination> keySet = new HashSet<>(A.keySet());
        keySet.addAll(B.keySet());


        //Dot product, Magnitude for A and Magnitude for B.
        for (Destination i : keySet) {
            dotProduct += A.get(i) * B.get(i);
            magnitudeA += Math.pow(A.get(i), 2);
            magnitudeB += Math.pow(B.get(i), 2);
        }

        result = dotProduct / Math.sqrt(magnitudeA * magnitudeB);
        return result;
    }


    public HashSet<User> recommendationForCurrent(User recommendationUser, ArrayList<User> users) {
        HashSet<User> userSet = new HashSet<>();
        int userAmount = 0;
        Map<User, Double> similarity = similarityMatrix(recommendationUser, users);
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
    public HashMap<Destination, Double> destinationSimilarity(User recommendationUser, ArrayList<User> users) {

        HashSet<User> knnUsers = recommendationForCurrent(recommendationUser, users);

        HashMap<Destination, Double> similarity = new HashMap<>();

        Map<User, Double> sim = similarityMatrix(recommendationUser, users);
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
    public ArrayList<Destination> recommendationDest(User recommendationUser, ArrayList<User> users) {

        ArrayList<Destination> rankedDest = new ArrayList<>();
        System.out.println("ha");
        HashMap<Destination, Double> similarityDest = destinationSimilarity(recommendationUser, users);
        System.out.println("ha2");
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


//Mangler noget til grupper her eller måske anden fil til det.


    public HashSet<Destination> destinationRecommendation(int k, User recommendationUser, ArrayList<User> trainSet) {
        HashSet<User> knnUsers = this.recommendationForCurrent(recommendationUser, trainSet);
        HashSet<Destination> recommendationDest = new HashSet<>();
        Set<Destination> tempUserDest;
        for (User i : knnUsers) {
            tempUserDest = new HashSet<>(i.getUsersDestination());
            recommendationDest.addAll(tempUserDest);
        }

        //previosly visited destinations aren't included.
        for (Destination dest : recommendationUser.getUsersDestination()) {
            if (recommendationDest.contains(dest)) {
                recommendationDest.remove(dest);
            }
        }
        setRecommendationForCurrent(recommendationDest);
        return recommendationDest;
    }


    //Måske prioritere med fairness istedet for.
    public HashSet<Destination> destinationRecommendationForGroup(int k, ArrayList<User> groupMembers, ArrayList<User> trainSet) {
        HashSet<Destination> recommendationDest = new HashSet<>();
        HashSet<Destination> tempUserDest = new HashSet<>();
        for (User i : groupMembers) {
            tempUserDest = destinationRecommendation(k, i, trainSet);
            recommendationDest.addAll(tempUserDest);
        }
        return recommendationDest;
    }

}