package hw3.structures;

import hw3.monitoring.PerformanceMonitor;
import java.util.Iterator;

/**
 * Linked list-based implementation of SortableList and PerformanceMonitor.
 * This implementation uses LinkPosition objects as the actual nodes in a
 * singly-linked list structure with a sentinel node.
 *
 * @param <T> the type of elements stored in the list.
 *            pre: T must be Comparable.
 */
public class SortableLinkedList<T extends Comparable<T>>
    implements SortableList<T>, PerformanceMonitor {
  protected final LinkPosition<T> sentinel;
  protected final int size;
  protected int comparisonCount;
  protected int swapCount;

  /**
   * Creates a SortableLinkedList with the given data.
   *
   * @param data the array of elements to initialize the list with
   * @throws IllegalArgumentException if data is null
   */
  public SortableLinkedList(T[] data) {
    // TODO: Update me!
    this.sentinel = null;
    this.size = 0;
  }

  @Override
  public int compare(Position pos1, Position pos2) {
    // TODO: Implement me!
    return 0;
  }

  @Override
  public void swap(Position pos1, Position pos2) {
    // TODO: Implement me!
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Position getPosition(int index) {
    // TODO: Implement me!
    return null;
  }

  @Override
  public T get(Position pos) {
    // TODO: Implement me!
    return null;
  }

  @Override
  public Iterator<Position> iterator() {
    return new SortableLinkedListIterator();
  }

  // PerformanceMonitor interface implementation
  @Override
  public void resetCounters() {
    comparisonCount = 0;
    swapCount = 0;
  }

  @Override
  public int getComparisonCount() {
    return comparisonCount;
  }

  @Override
  public int getModificationCount() {
    return swapCount;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    LinkPosition<T> current = sentinel.next;
    for (int i = 0; i < size; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(current.data);
      current = current.next;
    }
    sb.append("]");
    return sb.toString();
  }

  // Protected nested class for linked list-based positions.
  // This encapsulates the position implementation details within the SortableLinkedList.
  protected static class LinkPosition<T> extends Position {
    public T data;
    public LinkPosition<T> next;

    /**
     * Constructs a new LinkPosition node with the specified index and data.
     * The next pointer is initialized to null.
     * @param index the position index
     * @param data the element to store in this node
     */
    public LinkPosition(int index, T data) {
      super(index);
      this.data = data;
      this.next = null;
    }

    @Override
    public String toString() {
      return "LinkPosition[" + index + ":" + data + "]";
    }
  }

  private class SortableLinkedListIterator implements Iterator<Position> {

    @Override
    public boolean hasNext() {
      // TODO: Implement me!
      return false;
    }

    @Override
    public Position next() {
      // TODO: Implement me!
      return null;
    }
  }
}
