package src.storage;

import src.models.Income;
import java.util.ArrayList;
import java.util.List;

public class IncomeStorage {
    private static final List<Income> incomes = new ArrayList<>();

    public static void addIncome(Income inc) {
        incomes.add(inc);
    }

    public static List<Income> getAll() {
        return new ArrayList<>(incomes);
    }
}
