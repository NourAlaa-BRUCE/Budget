package src;
import src.models.User;
import src.services.AuthService;
import src.services.IncomeService;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AuthService auth = new AuthService();
    private static final IncomeService incomeService = new IncomeService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Personal Budget App ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> auth.signUp();
                case "2" -> {
                    if (auth.login()) {
                        showDashboard(auth.getCurrentUser());
                    }
                }
                case "3" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void showDashboard(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Dashboard (" + user.getUsername() + ") ---");
            System.out.println("1. Add Income");
            System.out.println("2. Set Budget");
            System.out.println("3. Track Expenses");
            System.out.println("4. Add Reminder");
            System.out.println("5. Set Goal");
            System.out.println("6. View All Incomes");
            System.out.println("7. Log Out");
            System.out.print("Choose option: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1" -> incomeService.addIncome();
                case "2" -> System.out.println("[Set Budget] - under construction");
                case "3" -> System.out.println("[Track Expenses] - under construction");
                case "4" -> System.out.println("[Add Reminder] - under construction");
                case "5" -> System.out.println("[Set Goal] - under construction");
                case "6" -> incomeService.viewAllIncomes();
                case "7" -> {
                    loggedIn = false;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
