package BonusPractice3;

public class Main {

    public static void main(String[] args) {
        Burger seattle = new USABurger("Seattle");
        Burger cancun = new MexicoBurger("Cancun");

        seattle.menu();
        cancun.menu();
    }
}
