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
    PriorityQueue<Integer> heap = new PriorityQueue<>(data.length);
    for (Integer i : data) {
      heap.add(i);
    }
    for (int i = 0; i < data.length; i++) {
      data[i] = heap.remove();
    }
  }
}
