package BonusPractice3;

import java.util.ArrayList;
import java.util.List;

public abstract class Burger {

    private static final double BASE_COST = 3.0;
    private final List<String> toppings;
    private final String currency;

    private final String location;

    public Burger(String currency, String location) {
        toppings = new ArrayList<>();

        toppings.add("beef patty");
        toppings.add("tomato");
        toppings.add("onion");
        toppings.add("ranch sauce");

        this.currency = currency;
        this.location = location;
    }

    public double getBaseCost() {
        return BASE_COST;
    }

    public abstract double price();

    public abstract void menu();

    public String getCurrency() {
        return currency;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void addTopping(String topping) {
        toppings.add(topping);
    }

    public void printToppings() {
        System.out.println(toppingAsString());
    }

    public String toppingAsString() {
        return String.join(", ", toppings);
    }

    public String getLocation() {
        return location;
    }

//    public void menu() {
//        System.out.println("Location: " + getLocation() + ". price: " + price() + ". toppings: " + toppingAsString());
//    }

}
