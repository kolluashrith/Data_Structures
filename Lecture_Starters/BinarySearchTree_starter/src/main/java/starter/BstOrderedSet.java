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
    remove(root, t);
  }

  //node is root of subtree, always call as follows: subtree = remove(subtree, t);
  private Node<T> remove(Node<T> node, T t) {
    //If node is null, base case: nothing to remove (element not present)
    if (node != null) {
      int comparisonResult = t.compareTo(node.data);
      if (comparisonResult < 0) {
        node.left = remove(node.left, t);
      } else if (comparisonResult > 0) {
        node.right = remove(node.right, t);
      }  else {
        node = removeSubtreeRoot(node);
      }
    }
    return node;
  }

  private Node<T> removeSubtreeRoot(Node<T> node) {
    if  (node.left == null) {
      node = node.right;
      numElements--;
    } else if (node.right == null) {
      node = node.left;
      numElements--;
    } else { //General case, remove smallest value of right subtree and replace node.data with it
      node.right = popSmallest(node.right, node);
    }

    return node;
  }

  //call in the form subtree = popSmallest(subtree, receiveSmallest);
  private Node<T> popSmallest(Node<T> subtree, Node<T> receiveSmallest) {

    //base case: left child is null --> subtree root holds smallest value
    if (subtree.left == null) {
      receiveSmallest.data = subtree.data;
      subtree = subtree.right;
      numElements--;
    } else {
      subtree.left = popSmallest(subtree.left, receiveSmallest);
    }
    return subtree;
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

    bst.remove(13);
    bst.remove(23);
  }
}
