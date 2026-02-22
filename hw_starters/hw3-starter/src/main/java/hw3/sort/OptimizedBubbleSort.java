package hw3.sort;

import hw3.structures.SortableList;

/**
 * Optimized bubble sort implementation with early termination.
 * This version of bubble sort includes the early termination optimization.
 * If no swaps occur during a complete pass through the array, the algorithm
 * knows that the array is already sorted and can terminate early.
 * This optimization dramatically improves performance on already sorted or
 * nearly sorted data, changing the best-case time complexity from O(n²) to O(n)
 * while maintaining the same O(n²) worst-case performance.
 *
 * @param <T> the type of elements to be sorted.
 *            pre: T must be Comparable.
 */
public class OptimizedBubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

  @Override
  public void sort(SortableList<T> list) {
    // TODO: Implement Me!
  }

  @Override
  public String getName() {
    return "Optimized Bubble Sort";
  }
}
