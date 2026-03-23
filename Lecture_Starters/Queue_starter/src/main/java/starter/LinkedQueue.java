package starter;

import exceptions.EmptyException;

/**
 * Linked implementation of Queue ADT.
 *
 * @param <T> base type.
 */
public class LinkedQueue<T> implements Queue<T> {

  private Node<T> head;
  private Node<T> tail;

  @Override
  public void enqueue(T value) {
    if (head == null) {
      head = new Node<>();
      head.value = value;
      tail = head;
    } else {
      tail.next = new Node<>();
      tail.next.value = value;
      tail = tail.next;
    }
  }

  @Override
  public void dequeue() throws EmptyException {
    if (head == null) {
      throw new EmptyException();
    } else {
      head = head.next;
    }
  }

  @Override
  public T front() throws EmptyException {
    if (head == null) {
      throw new EmptyException();
    } else {
      return head.value;
    }
  }

  @Override
  public boolean empty() {
    return head == null;
  }

  private static class Node<T> {
    private T value;
    private Node<T> next;
  }
}
