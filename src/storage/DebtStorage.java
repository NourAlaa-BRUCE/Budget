package src.storage;

import src.models.Debt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DebtStorage {
    private static final String FILE = "data/debts.txt";
    private static final String SEP  = "\\|";
    private static List<Debt> debts = new ArrayList<>();

    public static void load() {
        debts.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                Debt d = new Debt(p[0], Double.parseDouble(p[1]));
                d.makePayment(Double.parseDouble(p[1]) - Double.parseDouble(p[2])); // set remaining
                debts.add(d);
            }
        } catch (IOException ignored) {}
    }

    public static void addDebt(Debt d) {
        debts.add(d);
        saveToFile();
    }

    public static void makePayment(int index, double amount) {
        if (index < 0 || index >= debts.size()) throw new IndexOutOfBoundsException("Invalid index");
        debts.get(index).makePayment(amount);
        saveToFile();
    }

    public static List<Debt> getAll() {
        return new ArrayList<>(debts);
    }

    private static void saveToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            for (Debt d : debts) {
                w.write(String.join("|",
                        d.getName(),
                        String.valueOf(d.getTotalAmount()),
                        String.valueOf(d.getRemainingBalance())));
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving debts: " + e.getMessage());
        }
    }
}
