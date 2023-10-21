package BonusPractice3;

public class MexicoBurger extends Burger{

    private static final double EXCHANGE_RATE = 17.5;

    public MexicoBurger(String location) {
        super("Mexican Peso", location);
        addTopping("taco source");
    }

    @Override
    public double price() {
        return getBaseCost() * EXCHANGE_RATE;
    }

    @Override
    public void menu() {
        System.out.println("Location: " + getLocation() + ". price: " + price() + ". toppings: " + toppingAsString());
    }
}
