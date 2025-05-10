package src.storage;

import src.models.Goal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Handles storage and retrieval of goal data.
public class GoalStorage {
    private static final String FILE = "data/goals.txt";
    private static final String SEP = "\\|";
    private static List<Goal> goals = new ArrayList<>();

    // Loads all goals from the file into the list.
    public static void load() {
        goals.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                Goal g = new Goal(p[0], Double.parseDouble(p[1]));
                g.add(Double.parseDouble(p[2])); // saved part
                goals.add(g);
            }
        } catch (IOException ignored) {
        }
    }

    // Adds a new goal to the list and appends it to the file.
    public static void add(Goal g) {
        goals.add(g);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", g.getName(),
                    String.valueOf(g.getTarget()),
                    String.valueOf(g.getSaved())));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving goal: " + e.getMessage());
        }
    }

    // Retrieves all stored goals as a new list.
    public static List<Goal> getAll() {
        return new ArrayList<>(goals);
    }
}
