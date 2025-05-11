// This class represents a recurring financial transaction with a category, type,
// amount, schedule, and active status. It supports income and expense entries.

package src.models;

import java.time.LocalDate;

public class Transactions {
    private int transactionId;
    private String category;
    private String transactionType; // "income" or "expense"
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate; // Optional
    private String recurrencePattern; // e.g., "daily", "weekly", "monthly"
    private boolean isActive;

    public Transactions(int transactionId, String category, String transactionType,
                        double amount, LocalDate startDate, LocalDate endDate,
                        String recurrencePattern, boolean isActive) {
        this.transactionId = transactionId;
        this.category = category;
        this.transactionType = transactionType;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrencePattern = recurrencePattern;
        this.isActive = isActive;
    }

    public int getTransactionId()        { return transactionId; }
    public String getCategory()          { return category; }
    public String getTransactionType()   { return transactionType; }
    public double getAmount()            { return amount; }
    public LocalDate getStartDate()      { return startDate; }
    public LocalDate getEndDate()        { return endDate; }
    public String getRecurrencePattern() { return recurrencePattern; }
    public boolean isActive()            { return isActive; }

    @Override
    public String toString() {
        return "Transaction #" + transactionId +
               " | " + transactionType + ": " + category +
               " | Amount: " + amount +
               " | Start: " + startDate +
               " | End: " + (endDate != null ? endDate : "N/A") +
               " | Recurs: " + recurrencePattern +
               " | Active: " + isActive;
    }
}
