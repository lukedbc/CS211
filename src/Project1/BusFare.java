package Project1;

public abstract class BusFare {

    private static final double BASE_RATE = 5.0;

    private final String name;

    public BusFare(String name) {
        this.name = name;
    }
    public double getBaseRate() {
        return BASE_RATE;
    }

    // Calculate fare
    // Adult: use base rate
    // Senior: discount 50%
    public abstract double fare();

    public void printFare() {
        System.out.println("Fare: " + fare());
    }

    public String getName() {
        return name;
    }
}
