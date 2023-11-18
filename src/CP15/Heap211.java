package CP15;

import java.util.ArrayList;
import java.util.PriorityQueue;

//Fall 2023

public class Heap211 {

    public static ArrayList<Integer> heap = new ArrayList<>();


    Heap211() {
        // We need add value for index 0 because
        // we don't use index 0 for the implementation of the heap (start from 1 is the most efficient to to implement)
        // the index of the heap starts at index 1 cause
        // the operator add, remove, find parent, find left child, find right child are based on the index
        // The index starts at 1 make the calculation and implementation easier
        heap.add(0);  // explain (comment) why we need add(0)
    }

    //proj. 3

    int parent(int index) {
        return index / 2;
    }


    int leftChild(int index) {
        return index * 2;
    }


    int rightChild(int index) {
        return index * 2 + 1;
    }


    boolean hasParent(int index) {
        return index > 1;
    }

    int heapSize() {
        return heap.size() - 1;
    }

    boolean hasLeftChild(int index) {
        return leftChild(index) <= heapSize();
    }


    boolean hasRightChild(int index) {
        return rightChild(index) <= heapSize();
    }


    void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }


    // CP 15
    int peekMin() {
        return heap.get(1);
    }


    boolean isEmpty() {
        return heap.size() == 1;
    }


    void bubbleUp() {

    }

    void add(int value) {

        // CP 15
        heap.add(value);

        System.out.println("   heap: " + printHeap());
        System.out.println("   bubble-up: start");

        // proj 3
       	/*
           bubble-up here
        */
        int index = heapSize();
        boolean found = false;

        while (!found && hasParent(index)) {
            int parentIndex = parent(index);

            int childValue = heap.get(index);
            int parentValue = heap.get(parentIndex);

            if (childValue < parentValue) {
                // swap
               swap(index, parentIndex);
               index = parentIndex;
            } else {
                found = true;
            }
        }

        System.out.println("   bubble-up: end");
        System.out.println("   new heap: " + printHeap());
    }


    int remove() {

        System.out.println("   heap: " + printHeap());

        // CP 15
    	int min = peekMin();  // peek min value by calling peekMin()
        heap.set(1, heap.get(heap.size() - 1)); // move the last node to the first. tip: use one of the ArrayList methods
        System.out.println("   Removed: " + min);
        heap.remove(heap.size() - 1); // delete the last node from the heap. The heap is reduced.

        System.out.println("   heap: " + printHeap());
        System.out.println("   bubble-down: start");

        // proj 3
     	/*
         bubble-down here

        */

        System.out.println("   bubble-down: end");
        System.out.println("   new heap: " + printHeap());

        return min;
    }

    //use this method as is
    public String printHeap() {

        StringBuilder result = new StringBuilder("[");

        if (heap.size() > 1) {
            result.append(heap.get(1));
        }

        for (int i = 2; i < heap.size(); i++) {
            result.append(", ").append(heap.get(i));
        }

        return result + "]";
    }
}
