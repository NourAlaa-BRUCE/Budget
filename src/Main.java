
package src;
import src.models.User;
import src.services.AuthService;
import src.services.IncomeService;
import src.services.BudgetService;
import src.services.DebtService;
import src.services.ReminderService;
import src.storage.BudgetStorage;
import src.storage.DebtStorage;
import src.storage.ExpenseStorage;
import src.storage.GoalStorage;
import src.storage.IncomeStorage;
import src.storage.ReminderStorage;
import src.storage.UserStorage;
import src.services.GoalService;
import src.services.ExpenseService;


import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AuthService auth = new AuthService();
    private static final IncomeService incomeService = new IncomeService();
    private static final BudgetService budgetService = new BudgetService();
    private static final ReminderService reminderService = new ReminderService();
    private static final GoalService goalService = new GoalService();
    private static final ExpenseService expenseService = new ExpenseService();
    private static final DebtService debtService = new DebtService();

    public static void main(String[] args) {
        UserStorage.load();
        IncomeStorage.load();
        BudgetStorage.load();
        ExpenseStorage.load();
        ReminderStorage.load();
        GoalStorage.load();
        DebtStorage.load();
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== Personal Budget App ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> auth.signUp();
                case "2" -> {
                    if (auth.login()) {
                        showDashboard(auth.getCurrentUser());
                    }
                }
                case "3" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void showDashboard(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Dashboard (" + user.getUsername() + ") ---");
            System.out.println("1. Add Income");
            System.out.println("2. Create Budget");
            System.out.println("3. Track Expenses");
            System.out.println("4. Add Reminder");
            System.out.println("5. Set Savings Goal");
            System.out.println("6. View All Incomes");
            System.out.println("7. View All Budgets");
            System.out.println("8. View All Reminders");
            System.out.println("9. View All Goals");
            System.out.println("10. view All Expenses");
            System.out.println("11. Add Debt");           
            System.out.println("12. View Debts");          
            System.out.println("13. Make Payment");
            System.out.println("0. Log Out");
            System.out.print("Choose option: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1" -> incomeService.addIncome();
                case "2" -> budgetService.setBudget();
                case "3" -> expenseService.trackExpenses();
                case "4" -> reminderService.addReminder();
                case "5" -> goalService.addGoal();
                case "6" -> incomeService.viewAllIncomes();
                case "7" -> budgetService.viewBudgets();
                case "8" -> reminderService.viewReminders();
                case "9" -> goalService.viewGoals();
                case "10"-> expenseService.viewAllExpenses();
                case "11"-> debtService.addDebt();          
                case "12"-> debtService.listDebts();        
                case "13"-> debtService.makePayment();
                case "0" -> { loggedIn = false; System.out.println("Logged out."); }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
   

