package src.services;

import src.models.Expense;
import src.storage.ExpenseStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// This class manages expense-related operations, including tracking and viewing expenses.
public class ExpenseService {
    private final Scanner sc = new Scanner(System.in);

    /**
     * User Story #7 – Track Expenses
     */

    // method to take Expenses from user
    public void trackExpenses() {
        // Take expense category
        System.out.println("\n--- Track Expenses ---");
        System.out.print("Category (e.g., food, transport): ");
        String category = sc.nextLine();

        // Take amount & validate
        System.out.print("Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        // Take date of expense & validate
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        // Finally , create the expense
        Expense exp = new Expense(category, amount, date);
        ExpenseStorage.addExpense(exp);
        System.out.println("Expense recorded: " + exp);
    }

    // Prompts all Expenses stored
    public void viewAllExpenses() {
        System.out.println("\n--- All Expenses ---");
        System.out.printf("%-12s | %-15s : %s%n", "Date", "Category", "Amount");
        List<Expense> list = ExpenseStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
