package starter;

import exceptions.EmptyException;

/**
 * Array Implementation of the Queue ADT.
 *
 * @param <T> base type.
 */
@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements Queue<T> {

  private static final int INIT_CAPACITY = 16;

  private T[] data;
  private int numElements;
  private int front;


  /**
   * Construct an ArrayQueue.
   */
  public ArrayQueue() {
    data = (T[]) new Object[INIT_CAPACITY];
    numElements = 0;
    front = 0;
  }

  @Override
  public void enqueue(T value) {
    if (numElements == data.length) {
      grow();
      //front should be reset to 0
    }
    //Use a circular array implementation
    data[(front + numElements) % data.length] = value;
    numElements++;
  }

  private void grow() {
    T[] newData = (T[]) new Object[data.length * 2];
    for (int i = 0; i < numElements; i++) {
      newData[i] = data[(front + i) % data.length];
    }
    data = newData;
    front = 0;
  }

  @Override
  public void dequeue() throws EmptyException {
    if (numElements == 0) {
      throw new EmptyException();
    } else {
      front = (front + 1) % data.length;
      numElements--;
    }
  }

  @Override
  public T front() throws EmptyException {
    if  (numElements == 0) {
      throw new EmptyException();
    } else {
      return data[front];
    }
  }

  @Override
  public boolean empty() {
    return numElements == 0;
  }
}
