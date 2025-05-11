package src.services;

// Manages recurring transactions: adding and viewing them.

import src.models.Transactions;
import src.storage.TransactionsStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TransactionsService {
    private final Scanner sc = new Scanner(System.in);

    // Adds a recurring transaction with user input and validation
    public void addTransaction() {
        System.out.println("\n--- Add Recurring Transaction ---");

        System.out.print("Category (e.g., Salary, Rent): ");
        String category = sc.nextLine();

        System.out.print("Type (income/expense): ");
        String type = sc.nextLine().toLowerCase();
        if (!type.equals("income") && !type.equals("expense")) {
            System.out.println("Invalid type. Must be 'income' or 'expense'.");
            return;
        }

        System.out.print("Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Amount must be a number greater than 0.");
            return;
        }

        System.out.print("Start Date (YYYY-MM-DD): ");
        LocalDate startDate;
        try {
            startDate = LocalDate.parse(sc.nextLine());
            if (startDate.isBefore(LocalDate.now())) {
                System.out.println("Start date cannot be in the past.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("End Date (optional, press Enter to skip): ");
        String endInput = sc.nextLine();
        LocalDate endDate = null;
        if (!endInput.isBlank()) {
            try {
                endDate = LocalDate.parse(endInput);
                if (endDate.isBefore(startDate)) {
                    System.out.println("End date must be after start date.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid end date format.");
                return;
            }
        }

        System.out.print("Recurrence (daily/weekly/monthly): ");
        String recurrence = sc.nextLine().toLowerCase();
        if (!recurrence.matches("daily|weekly|monthly")) {
            System.out.println("Invalid recurrence pattern.");
            return;
        }

        // Create and save the transaction
        int id = TransactionsStorage.getNextId();
        Transactions tx = new Transactions(id, category, type, amount, startDate, endDate, recurrence, true);
        TransactionsStorage.add(tx);
        System.out.println("Transaction scheduled: " + tx);
    }

    // Views all stored transactions
    public void viewTransactions() {
        System.out.println("\n--- All Recurring Transactions ---");
        System.out.printf("%-4s | %-10s | %-8s | %-6s | %-12s | %-12s | %-8s | %s%n",
                "ID", "Category", "Type", "Amount", "Start", "End", "Repeat", "Active");

        List<Transactions> list = TransactionsStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transactions t : list) {
                System.out.printf("%-4d | %-10s | %-8s | %-6.2f | %-12s | %-12s | %-8s | %s%n",
                        t.getTransactionId(),
                        t.getCategory(),
                        t.getTransactionType(),
                        t.getAmount(),
                        t.getStartDate(),
                        (t.getEndDate() != null ? t.getEndDate() : "N/A"),
                        t.getRecurrencePattern(),
                        t.isActive() ? "Yes" : "No");
            }
        }
    }
}
