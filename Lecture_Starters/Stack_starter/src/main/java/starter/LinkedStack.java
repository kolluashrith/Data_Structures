package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using linked nodes.
 *
 * @param <T> base type.
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> list;

    /**
     * Construct an ListStack.
     */
    public LinkedStack() {
        list = null;
    }

    @Override
    public boolean empty() {
        return false; // TODO: Implement me!
    }

    @Override
    public T top() throws EmptyException {
        return null; // TODO: Implement me!
    }

    @Override
    public void pop() throws EmptyException {
        // TODO: Implement me!
    }

    @Override
    public void push(T t) {
        // TODO: Implement me!
    }

    private static class Node<T> {
        T data;
        Node<T> next;
    }
}
