package hw3.structures;

import hw3.monitoring.PerformanceMonitor;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    if  (data == null) {
      throw new IllegalArgumentException("Data array cannot be null");
    }

    this.sentinel = new LinkPosition<>(-1, null); //Initialize sentinel node
    LinkPosition<T> currNode = this.sentinel; //Temp var to grow linkedlist
    this.size = data.length;

    for (int i = 0; i < data.length; i++) {
      currNode.next = new LinkPosition<>(i, data[i]);
      currNode = currNode.next;
    }

    this.comparisonCount = 0;
    this.swapCount = 0;
  }

  /**
   * Validates that the given index is within bounds.
   *
   * @param index the index to validate
   * @throws IndexOutOfBoundsException if index is negative or >= size()
   */
  private void validateIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(
              String.format("Index %d out of bounds for length %d", index, size));
    }
  }

  @SuppressWarnings("unchecked")
  private LinkPosition<T> validatePosition(Position pos) {
    if (pos == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    if (!(pos instanceof LinkPosition)) {
      throw new IllegalArgumentException(
              "Position must be an LinkPosition for this implementation");
    }
    if (pos.index < 0 || pos.index >= size) {
      throw new IllegalArgumentException("Position index out of bounds: " + pos.index);
    }
    if (getPosition(pos.index) != pos) {
      throw new IllegalArgumentException("Position does not belong to this list");
    }
    return (LinkPosition<T>) pos;
  }

  @Override
  public int compare(Position pos1, Position pos2) {
    LinkPosition<T> linkPos1 = validatePosition(pos1);
    LinkPosition<T> linkPos2 = validatePosition(pos2);
    comparisonCount++;
    return linkPos1.data.compareTo(linkPos2.data);
  }

  @Override
  public void swap(Position pos1, Position pos2) {
    LinkPosition<T> linkPos1 = validatePosition(pos1);
    LinkPosition<T> linkPos2 = validatePosition(pos2);
    swapCount++;
    T temp = linkPos1.data;
    linkPos1.data = linkPos2.data;
    linkPos2.data = temp;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Position getPosition(int index) {
    validateIndex(index);
    LinkPosition<T> currNode = this.sentinel;
    for (int i = 0; i <= index; i++) {
      currNode = currNode.next;
    }
    return currNode;
  }

  @Override
  public T get(Position pos) {
    LinkPosition<T> linkPos = validatePosition(pos);
    return linkPos.data;
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
    LinkPosition<T> currNode;

    public SortableLinkedListIterator() {
      currNode = sentinel.next;
    }

    @Override
    public boolean hasNext() {
      return currNode != null;
    }

    @Override
    public Position next() {
      if (currNode == null) {
        throw new NoSuchElementException();
      } else {
        T currData = currNode.data;
        currNode = currNode.next;
        return currNode;
      }
    }
  }
}
