package destination;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Destination implements Cloneable{
    private String destinationName;
    private double lattitude;
    private double longitude;
    private String countryName;
    private String cityType;
    private ArrayList<Venue> venues = new ArrayList<>();

    public String getDestinationName() {
        return destinationName;
    }

    private void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public double getLattitude() {
        return lattitude;
    }

    private void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private String getCountryName() {
        return countryName;
    }

    private void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    private String getCityType() {
        return cityType;
    }

    private void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    //contructor with destination name as argument.
    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    //contructor without initialization.
    private Destination() {
    }

    public static ArrayList<Destination> listOfDestination() throws IOException {
        ArrayList<Destination> fileDestination = new ArrayList<>();

        FileReader fr;
        String line;

        //kan kun gøre det med abosulte path og ikke den relative
        fr = new FileReader("/Users/Abiram/IdeaProjects/group_recommender01/src/destination/cities.txt");
        BufferedReader bf = new BufferedReader(fr);
        int totalLine = linesInFile("/Users/Abiram/IdeaProjects/group_recommender01/src/destination/cities.txt");



        for(int i = 0; i<totalLine;i++) {
            line = bf.readLine();
            String[] strings = line.split("\\t",8);
            Destination temp = new Destination();

            if(isNumeric(strings[3]) && isNumeric(strings[4])){
                temp.setDestinationName(strings[0] + strings[1] + strings[2] + strings[3]);
                temp.setLattitude(Double.parseDouble(strings[4]));
                temp.setLongitude(Double.parseDouble(strings[5]));
                temp.setCountryName(strings[6]);
                temp.setCityType(strings[7]);
                fileDestination.add(temp);
            }else if(isNumeric(strings[2]) && isNumeric(strings[3])){
                temp.setDestinationName(strings[0] + strings[1] + strings[2]);
                temp.setLattitude(Double.parseDouble(strings[3]));
                temp.setLongitude(Double.parseDouble(strings[4]));
                temp.setCountryName(strings[5]);
                temp.setCityType(strings[6]);
                fileDestination.add(temp);
            }else{
                temp.setDestinationName(strings[0]);
                temp.setLongitude(Double.parseDouble(strings[1]));
                temp.setLattitude(Double.parseDouble(strings[2]));
                temp.setCountryName(strings[3]);
                temp.setCityType(strings[5]);
                fileDestination.add(temp);
            }
        }

        try {
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileDestination;
    }

    public static int linesInFile(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    private static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void printDestination(){
        System.out.println("Destination name: " + this.getDestinationName() +
                "   Lattitude :" + this.getLattitude() +"   Longitude :" + this.getLongitude() +
                "   Country :" + this.getCountryName() + "   City type :" +this.getCityType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return Objects.equals(getDestinationName(), that.getDestinationName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDestinationName());
    }
}