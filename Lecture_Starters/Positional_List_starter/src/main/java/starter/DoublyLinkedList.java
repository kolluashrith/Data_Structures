package starter;

import exceptions.PositionException;

public class DoublyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  private static class Node<E> implements Position<E> {
    E data;
    Node<E> next;
    Node<E> prev;
    DoublyLinkedList<E> owner;

    Node(E data, DoublyLinkedList<E> owner) {
      this.data = data;
      this.owner = owner;
    }

    @Override
    public E get() {
      return data;
    }
  }

  public DoublyLinkedList() {
    head = new Node<>(null, this); //Sentinel Nodes to remove edge case handling
    tail = new Node<>(null, this);
    head.next = tail;
    tail.prev = head;
    numElements = 0;
  }

  public boolean isEmpty() {
    return head == null;
  }
  public Position<T> addFirst (T data) {
    Node<T> newNode = new Node<>(data, this);

    newNode.next = head.next;
    head.next = newNode;

    numElements++;
    return newNode;
  }

  //Need to edit this method and everything below to account for sentinel nodes
  public Position<T> addLast(T data) {
    Node<T> newNode = new Node<>(data, this);
    if (isEmpty()) {
      return addFirst(data);
    } else {
      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;
      numElements++;
      return newNode;
    }
  }

  public Position<T> get(int index) {
    Node<T> target = tail;
    for (int i = numElements-1; i > index; i--) {
      target = target.prev;
    }
    return target;
  }

  private Node<T> convert(Position<T> position) throws PositionException {
    try {
      Node<T> node = (Node<T>) position;
      if (node.owner != this) {
        throw new PositionException();
      }
      return node;
    } catch (NullPointerException | ClassCastException e) {
      throw new PositionException();
    }
  }

  public void delete (Position<T> targetPosition) throws PositionException {

    Node<T> target = convert(targetPosition);

    if (target.prev == null) {
      head = target.next;
      target.next.prev = null;
    }
    if (target.next == null) {
      tail = target.prev;
      target.prev.next = null;
    }
    if (target.prev != null && target.next != null) {
      Node<T> prevNode = target.prev;
      Node<T> nextNode = target.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
    }

    target.owner = null; //Make sure the Node can never be used as an argument again
    numElements--;
  }

  public void insertAfter(Position<T> position, T data) throws PositionException {
    Node<T> target = convert(position);
    if (target == tail) {
      addLast(data);
    } else {
      Node<T> newNode = new Node<>(data, this);
      newNode.next = target.next;
      target.next.prev = newNode;
      target.next = newNode;
      newNode.prev = target;
      numElements++;
    }
  }

  public void insertBefore(Position<T> position, T data) throws PositionException {
    Node<T> target = convert(position);
    if (target == head) {
      addFirst(data);
    } else {
      Node<T> newNode = new Node<>(data, this);
      newNode.prev = target.prev;
      newNode.prev.next = newNode;
      target.prev = newNode;
      newNode.next = target;
      numElements++;
    }
  }

}
