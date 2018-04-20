package userProfiles;

import destination.Destination;

import java.io.*;
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

    private ArrayList<Destination> usersDestination; //User's destination in an ordered list (the order of listOfDestinations)

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

    public static ArrayList<User> listOfCreatedUsers() throws IOException {
        ArrayList<User> fileDestination = new ArrayList<>();

        FileReader fr;
        String line;

        fr = new FileReader("src/userData.txt");
        BufferedReader bf = new BufferedReader(fr);
        int totalLine = Destination.linesInFile("src/userData.txt");

        for (int i = 0; i < totalLine; i++) {
            line = bf.readLine();
            String[] strings = line.split("\\t", 6);
            User temp = new User();

            temp.setFirstName(strings[0]);
            temp.setLastName(strings[1]);
            temp.setAge(strings[2]);
            temp.setGender(strings[3]);
            temp.setUsernameID(strings[4]);
            temp.setPassword(strings[5]);
        }
        try {
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDestination;
    }
}

