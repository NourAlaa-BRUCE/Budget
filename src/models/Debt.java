package src.models;

public class Debt {
    private String name;
    private double totalAmount;
    private double remainingBalance;

    public Debt(String name, double totalAmount) {
        if (totalAmount <= 0)
            throw new IllegalArgumentException("Debt amount must be greater than zero.");
        this.name = name;
        this.totalAmount = totalAmount;
        this.remainingBalance = totalAmount;
    }

    public String getName() { return name; }
    public double getTotalAmount() { return totalAmount; }
    public double getRemainingBalance() { return remainingBalance; }

    public void makePayment(double amount) {
        if (amount <= 0 || amount > remainingBalance) {
            throw new IllegalArgumentException("Invalid payment amount.");
        }
        remainingBalance -= amount;
    }

    @Override
    public String toString() {
        return name + " | Total: " + totalAmount + " | Remaining: " + remainingBalance;
    }
}

