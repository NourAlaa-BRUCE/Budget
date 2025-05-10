package src.services;

import src.models.Goal;
import src.storage.GoalStorage;

import java.util.List;
import java.util.Scanner;

// This class manages savings goals, including adding new goals and viewing progress.
public class GoalService {
    private final Scanner sc = new Scanner(System.in);

    public void addGoal() {
        
        // Take Goal's name , target amount & validate it
        System.out.println("\n--- Set Savings Goal ---");
        System.out.print("Goal name (e.g., Umrah trip): ");
        String name = sc.nextLine();

        System.out.print("Target amount: ");
        double target;
        try {
            target = Double.parseDouble(sc.nextLine());
            if (target <= 0)
                throw new NumberFormatException();
        } catch (Exception e) {
            System.out.println("Invalid amount.");
            return;
        }

        // Finally , create Goal object & store it
        Goal g = new Goal(name, target);
        GoalStorage.add(g);
        System.out.println("Goal created: " + g);
    }

    // Prompts all Goals stored
    public void viewGoals() {
        System.out.println("\n--- All Goals ---");
        System.out.printf("%-20s | %s%n", "Name", "Progress");
        List<Goal> list = GoalStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No goals set.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
