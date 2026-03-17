package hw4;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Map implemented as a Treap.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'rand'. ***/
  private static Random rand;
  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;

  private int size;

  /**
   * Make a TreapMap.
   */
  public TreapMap() {
    rand = new Random();
    size = 0;
  }

  /**
   * TreapMap overloaded constructor for testing purposes.
   * @param test pass any boolean to indicate testing
   */
  public TreapMap(boolean test) {
    rand = new FakeRandom();
    size = 0;
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    root = insert(root, k, v);
    size++;
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new Node<>(k, v);
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
      if (n.left.priority < n.priority) {
        n = rightRotate(n);
      }
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
      if (n.right.priority < n.priority) {
        n = leftRotate(n);
      }
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    return n;
  }

  private Node<K, V> leftRotate(Node<K, V> subtreeroot) {
    Node<K, V> child = subtreeroot.right;
    subtreeroot.right = child.left;
    child.left = subtreeroot;
    subtreeroot = child;

    return subtreeroot;
  }

  private Node<K, V> rightRotate(Node<K, V> subtreeroot) {
    Node<K, V> child = subtreeroot.left;
    subtreeroot.left = child.right;
    child.right = subtreeroot;
    subtreeroot = child;

    return subtreeroot;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {

    Node<K, V> node = findForSure(k);

    V value = node.value;
    root = remove(root, k);
    size--;
    return value;
  }

  //Recurse into tree to remove node
  private Node<K, V> remove(Node<K, V> subtreeRoot, K key) {

    int cmp = key.compareTo(subtreeRoot.key);

    if (cmp < 0) {
      subtreeRoot.left = remove(subtreeRoot.left, key);
    } else if  (cmp > 0) {
      subtreeRoot.right = remove(subtreeRoot.right, key);
    } else {
      subtreeRoot.priority = Integer.MAX_VALUE;
      //Treap-specific handling of priorities while removing
      subtreeRoot = removeWithRotations(subtreeRoot, key);

    }
    return subtreeRoot;
  }

  //Treap-specific handling of priorities while removing
  private Node<K, V> removeWithRotations(Node<K, V> nodeToRemove, K key) {

    //Base case when the node to remove becomes a leaf
    if (nodeToRemove.left == null && nodeToRemove.right == null) {
      return null;
    } else if (nodeToRemove.left == null) {
      //If only right child exists, rotate left and remove from left
      nodeToRemove = leftRotate(nodeToRemove);
      nodeToRemove.left = remove(nodeToRemove.left, key);
    } else if (nodeToRemove.right == null) {
      //If only left child exists, rotate right and remove from right
      nodeToRemove = rightRotate(nodeToRemove);
      nodeToRemove.right = remove(nodeToRemove.right, key);
    } else {
      //Two children exist, need to rotate in direction of higher priority child
      if (nodeToRemove.left.priority < nodeToRemove.right.priority) {
        nodeToRemove = rightRotate(nodeToRemove);
        nodeToRemove.right = remove(nodeToRemove.right, key);
      } else {
        nodeToRemove = leftRotate(nodeToRemove);
        nodeToRemove.left = remove(nodeToRemove.left, key);
      }
    }
    return nodeToRemove;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    return n.value;
  }

  // Return node for given key,
  // throw an exception if the key is not in the tree.
  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  // Return node for given key.
  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    Node<K, V> n = root;
    while (n != null) {
      int cmp = k.compareTo(n.key);
      if (cmp < 0) {
        n = n.left;
      } else if (cmp > 0) {
        n = n.right;
      } else {
        return n;
      }
    }
    return null;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }


  /**
   * Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers. Since this is
   * a node class for a Treap we also include a priority field.
   *
   * @param <K> Type for keys.
   * @param <V> Type for values.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int priority;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      priority = generateRandomInteger();
    }

    // Use this function to generate random values
    // to use as node priorities as you insert new
    // nodes into your TreapMap.
    private int generateRandomInteger() {
      // Note: do not change this function!
      return rand.nextInt();
    }

    @Override
    public String toString() {
      return key + ":" + value + ":" + priority;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }

  }

  // Iterative in-order traversal over the keys
  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  private static class FakeRandom extends Random {
    private final int[] values;
    private int curr;

    FakeRandom() {
      values = new int[]{16, 3, 2, 13, 6, 18, 17, 10, 15, 1, 14, 7, 4, 9, 12, 5, 8, 11, 19};
      curr = 0;
    }

    @Override
    public int nextInt() {
      //Allows for integer array looping so we don't run out numbers to "randomly" generate
      if (curr >= values.length) {
        curr = 0;
      }
      return values[curr++];
    }
  }

  // Feel free to add whatever you want to the Node class (e.g. new fields).
  // Just avoid changing any existing names, deleting any existing variables, or modifying the overriding methods.
}
