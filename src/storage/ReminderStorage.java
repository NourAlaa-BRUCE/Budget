package src.storage;

import src.models.Reminder;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderStorage {
    private static final String FILE = "data/reminders.txt";
    private static final String SEP  = "\\|";
    private static List<Reminder> reminders = new ArrayList<>();

    public static void load() {
        reminders.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                reminders.add(new Reminder(
                  p[0], 
                  LocalDateTime.parse(p[1])
                ));
            }
        } catch (IOException ignored) {}
    }

    public static void add(Reminder rmd) {
        reminders.add(rmd);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", rmd.getTitle(), rmd.getDateTime().toString()));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving reminder: " + e.getMessage());
        }
    }

    public static List<Reminder> getAll() {
        return new ArrayList<>(reminders);
    }
}
