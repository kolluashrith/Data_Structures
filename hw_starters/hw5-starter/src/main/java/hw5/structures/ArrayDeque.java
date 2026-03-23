package hw5.structures;

import hw5.exceptions.EmptyException;

/**
 * A circular-array implementation of a generic double‑ended queue (deque).
 * See {@link Deque} for details on the interface.
 * 
 * @param <T> the type of elements held in this deque
 */
public class ArrayDeque<T> implements Deque<T> {
  // NOTE: Do not remove or rename any of the provided fields.
  private static final int INITIAL_CAPACITY = 8;

  private T[] elements;
  private int first; // index of the front element
  private int numElements;

  /**
   * Initializes an empty deque with an initial capacity of 8.
   */
  @SuppressWarnings("unchecked")
  public ArrayDeque() {
    this.elements = (T[]) new Object[INITIAL_CAPACITY];
    this.first = 0;
    this.numElements = 0;
  }

  @Override
  public void addFirst(T element) {
    // Implement me!
  }

  @Override
  public void addLast(T element) {
    // Implement me!
  }

  @Override
  public T removeFirst() {
    // Implement me!
    return null;
  }

  @Override
  public T removeLast() {
    // Implement me!
    return null;
  }

  @Override
  public T peekFirst() {
    // Implement me!
    return null;
  }

  @Override
  public T peekLast() {
    // Implement me!
    return null;
  }

  @Override
  public int size() {
    // Implement me!
    return 0;
  }

  @Override
  public boolean isEmpty() {
    // Implement me!
    return false;
  }
}
