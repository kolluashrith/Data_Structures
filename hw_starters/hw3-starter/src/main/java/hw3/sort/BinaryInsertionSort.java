package hw3.sort;

import hw3.structures.SortableList;

/**
 * Binary insertion sort implementation.
 * This is an optimization of the standard insertion sort that uses binary search
 * to find the insertion position for each element. While this reduces the number
 * of comparisons from O(n) to O(log n) per insertion, the overall time complexity
 * remains O(n²) because elements still need to be shifted to make room for insertion.
 * This optimization is most beneficial when comparison operations are expensive
 * relative to element movement operations.
 *
 * @param <T> the type of elements to be sorted.
 *            pre: T must be Comparable.
 */
public class BinaryInsertionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

  @Override
  public void sort(SortableList<T> list) {
    // TODO: Implement Me!
  }

  @Override
  public String getName() {
    return "Binary Insertion Sort";
  }
}
