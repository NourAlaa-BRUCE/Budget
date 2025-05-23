package src.storage;

import src.models.Income;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Manages storage and retrieval of income data.
public class IncomeStorage {
    private static final String FILE = "data/incomes.txt";
    private static final String SEP = "\\|";
    private static List<Income> incomes = new ArrayList<>();

    // Loads all incomes from the file into the list.
    public static void load() {
        incomes.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                // source|amount|date
                incomes.add(new Income(p[0], Double.parseDouble(p[1]), LocalDate.parse(p[2])));
            }
        } catch (IOException ignored) {
        }
    }

    // Adds a new income to the list and appends it to the file.
    public static void addIncome(Income inc) {
        incomes.add(inc);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|", inc.getSource(),
                    String.valueOf(inc.getAmount()),
                    inc.getDate().toString()));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving income: " + e.getMessage());
        }
    }

    // Retrieves all stored incomes as a new list.
    public static List<Income> getAll() {
        return new ArrayList<>(incomes);
    }
}
