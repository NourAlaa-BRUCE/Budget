package src.services;

// This service manages budget-related operations, such as creating and retrieving budgets.

import src.models.Budget;
import src.storage.BudgetStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BudgetService {
    private final Scanner sc = new Scanner(System.in);

    public void setBudget() {
        //Taking Budget info
        System.out.println("\n--- Create Budget ---");
        System.out.print("Category (e.g., groceries): ");
        String cat = sc.nextLine();

        //Taking Limit amout & validate it
        System.out.print("Limit amount: ");
        double lim;
        try {
            lim = Double.parseDouble(sc.nextLine());
            if (lim <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            System.out.println("Invalid amount."); return;
        }

        //Taking Date info ( start & end )
        System.out.print("Start date (YYYY-MM-DD): ");
        LocalDate start;
        try { start = LocalDate.parse(sc.nextLine()); }
        catch (Exception e) { System.out.println("Invalid date."); return; }

        System.out.print("End date (YYYY-MM-DD): ");
        LocalDate end;
        try { end = LocalDate.parse(sc.nextLine()); }
        catch (Exception e) { System.out.println("Invalid date."); return; }

        //Finally , create the budget object 
        Budget b = new Budget(cat, lim, start, end);
        BudgetStorage.addBudget(b);
        System.out.println("Budget created: " + b);
    }

    //Prompts the Budgets info 
    public void viewBudgets() {
        System.out.println("\n--- All Budgets ---");
        System.out.printf("%-15s | %-10s | %-10s to %-10s%n",
                          "Category", "Limit", "Start", "End");
        List<Budget> list = BudgetStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No budgets set.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
