package src.services;

import src.models.Goal;
import src.storage.GoalStorage;

import java.util.List;
import java.util.Scanner;

public class GoalService {
    private final Scanner sc = new Scanner(System.in);

    public void addGoal() {
        System.out.println("\n--- Set Savings Goal ---");
        System.out.print("Goal name (e.g., Umrah trip): ");
        String name = sc.nextLine();

        System.out.print("Target amount: ");
        double target;
        try {
            target = Double.parseDouble(sc.nextLine());
            if (target <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            System.out.println("Invalid amount."); return;
        }

        Goal g = new Goal(name, target);
        GoalStorage.add(g);
        System.out.println("Goal created: " + g);
    }

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
