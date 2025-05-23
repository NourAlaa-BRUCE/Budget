// This class represents an expense with a category, amount, and date.
// It provides methods to retrieve the expense's details and a string representation of the expense.

package src.models;

import java.time.LocalDate;

public class Expense {
    private String category;
    private double amount;
    private LocalDate date;

    public Expense(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount   = amount;
        this.date     = date;
    }

    public String getCategory() { return category; }
    public double getAmount()   { return amount;   }
    public LocalDate getDate()  { return date;     }

    @Override
    public String toString() {
        return date + " | " + category + " : " + amount;
    }
}
