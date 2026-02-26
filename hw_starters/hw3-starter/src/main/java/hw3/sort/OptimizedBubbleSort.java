package hw3.sort;

import hw3.structures.Position;
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
    if (list == null) {
      throw new IllegalArgumentException("List cannot be null");
    }

    int n = list.size();
    if (n <= 1) {
      return; // Already sorted
    }

    // Perform n-1 passes
    for (int i = 0; i < n - 1; i++) {
      // In each pass, bubble the largest unsorted element to its correct position
      boolean ifswap = false; //Boolean to check if a swap is performed during each pass
      for (int j = 0; j < n - i - 1; j++) {
        if (swapHelper(list, j)) {
          ifswap = true;
        }
      }
      if (!ifswap) {
        return; //Already sorted if no swaps are made during a pass
      }
    }
  }

  /**
   * Helper method to check if two elements at a position need to be swapped and swap if needed.
   * @param list the sortable list upon which the algorithm is working
   * @param j the current index during the pass
   * @return boolean value containing whether a swap was performed
   */
  private boolean swapHelper(SortableList<T> list, int j) {
    // Get positions for adjacent elements
    Position pos1 = list.getPosition(j);
    Position pos2 = list.getPosition(j + 1);

    // Compare adjacent elements
    if (list.compare(pos1, pos2) > 0) {
      // Swap if they are in wrong order
      list.swap(pos1, pos2);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getName() {
    return "Optimized Bubble Sort";
  }
}
