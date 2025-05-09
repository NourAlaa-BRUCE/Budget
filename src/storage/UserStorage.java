package src.storage;

import src.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final List<User> users = new ArrayList<>();

    public static boolean exists(String email) {
        return users.stream()
                    .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
    public static void addUser(User user) {
        users.add(user);
    }
    public static User getUserByEmail(String email) {
        return users.stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
    }
}