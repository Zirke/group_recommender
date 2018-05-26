package destination;

import CustomExceptions.FileFormatException;
import destination.Activities.Beach;
import destination.Activities.Museum;
import destination.Activities.Sightseeing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Destination implements Cloneable {
    private String destinationName;
    private double lattitude;
    private double longitude;
    private String countryName;
    private String cityType;
    private String checkins;
    private ArrayList<Venue> venues = new ArrayList<>();
    private ArrayList<Activity> activities = new ArrayList<>();

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

    public String getCountryName() {
        return countryName;
    }

    private void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityType() {
        return cityType;
    }

    private void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public String getCheckins() {
        return checkins;
    }

    public void setCheckins(String checkins) {
        this.checkins = checkins;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //contructor with destination name as argument.
    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    //constructor with destination name and checkins
    public Destination(String destinationName, String checkins) {
        this.destinationName = destinationName;
        this.checkins = checkins;
    }

    //contructor without initialization.
    private Destination() {
    }

    public static ArrayList<Destination> listOfDestination() throws IOException {
        ArrayList<Destination> fileDestination = new ArrayList<>();

        FileReader fr;
        String line;

        //kan kun gøre det med abosulte path og ikke den relative
        fr = new FileReader("src/destination/cities.txt");
        BufferedReader bf = new BufferedReader(fr);
        int totalLine = linesInFile("src/destination/cities.txt");


        for (int i = 0; i < totalLine; i++) {
            line = bf.readLine();
            String[] strings = line.split("\\t", 8);
            Destination temp = new Destination();

            if (isNumeric(strings[3]) && isNumeric(strings[4])) {
                temp.setDestinationName(strings[0] + strings[1] + strings[2] + strings[3]);
                temp.setLattitude(Double.parseDouble(strings[4]));
                temp.setLongitude(Double.parseDouble(strings[5]));
                temp.setCountryName(strings[6]);
                temp.setCityType(strings[7]);
                fileDestination.add(temp);
            } else if (isNumeric(strings[2]) && isNumeric(strings[3])) {
                temp.setDestinationName(strings[0] + strings[1] + strings[2]);
                temp.setLattitude(Double.parseDouble(strings[3]));
                temp.setLongitude(Double.parseDouble(strings[4]));
                temp.setCountryName(strings[5]);
                temp.setCityType(strings[6]);
                fileDestination.add(temp);
            } else {
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

    public static List<String> listDestNames() throws IOException {
        ArrayList<Destination> temp = listOfDestination();
        List<String> destNames = new ArrayList<String>();

        for (Destination destination : temp) {
            destNames.add(destination.getDestinationName());
        }
        return destNames;
    }

    public static int linesInFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    private static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void printDestination() {
        System.out.println("Destination name: " + this.getDestinationName() +
                "   Lattitude :" + this.getLattitude() + "   Longitude :" + this.getLongitude() +
                "   Country :" + this.getCountryName() + "   City type :" + this.getCityType());
    }

    private String destinationNameToFilePath() {
        return "src/destination/Activities/Destinations/" + getDestinationName() + ".txt";
    }

    //TODO OBS! Absolute path
    public static ArrayList<Destination> mostPopularDestinations() {
        Path inpath = Paths.get("C:\\Java-programmer\\GitHub\\group_recommender\\src\\destination\\mostpopular.txt");
        ArrayList<Destination> destList = new ArrayList<>();
        int j = 0;

        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(inpath))) {

            String currentLine = null;

            while ((currentLine = reader.readLine()) != null && j <= 6) {
                String[] tempArr = currentLine.split("\t");
                String first = tempArr[0];
                String second = tempArr[1];
                ++j;
                destList.add(new Destination(first, second));
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
        return destList;
    }

    public void fillActivities() {
        File filepath = new File(destinationNameToFilePath());
        if (filepath.exists()) {
            Path inpath = Paths.get(destinationNameToFilePath());

            try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(inpath))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] temp = currentLine.split(":");
                    String type = temp[0];
                    String name = temp[1];
                    String location = temp[2];
                    String typeSpecific = temp[3];
                    switch (type) {
                        case "Beach":
                            getActivities().add(new Beach(name, location, Integer.parseInt(typeSpecific)));
                            break;
                        case "Museum":
                            getActivities().add(new Museum(name, location, typeSpecific));
                            break;
                        case "Sightseeing":
                            getActivities().add(new Sightseeing(name, location, typeSpecific));
                            break;
                        default:
                            throw new FileFormatException();
                    }
                }
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
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