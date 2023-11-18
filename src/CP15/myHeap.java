package CP15;

import java.util.Random;

// Fall 2023

public class myHeap {

    final static int HOW_MANY_TESTs = 20;

    public static void main(String[] args) {

        System.out.println("Project 3. MinHeap (Fall 2023)");

        // create an instance of Random class
        Random random = new Random();

        // creat an instance of Heap211 class
        Heap211 minHeap = new Heap211();

        for (int test = 1; test <= HOW_MANY_TESTs; test++) {

            //generate an random number to decide the action is 'add' or 'remove'
            // 0 - add
            // 1 - remove
            int action = random.nextInt(2);

            if (action == 0) {

                // generate a node number using random number (eg 0~49)
                int node = random.nextInt(50);

                System.out.println("Action " + test + ": Add " + node);

                // add node to the heap	by calling 'add method' in Heap211 class.
                minHeap.add(node);

            }
            if (action == 1) {
                if (minHeap.isEmpty()) {  //if the heap is empty, skip this action, do not count this action
                    test = test - 1;
                } else {
                    System.out.println("Action " + test + ": Remove min");

                    // remove node to the heap by calling 'remove method' in Heap211 class.
                    int min = minHeap.remove();
                }
            }
        }
    }
}
