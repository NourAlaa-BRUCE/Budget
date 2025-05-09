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