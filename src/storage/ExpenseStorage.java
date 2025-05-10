package src.storage;

import src.models.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Manages storage and retrieval of expense data.
public class ExpenseStorage {
    private static final String FILE = "data/expenses.txt";
    private static final String SEP = "\\|";
    private static List<Expense> expenses = new ArrayList<>();

    // Loads all expenses from the file into the list.
    public static void load() {
        expenses.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                expenses.add(new Expense(p[0], Double.parseDouble(p[1]), LocalDate.parse(p[2])));
            }
        } catch (IOException ignored) {
        }
    }

    // Adds a new expense to the list and appends it to the file.
    public static void addExpense(Expense e) {
        expenses.add(e);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", e.getCategory(),
                    String.valueOf(e.getAmount()),
                    e.getDate().toString()));
            w.newLine();
        } catch (IOException ex) {
            System.out.println("Error saving expense: " + ex.getMessage());
        }
    }

    // Retrieves all stored expenses as a new list.
    public static List<Expense> getAll() {
        return new ArrayList<>(expenses);
    }
}
