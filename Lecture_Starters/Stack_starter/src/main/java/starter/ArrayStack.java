package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using an array.
 *
 * @param <T> base type.
 */
@SuppressWarnings("all")
public class ArrayStack<T> implements Stack<T> {
    private T[] data;
    private int numElements;

    private final static int INIT_CAPACITY = 16;
    /**
     * Construct an ArrayStack.
     */
    public ArrayStack() {
        numElements = 0;
        data = (T[]) new Object[INIT_CAPACITY];
    }

    @Override
    public boolean empty() {
        return numElements == 0;
    }

    @Override
    public T top() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        } else {
            return data[numElements-1];
        }
    }

    @Override
    public void pop() throws EmptyException {
        if (!empty()) {
            data[numElements - 1] = null; //Optional
            numElements--;
        } else  {
            throw new EmptyException();
        }

    }

    @Override
    public void push(T value) {
        if (numElements == data.length) {
            grow();
        }
        data[numElements] = value;
        numElements++;
    }

    private void grow() {

        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < numElements; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
