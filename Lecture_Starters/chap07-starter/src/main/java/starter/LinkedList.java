package starter;

import exceptions.IndexException;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO some of the sanity checks are convoluted and need to be refactored!
public class LinkedList<T> implements Iterable<T> {
  private Node<T> head;
  private int numElements;

  public LinkedList() {
    head = null;
    numElements = 0;
  }

  public void addFirst(T t) {
    Node<T> node = new Node<T>(t);
    node.next = head;
    head = node;
    numElements++;
  }

  public void addLast(T t) {

    if (head == null) {
      addFirst(t); //Adding last is same as adding first for empty lists
    }

    Node<T> tail = findNodeAtIndex(numElements - 1); //Last valid index
    tail.next = new Node<>(t);
    numElements++;
  }

  public void println() {
//    Node<T> curr = head;
//    while (curr != null) {
//      System.out.print(curr.data + " ");
//      curr = curr.next;
//    }

    //Alternative way to do this with the whole traversal component packaged
    for (Node<T> cur = head; cur != null; cur = cur.next) {
      System.out.print(cur.data + " ");
    }
  }

  //Create a method to find a node to prevent repeated code between get and put
  private Node<T> findNodeAtIndex (int index) throws IndexException {
    if (index < 0 || index >= numElements) {
      throw new IndexException();
    } else {
      Node<T> node = head;
      int counter = 0;
      while (counter < index) {
        node = node.next;
        counter++;
      }
      return node;
    }
  }

  public T get(int index) {
    return findNodeAtIndex(index).data;
  }


  public void insert(int index, T t) {
    if (index == 0) {
      addFirst(t);
    } else {
      Node<T> nodeBefore = findNodeAtIndex(index - 1);
      Node<T> nodeExisting = nodeBefore.next;
      nodeBefore.next = new Node<>(t);
      nodeBefore.next.next = nodeExisting;
      numElements++;
    }
  }

  public void delete(int index) throws IndexException {
    if (index == 0 && numElements == 0) {
      throw new IndexException(); //Problem if we try to remove something from an empty list
    } else if (index == 0) {
      head = head.next; //Handle case if we're removing from the front
      numElements--;
    } else {
      Node<T> nodeBefore = findNodeAtIndex(index - 1);
      nodeBefore.next = nodeBefore.next.next;
      numElements--;
    }
  }

  //iterator() method required when implementing Iterable
  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  public int length() {
    return numElements;  // TODO Implement me!
  }

  //Static indicates that this is a NESTED helper class that cannot access members from the class it's in
  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
    }
  }

  //No static, so this is an INNER class that can access stuff from the class it was instantiated in
  private class LinkedListIterator implements Iterator<T> {
    Node<T> cur;

    public LinkedListIterator() {
      cur = head;
    }
    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        T currentData = cur.data;
        cur = cur.next;
        return currentData;
      }
    }

    @Override
    public boolean hasNext() {
      return cur != null;     // TODO Implement me!
    }
  }

}
