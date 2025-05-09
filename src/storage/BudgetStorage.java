package src.storage;

import src.models.Budget;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetStorage {
    private static final String FILE = "data/budgets.txt";
    private static final String SEP  = "\\|";
    private static List<Budget> budgets = new ArrayList<>();

    public static void load() {
        budgets.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                budgets.add(new Budget(
                  p[0], 
                  Double.parseDouble(p[1]), 
                  LocalDate.parse(p[2]), 
                  LocalDate.parse(p[3])
                ));
            }
        } catch (IOException ignored) {}
    }

    public static void addBudget(Budget b) {
        budgets.add(b);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", b.getCategory(),
                                 String.valueOf(b.getLimit()),
                                 b.getStartDate().toString(),
                                 b.getEndDate().toString()));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving budget: " + e.getMessage());
        }
    }

    public static List<Budget> getAll() {
        return new ArrayList<>(budgets);
    }
}
