package Project1;

public class myBusFare {

    // Name: Loc, Dao
    // sID: 201875743 
    // Date: 28 Sept, 2023
    // The purpose of program: 
    //      Print out the bus fare price based on the type of user
    //      Using OOP approach: encapsulation, polymorphism, inheritance, abstraction
    public static void main(String[] args) {
        BusFare ted = new Senior("Ted");
        BusFare chloe = new Adult("Chloe");

        ted.printFare();
        chloe.printFare();
    }
}
