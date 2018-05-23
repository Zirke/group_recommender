package userProfiles;

import destination.Destination;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Cloneable {
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String usernameID;
    private String password;
    private ArrayList<Destination> usersDestination = new ArrayList<>(); //User's destination in an ordered list (the order of listOfDestinations)

    public User(String usernameID, ArrayList<Destination> usersDestination) {
        this.usernameID = usernameID;
        this.usersDestination = usersDestination;
    }

    public ArrayList<Destination> getUsersDestination() {
        return usersDestination;
    }

    public User(String usernameID) {
        this.usernameID = usernameID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUsernameID(String usernameID) {
        this.usernameID = usernameID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsersDestination(ArrayList<Destination> usersDestination) {
        this.usersDestination = usersDestination;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getUsernameID() {
        return usernameID;
    }

    public String getPassword() {
        return password;
    }
    /*
    public User(String id, ArrayList<Destination> usersDestination) {
        this.usernameID = id;
        this.usersDestination = usersDestination;
    }

    public ArrayList<Destination> getUsersDestination() {
        return usersDestination;
    }

    public void setUsersDestination(ArrayList<Destination> usersDestination) {
        this.usersDestination = usersDestination;
    }
    */


    public String getId() {
        return usernameID;
    }

    //Reading dataset and add destinations to each user
    public static ArrayList<User> listDataset() throws IOException {
        Path dataset = Paths.get("src/userProfiles/userid_city.txt");
        BufferedReader reader = Files.newBufferedReader(dataset);
        ArrayList<User> userRecords = new ArrayList<>();
        String currentLine = reader.readLine();
        reader.mark(0);
        ArrayList<User> templist = new ArrayList<>();
        int j = 0;

        while (currentLine != null) {
            String[] userDetail = currentLine.split("\t");
            String id = userDetail[0];
            String cityname = userDetail[1];
            templist.add(new User(id));
            currentLine = reader.readLine();
        }

        for (int i = 0; i < templist.size() - 1; ++i) {
            if (!templist.get(i).getId().equals(templist.get(i + 1).getId())) {
                userRecords.add(new User(templist.get(i).getId()));
            }
        }
        templist.clear();
        BufferedReader reader2 = Files.newBufferedReader(dataset);
        currentLine = reader2.readLine();

        while (currentLine != null) {
            String[] userDetail = currentLine.split("\t");
            String id = userDetail[0];
            String cityname = userDetail[1];
            if (id.equals(userRecords.get(j).getId())) {
                if(!userRecords.get(j).getUsersDestination().contains(new Destination(cityname))){
                userRecords.get(j).getUsersDestination().add(new Destination(cityname));
                }
            } else if (!id.equals(userRecords.get(j).getId()) && j != userRecords.size() -1) {
                ++j;
                userRecords.get(j).getUsersDestination().add(new Destination(cityname));
            }
            currentLine = reader2.readLine();
        }
        return userRecords;
    }


    //Method saves a List of userProfiles.User into a file with the path filename.
    public static void usersToFile(String filename, List<User> e) {

        File file = new File(filename);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(e);
        } catch (IOException t) {
            t.printStackTrace();
        }
    }

    //Method reads the List of userProfiles.User from a file.
    //Hvis vi laver en arrayList can den bare typecastes, når metoden anvendes.
    public static List<User> deserializeFromDisk(String filename) {
        List<User> ret = new ArrayList<>();
        File file = new File(filename);

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            ret = (List<User>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(usernameID, user.usernameID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(usernameID);
    }

}

