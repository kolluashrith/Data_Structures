package hw5.adapters;

import hw5.structures.Deque;

/**
 * Stack ADT implementation using a Deque.
 *
 * @param <T> base type.
 */
public class DequeStack<T> implements Stack<T> {
  // NOTE: Do not remove or rename the provided field (nor add any new fields).
  private final Deque<T> deque;

  /**
   * Constructs a DequeStack with the provided Deque.
   *
   * @param deque the Deque to be used as the underlying storage.
   */
  public DequeStack(Deque<T> deque) {
    this.deque = deque;
  }

  @Override
  public void push(T element) {
    // TODO: Implement me!
  }

  @Override
  public void pop() {
    // TODO: Implement me!
  }

  @Override
  public T top() {
    // TODO: Implement me!
    return null;
  }

  @Override
  public boolean isEmpty() {
    // TODO: Implement me!
    return true;
  }

  @Override
  public int size() {
    // TODO: Implement me!
    return 0;
  }
}
