// This class represents a financial goal with a name, target amount, and saved amount.
// It provides methods to retrieve the goal's details, add to the saved amount, and a string representation of the goal.

package src.models;

public class Goal {
    private String name;
    private double target;
    private double saved;

    public Goal(String name, double target) {
        this.name   = name;
        this.target = target;
        this.saved  = 0;
    }

    public String getName()    { return name; }
    public double getTarget()  { return target; }
    public double getSaved()   { return saved; }
    public void add(double amt){ this.saved += amt; }

    @Override
    public String toString() {
        return name + " | Saved: " + saved + " / " + target;
    }
}
