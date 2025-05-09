package src.storage;

import src.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final String FILE = "data/users.txt";
    private static final String SEP  = "\\|";
    private static List<User> users = new ArrayList<>();

    // استدعِه في بداية main
    public static void load() {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(SEP);
                if (p.length == 4) {
                    users.add(new User(p[0], p[1], p[2], p[3]));
                }
            }
        } catch (IOException ignored) {}
    }

    public static boolean exists(String email) {
        return users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public static void addUser(User u) {
        users.add(u);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", u.getUsername(), u.getEmail(), u.getPhoneNumber(), u.getPassword()));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static User getUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }
}
