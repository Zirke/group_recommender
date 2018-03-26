package userProfiles;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private int age;
    private int gender; //1=male, 2=female
    private String country;
    int test;
    int douhbletest;

    public User(String id, int age, int gender, String country) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
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
}