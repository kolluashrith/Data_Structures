package starter;

import java.util.Iterator;

/**
 * A Binary Search Tree implementation of OrderedSet ADT.
 *
 * @param <T> Element type.
 */
public class BstOrderedSet<T extends Comparable<T>> implements OrderedSet<T> {

  private Node<T> root;
  private int numElements;

  /**
   * Construct an empty BstOrderedSet.
   */
  public BstOrderedSet() {
    root = null;
    numElements = 0;
  }

  @Override
  public void insert(T t) {
    root = insert(root, t);
  }

  //Always call as follows: subtree = insert(subtree, data);
  private Node<T> insert(Node<T> node, T t) {
    if (node == null) {
      node = new Node<>(t);
      numElements++;
    } else {
      int comparsionResult = t.compareTo(node.data);
      if (comparsionResult < 0) {
        node.left = insert(node.left, t);
      } else if (comparsionResult > 0) {
        node.right = insert(node.right, t);
      }
      //else comparisonResult == 0, element is already present so nothing needs to be done
    }
    return node;
  }

  @Override
  public void remove(T t) {
    // TODO Implement me!
  }

  @Override
  public boolean has(T t) {
    return find(root, t) != null;
  }

  /**
   * Locate the node containing data element t in the subtree rooted at node
   * @param node root of the subtree in which to search
   * @param t the data element to search for
   * @return reference to node containing t or null if t is not present in subtree.
   */
  private Node<T> find(Node<T> node, T t) {
    if (node == null) {
      return null;
    } else {
      //Careful not to compare references unless that's actually your intention
      int comparisonResult = t.compareTo(node.data);
      if (comparisonResult == 0) {
        return node;
      } else if (comparisonResult < 0) {
        return find(node.left, t);
      } else {
        return find(node.right, t);
      }
    }
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement me!
    return null;
  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
    bst.insert(7);
    bst.insert(4);
    bst.insert(13);
    bst.insert(1);
    bst.insert(6);
    bst.insert(10);
    bst.insert(15);
    bst.insert(3);

    System.out.println(bst.size());
    System.out.println(bst.has(13));
    System.out.println(bst.has(23));
  }
}
