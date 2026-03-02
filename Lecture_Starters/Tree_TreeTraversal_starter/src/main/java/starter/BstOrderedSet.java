package starter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
//    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
//    bst.insert(7);
//    bst.insert(2);
//    bst.insert(13);
//    bst.insert(4);
//    bst.insert(10);
//    bst.insert(15);
//    bst.insert(5);
//    bst.insert(8);
//    bst.insert(11);
//    bst.insert(14);
//    bst.insert(17);
//
//    bst.remove(5);
//    bst.remove(2);
//    bst.remove(13);
//
//    System.out.println("Calling printInOrder: ");
//    bst.printInOrder();
//    System.out.println("\nUsing foreach loop (iterator): ");
//    for (int x : bst) {
//      System.out.print(x + " ");
//    }
//    System.out.println();
//
//    System.out.println("Height of bst: " + bst.height());

    int nodeCount = 10_000;
    int repetitions = 10;

    double optimalHeight = log2(nodeCount + 1) - 1;
    System.out.println("Optimal height: " + optimalHeight);

    for (int r = 0; r < repetitions; r++) {

      BstOrderedSet<Integer> bst2 = new BstOrderedSet<>();
      Random rand = new Random();
      for (int i = 0; i < nodeCount; i++) {
        bst2.insert(rand.nextInt());
      }
      int bst2Height = bst2.height();
      System.out.println("BST height: " + bst2Height);
      System.out.println("Ratio: " + bst2Height / optimalHeight);
    }

  }

  /* recursive find */
  private Node<T> find(Node<T> node, T data) {
    if (node == null) {
      return null;
    }
    if (node.data.compareTo(data) > 0) {
      return find(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      return find(node.right, data);
    } else {
      return node;
    }
  }

  @Override
  public void insert(T t) {
    // Uses a recursive (private) helper insert
    root = insert(root, t);
  }

  /* inserts given value into subtree rooted at given node
      and returns changed subtree with new node added. */
  private Node<T> insert(Node<T> node, T data) {
    if (node == null) {
      /* If the subtree is empty, return a new node */
      numElements++;
      return new Node<>(data);
    }

    /* Otherwise, recur down the tree */
    if (node.data.compareTo(data) > 0) {
      node.left = insert(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      node.right = insert(node.right, data);
    }

    /* return the (unchanged) node pointer */
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

  @Override
  public int size() {
    return numElements;
  }

  /**
   * Print elements in tree in order
   */
  public void printInOrder() {
    printInOrder(root);
  }

  //node: subtree root
  private void printInOrder(Node<T> node) {
    if (node != null) {
      printInOrder(node.left); //everything smaller than data
      System.out.print(node.data + " "); //data
      printInOrder(node.right); //everything bigger than data
    }
  }

  private void fillListInOrder(Node<T> node, List<T> list) {
    if (node != null) {
      fillListInOrder(node.left, list);
      list.add(node.data);
      fillListInOrder(node.right, list);
    }
  }

  /**
   * Get height of tree
   * @return integer height of tree
   */
  public int height() {
    return height(root);
  }

  private int height(Node<T> node) {
    if (node == null) {
      return -1; //by convention
    }
    return 1 + Math.max(height(node.left), height(node.right));
  }

  private static double log2(double d) {
    return Math.log(d) / Math.log(2);
  }

  @Override
  public Iterator<T> iterator() {
    //Auxilary data structure for iterator would have O(h) space complexity with h = height of tree
    //Worst case h = n (tree degenerates into linear structure) and average case h = log n for balanced
    List<T> list = new ArrayList<>();
    fillListInOrder(root, list);
    return list.iterator();
  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }
}
