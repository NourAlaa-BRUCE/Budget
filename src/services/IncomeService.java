package src.services;

import src.models.Income;
import src.storage.IncomeStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// Handles income operations like adding and viewing incomes.
public class IncomeService {
    private final Scanner sc = new Scanner(System.in);

    /**
     * User Story #3 – Track Income
     */

    public void addIncome() {
        // Take income's source & amount & validate it
        System.out.println("\n--- Add Income ---");
        System.out.print("Source (e.g., Salary, Freelance, Bonus, etc): ");
        String source = sc.nextLine();

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
        // Take date & validate it
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        // Finally , create Income object & store
        Income inc = new Income(source, amount, date);
        IncomeStorage.addIncome(inc);
        System.out.println("Income added: " + inc);
    }

    // Prompts all Incomes stored
    public void viewAllIncomes() {
        System.out.println("\n--- All Incomes ---");
        // System.out.printf("%-12s | %-15s : %s%n", "Date", "Source", "Amount");
        List<Income> list = IncomeStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No incomes recorded.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
