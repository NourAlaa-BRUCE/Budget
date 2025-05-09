package src.models;

import java.time.LocalDate;

public class Budget {
    private String category;
    private double limit;
    private LocalDate startDate;
    private LocalDate endDate;

    public Budget(String category, double limit, LocalDate startDate, LocalDate endDate) {
        this.category  = category;
        this.limit     = limit;
        this.startDate = startDate;
        this.endDate   = endDate;
    }

    public String getCategory()   { return category; }
    public double getLimit()      { return limit; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getEndDate() { return endDate; }

    @Override
    public String toString() {
        return category + " | Limit: " + limit +
               " | From: " + startDate + " To: " + endDate;
    }
}
