package CP10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    private static final int NUM_OF_TEST = 5;
    private static final int NUM_OF_DATA = 90000;
    private static final Random RAND = new Random();

    private static ArrayList<String> AL = new ArrayList<>();
    private static LinkedList<String> LL = new LinkedList<>();

    public static void init() {
        AL.clear();
        LL.clear();

        for (int i = 0; i < NUM_OF_DATA; i++) {
            AL.add(String.valueOf(i));
            LL.add(String.valueOf(i));
        }
    }

    public static void remove() {
        double timeRemove_AL = 0;
        double timeRemove_LL = 0;

        double start, end;

        for (int i = 0; i < NUM_OF_DATA/2; i++) {
            int index2Remove = RAND.nextInt(NUM_OF_DATA/2);
            start = System.currentTimeMillis();
            AL.remove(index2Remove);
            end = System.currentTimeMillis();
            timeRemove_AL += (end - start);

            start = System.currentTimeMillis();
            LL.remove(index2Remove);
            end = System.currentTimeMillis();
            timeRemove_LL += (end - start);
        }
        System.out.println("Time remove AL:"+ timeRemove_AL/1000 + "(s)");
        System.out.println("Time remove LL:"+ timeRemove_LL/1000 + "(s)");
        double diff = (timeRemove_AL / timeRemove_LL) * 100;
        System.out.printf("Diff in percent (AL / LL) %.2f\n", diff);

    }

    public static void main(String[] args) {
        for (int i = 1; i<= NUM_OF_TEST; i++) {
            init();
            System.out.println("Test " + i +":");
            System.out.println();
            remove();
        }
    }
}
