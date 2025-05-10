package src.services;

// Manages reminders, including adding and viewing them.

import src.models.Reminder;
import src.storage.ReminderStorage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ReminderService {
    private final Scanner sc = new Scanner(System.in);

    public void addReminder() {
        // Take reminder title , date & time & validate date & time format
        System.out.println("\n--- Add Reminder ---");
        System.out.print("Title (e.g., Pay electricity bill): ");
        String title = sc.nextLine();

        System.out.print("Date and Time (YYYY-MM-DDTHH:MM): ");
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid format.");
            return;
        }

        // Finally , craete Reminder object & store it
        Reminder r = new Reminder(title, dt);
        ReminderStorage.add(r);
        System.out.println("Reminder set: " + r);
    }

    // Prompts all Reminders stored
    public void viewReminders() {
        System.out.println("\n--- All Reminders ---");
        System.out.printf("%-16s | %s%n", "DateTime", "Title");
        List<Reminder> list = ReminderStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No reminders set.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
