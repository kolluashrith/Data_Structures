package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using an array.
 *
 * @param <T> base type.
 */
public class ArrayStack<T> implements Stack<T> {
    private T[] data;
    private int numElements;

    /**
     * Construct an ArrayStack.
     */
    public ArrayStack() {
        // TODO: Implement me!
    }

    @Override
    public boolean empty() {
        // TODO: Implement me!
        return false;
    }

    @Override
    public T top() throws EmptyException {
        // TODO: Implement me!
        return null;
    }

    @Override
    public void pop() throws EmptyException {
        // TODO: Implement me!
    }

    @Override
    public void push(T value) {
        // TODO: Implement me!
    }

    private void grow() {
        // TODO: Implement me!
    }
}
