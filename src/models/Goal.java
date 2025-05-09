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
