package starter;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap {

  private static Integer[] sample1() {
    return new Integer[]{12, 14, 11};
  }

  private static Integer[] sample2() {
    return new Integer[]{10, 5, 8, 1, 6, 14, 12, 3, 7, 20};
  }

  /**
   * Demo starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    Integer[] data = sample2();

    System.out.println("Before:");
    System.out.println(Arrays.toString(data));

    sort(data);

    System.out.println("After:");
    System.out.println(Arrays.toString(data));
  }

  // Pre: data != null
  private static void sort(Integer[] data) {

    //O(n) operation because n memory locations need to be allocated and initialized to null (internal array)
    PriorityQueue<Integer> heap = new PriorityQueue<>(data.length);

    for (Integer i : data) { //Loops n times
      heap.add(i); //Addition is a O(lg n) operation
    }
    //Whole loop is O(n lg n)

    for (int i = 0; i < data.length; i++) { //Loops n times
      data[i] = heap.remove(); //O(lg n) to remove, replace top with last element and swim down
    }
    //Whole loop is O(n lg n)

    //TOTAL TIME: O(n) + O(n lg n) + O(n lg n) = O(n lg n)
    //Space: O(n) to hold helper priority queue

    /*
    Heapify optimization: Traverse heap bottom up to get asymptotic runtime of O(n)

    //Scheme:
    for (int i = numElements/2, i >= 1, i--) { //We can do numElements/2 to avoid all non-leaves
      sort (i, data)
    }




     */
  }
}
