package Project1;

public class Senior extends BusFare {
    private static final double DISCOUNT_PERCENT = 0.5;

    public Senior(String name) {
        super(name);
    }

    @Override
    public double fare() {
        return super.getBaseRate() * DISCOUNT_PERCENT;
    }
}
