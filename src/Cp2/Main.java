package Cp2;
class A {

    double gpa = 4.0;
    void output() {
        System.out.println("Your GPA is ");
    }
}
class B extends A {
    double gpa = 3.0;

    @Override
    void output() {
        System.out.println("My GPA is ");
    }
}
public class Main {

    public static void main(String[] args) {
        A b = new B();
        b.output();
        System.out.print(b.gpa);
    }
}