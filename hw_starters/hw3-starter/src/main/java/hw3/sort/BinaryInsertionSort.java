package hw3.sort;

import hw3.structures.Position;
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
    if (list == null) {
      throw new IllegalArgumentException("List cannot be null");
    }

    int n = list.size();

    // if n <= 1, the list is already sorted and will skip the following loop
    // Start from the second element (index 1)
    // The first element is considered already "sorted"
    for (int i = 1; i < n; i++) {
      // Find the correct position for element at index i
      // by comparing it with elements in the sorted portion (0 to i-1)
      int j = i;

      // Move element at position i to its correct position
      // by swapping it leftward until it's in the right place
      int middle = insert(list, i);
      while (j > middle) {
        Position prevPos = list.getPosition(j - 1);
        Position currentPos = list.getPosition(j);

        list.swap(prevPos, currentPos);
        j--;
      }
    }
  }

  //Takes in list and i and performs binary search between 0 and i
  private int insert(SortableList<T> list, int index) {
    int front = 0;
    int back = index - 1;
    Position currentPos = list.getPosition(index);
    int middle = (front + back) / 2;

    while (front <= back) {
      middle = (front + back) / 2;
      Position midPos = list.getPosition(middle);
      int comparison = list.compare(currentPos, midPos);
      if (comparison < 0) {
        back = middle - 1;
      } else { //Handle if comparison is >= 0
        front = middle + 1; //Try to find element right before where it would belong or end of equal code block
      }
    }
    return front; //If here, then front or back was updated but middle is stale, so return front
  }

  @Override
  public String getName() {
    return "Binary Insertion Sort";
  }
}
