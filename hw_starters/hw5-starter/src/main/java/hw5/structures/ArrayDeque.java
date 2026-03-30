package hw5.structures;

import hw5.exceptions.EmptyException;

/**
 * A circular-array implementation of a generic double‑ended queue (deque).
 * See {@link Deque} for details on the interface.
 * 
 * @param <T> the type of elements held in this deque
 */
@SuppressWarnings("unchecked")
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
    if (numElements == elements.length) {
      grow();
    }
    int indexToInsert = (first - 1 + elements.length) % elements.length; //Add elements.length to ensure positive
    elements[indexToInsert] = element;
    numElements++;
    first = indexToInsert;
  }

  @Override
  public void addLast(T element) {
    if (numElements == elements.length) {
      grow();
    }
    int indexToInsert = (first + numElements) % elements.length;
    elements[indexToInsert] = element;
    numElements++;
  }

  private void grow() {

    T[] newElements = (T[]) new Object[elements.length * 2];

    for (int i = 0; i < elements.length; i++) {
      newElements[i] = elements[(first + i) % elements.length];
    }
    elements = newElements;
    first = 0;

  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new EmptyException();
    }


    numElements--; //Had to move this up here to get around checkstyle issue, but does not affect logic
    T elementRemoved = elements[first];

    elements[first] = null;
    first = (first + 1) % elements.length;

    return elementRemoved;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new EmptyException();
    }
    T elementRemoved = elements[(first + numElements - 1)  % elements.length];

    elements[(first + numElements - 1)  % elements.length] = null;

    numElements--;

    return elementRemoved;
  }

  @Override
  public T peekFirst() {
    if (isEmpty()) {
      throw new EmptyException();
    }
    return elements[first];
  }

  @Override
  public T peekLast() {
    if (isEmpty()) {
      throw new EmptyException();
    }
    return elements[(first + numElements - 1)  % elements.length];
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public boolean isEmpty() {
    return numElements == 0;
  }
}
