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
    deque.addFirst(element);
  }

  @Override
  public void pop() {
    deque.removeFirst();
  }

  @Override
  public T top() {
    return deque.peekFirst();
  }

  @Override
  public boolean isEmpty() {
    return deque.isEmpty();
  }

  @Override
  public int size() {
    return deque.size();
  }
}
