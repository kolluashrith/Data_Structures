package hw3.structures;

import hw3.monitoring.PerformanceMonitor;

/**
 * Array-based implementation of Bag using transpose sequential search optimization.
 * This implementation uses an internal array to store elements and implements
 * the transpose optimization: when an element is found, it swaps with its
 * immediate predecessor (if not at position 0). This gradually moves popular
 * elements toward the front of the array.
 * The transpose method is ideal for arrays because it only requires O(1) swap
 * operations, unlike move-to-front which would require O(n) shifts.
 *
 * @param <T> the type of elements stored in the bag.
 *            pre: T must be Comparable.
 */
public class ArrayBag<T extends Comparable<T>> implements Bag<T>, PerformanceMonitor {
  protected final T[] elements;
  protected int comparisonCount;
  protected int modificationCount;

  /**
   * Creates an ArrayBag with the given data.
   *
   * @param data the array of elements to initialize the bag with
   * @throws IllegalArgumentException if data is null
   */
  @SuppressWarnings("unchecked")
  public ArrayBag(T[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Data array cannot be null");
    }

    // Create a defensive copy to avoid external modifications
    this.elements = (T[]) new Comparable[data.length];
    System.arraycopy(data, 0, this.elements, 0, data.length);

    this.comparisonCount = 0;
    this.modificationCount = 0;
  }

  // Compares the target element with the element at a given index.
  // Should return 0 if the elements are equal.
  protected int compare(T element, int index) {
    // TODO: Implement me!
    return 0;
  }

  // Swaps the element at the given index with its predecessor (transpose).
  protected void transpose(int index) {
    // TODO: Implement me!
  }

  @Override
  public boolean contains(T element) {
    // TODO: Implement me using compare and transpose operations!
    return false; 
  }

  @Override
  public boolean isEmpty() {
    return elements.length == 0;
  }

  // PerformanceMonitor interface implementation
  @Override
  public void resetCounters() {
    comparisonCount = 0;
    modificationCount = 0;
  }

  @Override
  public int getComparisonCount() {
    return comparisonCount;
  }

  @Override
  public int getModificationCount() {
    return modificationCount;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ArrayBag[");
    for (int i = 0; i < elements.length; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(elements[i]);
    }
    sb.append("]");
    return sb.toString();
  }
}
