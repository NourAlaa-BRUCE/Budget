package src.services;

import src.models.Income;
import src.storage.IncomeStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class IncomeService {
    private final Scanner sc = new Scanner(System.in);

    /**
     * User Story #3 – Track Income
     */
    public void addIncome() {
        System.out.println("\n--- Add Income ---");
        System.out.print("Source (e.g., Salary): ");
        String source = sc.nextLine();

        System.out.print("Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        Income inc = new Income(source, amount, date);
        IncomeStorage.addIncome(inc);
        System.out.println("Income added: " + inc);
    }

    public void viewAllIncomes() {
        System.out.println("\n--- All Incomes ---");
        List<Income> list = IncomeStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No incomes recorded.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
