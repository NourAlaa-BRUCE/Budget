package src.storage;

import src.models.Transactions;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Handles storage and retrieval of recurring transactions.
public class TransactionsStorage {
    private static final String FILE = "data/transactions.txt";
    private static final String SEP = "\\|";
    private static List<Transactions> transactions = new ArrayList<>();
    private static int nextId = 1;

    // Loads all transactions from the file into memory
    public static void load() {
        transactions.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(SEP);
                Transactions t = new Transactions(
                        Integer.parseInt(p[0]),
                        p[1],
                        p[2],
                        Double.parseDouble(p[3]),
                        LocalDate.parse(p[4]),
                        p[5].equals("null") ? null : LocalDate.parse(p[5]),
                        p[6],
                        Boolean.parseBoolean(p[7]));
                transactions.add(t);
                nextId = Math.max(nextId, t.getTransactionId() + 1); // ensure nextId is always increasing
            }
        } catch (IOException ignored) {
        }
    }

    // Adds a new transaction to the list and appends it to the file
    public static void add(Transactions t) {
        transactions.add(t);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(String.join("|",
                    String.valueOf(t.getTransactionId()),
                    t.getCategory(),
                    t.getTransactionType(),
                    String.valueOf(t.getAmount()),
                    t.getStartDate().toString(),
                    (t.getEndDate() != null ? t.getEndDate().toString() : "null"),
                    t.getRecurrencePattern(),
                    String.valueOf(t.isActive())));
            w.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Returns all stored transactions as a new list
    public static List<Transactions> getAll() {
        return new ArrayList<>(transactions);
    }

    // Returns the next available transaction ID
    public static int getNextId() {
        return nextId++;
    }

    // Overwrites all transactions in the file
    public static void saveAll(List<Transactions> transactions) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            for (Transactions t : transactions) {
                w.write(String.join("|",
                        String.valueOf(t.getTransactionId()),
                        t.getCategory(),
                        t.getTransactionType(),
                        String.valueOf(t.getAmount()),
                        t.getStartDate().toString(),
                        (t.getEndDate() != null ? t.getEndDate().toString() : ""),
                        t.getRecurrencePattern(),
                        String.valueOf(t.isActive())));
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

}
