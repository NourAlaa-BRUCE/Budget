package src.storage;

import src.models.Goal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GoalStorage {
    private static final String FILE = "data/goals.txt";
    private static final String SEP  = "\\|";
    private static List<Goal> goals = new ArrayList<>();

    public static void load() {
        goals.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                Goal g = new Goal(p[0], Double.parseDouble(p[1]));
                g.add(Double.parseDouble(p[2]));  // saved part
                goals.add(g);
            }
        } catch (IOException ignored) {}
    }

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

    public static List<Goal> getAll() {
        return new ArrayList<>(goals);
    }
}
