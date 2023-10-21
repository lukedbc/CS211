package BonusPractice3;

public class USABurger extends Burger {

    public USABurger(String location) {
       super("USD", location);
    }

    @Override
    public double price() {
        return getBaseCost();
    }

    @Override
    public void menu() {
        System.out.println("price: " + price() + ". toppings: " + toppingAsString());
    }
}
