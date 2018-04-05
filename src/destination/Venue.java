package destination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import static destination.Destination.*;
import static java.lang.Math.abs;

//Used to read data from file
public class Venue {
    private String ID;
    private double Latitude;
    private double longitude;
    private String CategoryName;
    private String CountryCode;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public static ArrayList<Venue> listOfVenues() throws IOException{
        ArrayList<Venue> listOfVenues = new ArrayList<>();
        Venue temp = new Venue();
        final String filePath = "/Users/Abiram/IdeaProjects/group_recommender01/src/destination/dataset_TIST2015_POIs.txt";
        FileReader fr = new FileReader(filePath);
        BufferedReader bf = new BufferedReader(fr);
        String line;
        int totalLine = linesInFile(filePath);

        for(int i = 0; i< totalLine;i++){
            line = bf.readLine();
            String[] strings = line.split("\\t",5);
            temp.setID(strings[0]);
            temp.setLatitude(Double.parseDouble(strings[1]));
            temp.setLongitude(Double.parseDouble(strings[2]));
            temp.setCategoryName(strings[3]);
            temp.setCountryCode(strings[4]);
            listOfVenues.add(temp);
        }
        return listOfVenues;
    }

    //vi skal måske samle POI sammen på en anden måde.
    public static void destinationVenues() throws IOException {
        try {
            ArrayList<Venue> listOfVenues = listOfVenues();
            ArrayList<Destination> listOfDestination = listOfDestination();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int totalDestination = 415;
        int totalVenue = linesInFile("/Users/Abiram/IdeaProjects/group_recommender01/src/destination/dataset_TIST2015_POIs.txt");
        int destIndex = 0;
        double distVenueDest;
        double indexDist;
        for(int i = 0; i< totalVenue;i++){
            for(int j = 1; j< totalDestination; j++){

                distVenueDest = abs(listOfDestination().get(j).getLattitude()-listOfVenues().get(i).getLatitude()) +
                        abs(listOfDestination().get(j).getLongitude()-listOfVenues().get(i).getLongitude());
                indexDist = abs(listOfDestination().get(destIndex).getLattitude()-listOfVenues().get(i).getLatitude()) +
                        abs(listOfDestination().get(destIndex).getLongitude()-listOfVenues().get(i).getLongitude());
                System.out.println("h");
                if(distVenueDest < indexDist){
                    destIndex = j;
                }
            }
            listOfDestination().get(destIndex).getVenues().add(listOfVenues().get(i));
        }

    }
}