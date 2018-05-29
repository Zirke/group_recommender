package recommender;

import destination.Destination;
import userProfiles.User;
import java.io.IOException;
import java.util.ArrayList;
import static userProfiles.User.listDataset;

//This class contains methods for evaluating a recommender system.

public class RecommenderEval {

    //Constructor for "RecommenderEval".
    public RecommenderEval() {
    }

    //returns the best k or the highest precision for "accuracyUser".
    public double accuracyOrBestK(User accuracyUser, int limit, String accOrBestK){
        ArrayList<User> dataSet = new ArrayList<>();
        try {
            dataSet = listDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double precision = 0;
        int kBest = 0;

        dataSet.remove(accuracyUser);
        Destination test01 = accuracyUser.getUsersDestination().get(0);
        accuracyUser.getUsersDestination().remove(test01);
        Destination test02 = accuracyUser.getUsersDestination().get(1);
        accuracyUser.getUsersDestination().remove(test02);
        Destination test03 = accuracyUser.getUsersDestination().get(2);
        accuracyUser.getUsersDestination().remove(test03);

        for (int k = 1; k < limit; k++) {
            double tp = 0, fp = 0;
            Recommender test = new Recommender(accuracyUser, dataSet, k);
            ArrayList<Destination> recommendation = test.recommendationDest();

            for (Destination i : recommendation) {

                Boolean isTruePositive =  i.getDestinationName().equals(test01.getDestinationName()) ||
                        i.getDestinationName().equals(test02.getDestinationName()) ||
                        i.getDestinationName().equals(test03.getDestinationName());

                if(isTruePositive){
                    tp++;
                }

                Boolean isFalsePositive = !(isTruePositive || accuracyUser.getUsersDestination().contains(i));

                if (isFalsePositive) {
                    fp++;
                }

            }

            //Calculation of precision and assignment of best k.
            if((tp / (fp + tp) * 100) > precision){
                precision = (tp / (fp + tp) * 100);
                kBest = k;
            }

        }
        accuracyUser.getUsersDestination().add(test01);
        accuracyUser.getUsersDestination().add(test02);
        accuracyUser.getUsersDestination().add(test03);

        dataSet.add(accuracyUser);
        if(accOrBestK.equals("k")){
            return kBest;
        }else
            return precision;
    }

    //returns average precision for kNN.
    public double averagePrecision(){
        ArrayList<User> dataSet = new ArrayList<>();
        ArrayList<User> moreThan5 = new ArrayList<>();
        double averagePrediction = 0.0;

        try {
            dataSet = listDataset();
        } catch (IOException e) {
            System.out.println("Data set unreadable");
        }

        //in order to find users with 5 destinations or more and add to "moreThan5".
        for (User i : dataSet) {
            if (i.getUsersDestination().size() >= 5) {
                moreThan5.add(i);
            }
        }

        //Calculation of accumulated precision.
        for(int i = 0; i < moreThan5.size(); i++){
            averagePrediction += new RecommenderEval().accuracyOrBestK(moreThan5.get(i),
                    dataSet.size(), "accuracy");
        }

        return averagePrediction/moreThan5.size();
    }
}
