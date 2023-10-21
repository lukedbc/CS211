package Cp2;

class Ax {
    public int number =12;
    public void team() {
        number += 1;
        System.out.println("San F");
    }
}

class Bx extends Ax {
    public int number =49;
    public void team() {
        System.out.println("Seahawks ");
    }
}


public class Main2 {

    public static void main(String[] args) {
        Ax a = new Bx();
        a.team();
        System.out.println(a.number);
    }
}
