// This class represents an income source with a description, amount, and date.
// It provides methods to retrieve the income's details and a string representation of the income.

package src.models;

import java.time.LocalDate;

public class Income {
    private String source;
    private double amount;
    private LocalDate date;

    public Income(String source, double amount, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getSource() { return source; }
    public double getAmount()  { return amount;  }
    public LocalDate getDate() { return date;    }

    @Override
    public String toString() {
        return date + " | " + source + " : " + amount;
    }
}