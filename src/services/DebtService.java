package src.services;

import src.models.Debt;
import src.storage.DebtStorage;

import java.util.List;
import java.util.Scanner;

 /**
 * User Story #8 
 */
public class DebtService {
    private final Scanner sc = new Scanner(System.in);

    public void addDebt() {
        System.out.println("\n--- Add Debt ---");
        System.out.print("Debt Name: ");
        String name = sc.nextLine();

        System.out.print("Total Amount: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        Debt debt = new Debt(name, amount);
        DebtStorage.addDebt(debt);
        System.out.println("Debt added: " + debt);
    }

    public void makePayment() {
        List<Debt> debts = DebtStorage.getAll();
        if (debts.isEmpty()) {
            System.out.println("No debts found.");
            return;
        }

        System.out.println("\n--- Debts ---");
        for (int i = 0; i < debts.size(); i++) {
            System.out.println("[" + i + "] " + debts.get(i));
        }

        System.out.print("Choose debt index: ");
        int index = Integer.parseInt(sc.nextLine());

        System.out.print("Payment Amount: ");
        double payment = Double.parseDouble(sc.nextLine());

        try {
            DebtStorage.makePayment(index, payment);
            System.out.println("Payment successful.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listDebts() {
        System.out.println("\n--- All Debts ---");
        List<Debt> list = DebtStorage.getAll();
        if (list.isEmpty()) {
            System.out.println("No debts recorded.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
