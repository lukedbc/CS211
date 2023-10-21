package Project1;

public class Adult extends BusFare {

    public Adult(String name) {
        super(name);
    }

    @Override
    public double fare() {
        return super.getBaseRate();
    }
}
