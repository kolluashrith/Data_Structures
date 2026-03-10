package hw4;

import java.util.Iterator;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

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
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    updateAll(n);
    if (Math.abs(n.balanceFactor) > 1) {
      n = handleRotations(n);
      updateAll(n); //updating new subtree root
    }

    return n;
  }

  private Node<K, V> handleRotations(Node<K, V> subtreeroot) {
    int bf = subtreeroot.balanceFactor;

    if (bf == -2) {
      if (subtreeroot.right.balanceFactor <= 0) { //simple left rotation
        subtreeroot = leftRotate(subtreeroot);
      }  else if (subtreeroot.right.balanceFactor == 1) { //right-left rotation
        subtreeroot = rightLeftRotate(subtreeroot);
      }
    } else if (bf == 2) {
      if (subtreeroot.left.balanceFactor >= 0) { //right rotation
        subtreeroot = rightRotate(subtreeroot);
      } else if (subtreeroot.left.balanceFactor == -1) { //left-right rotation
        subtreeroot = leftRightRotate(subtreeroot);
      }
    }
    return subtreeroot;
  }

  private Node<K, V> leftRightRotate(Node<K, V> subtreeroot) {
    subtreeroot.left = leftRotate(subtreeroot.left);
    updateAll(subtreeroot.left);
    subtreeroot = rightRotate(subtreeroot);
    updateAll(subtreeroot);

    return subtreeroot;
  }

  private Node<K, V> rightLeftRotate(Node<K, V> subtreeroot) {
    subtreeroot.right =  rightRotate(subtreeroot.right);
    updateAll(subtreeroot.right);
    subtreeroot = leftRotate(subtreeroot);
    updateAll(subtreeroot);

    return subtreeroot;
  }

  private Node<K, V> leftRotate(Node<K, V> subtreeroot) {
    Node<K, V> child = subtreeroot.right;
    subtreeroot.right = child.left;
    child.left = subtreeroot;
    subtreeroot = child;

    updateAll(subtreeroot.left); //update changed node

    return subtreeroot;
  }

  private Node<K, V> rightRotate(Node<K, V> subtreeroot) {
    Node<K, V> child = subtreeroot.left;
    subtreeroot.left = child.right;
    child.right = subtreeroot;
    subtreeroot = child;

    updateAll(subtreeroot.right); //update changed node

    return subtreeroot;
  }

  private void updateAll(Node<K, V> n) {
    updateHeight(n);
    updateBF(n);
  }

  private void updateBF(Node<K, V> n) {
    int leftBF = (n.left == null) ? -1 : n.left.height;
    int rightBF = (n.right == null) ? -1 : n.right.height;

    n.balanceFactor = leftBF - rightBF;
  }

  private void updateHeight(Node<K, V> n) {
    int leftHeight = (n.left == null) ? -1 : n.left.height;
    int rightHeight = (n.right == null) ? -1 : n.right.height;

    n.height = Math.max(leftHeight, rightHeight) + 1;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> node = findForSure(k);
    V value = node.value;
    root = remove(root, node);
    size--;
    return value;
  }

  private Node<K, V> remove(Node<K, V> subtreeRoot, Node<K, V> toRemove) {
    int cmp = subtreeRoot.key.compareTo(toRemove.key);
    if (cmp == 0) {
      return remove(subtreeRoot);
    } else if (cmp > 0) {
      subtreeRoot.left = remove(subtreeRoot.left, toRemove);
    } else {
      subtreeRoot.right = remove(subtreeRoot.right, toRemove);
    }

    updateAll(subtreeRoot);
    if (Math.abs(subtreeRoot.balanceFactor) > 1) {
      subtreeRoot = handleRotations(subtreeRoot);
      updateAll(subtreeRoot); //updating new subtree root
    }

    return subtreeRoot;
  }

  // Remove given node and return the remaining tree (structural change).
  private Node<K, V> remove(Node<K, V> node) {
    // Easy if the node has 0 or 1 child.
    if (node.right == null) {
      return node.left;
    } else if (node.left == null) {
      return node.right;
    }

    // If it has two children, find the predecessor (max in left subtree),
    Node<K, V> toReplaceWith = max(node);
    // then copy its data to the given node (value change),
    node.key = toReplaceWith.key;
    node.value = toReplaceWith.value;
    // then remove the predecessor node (structural change).
    node.left = remove(node.left, toReplaceWith);

    updateAll(node);
    if (Math.abs(node.balanceFactor) > 1) {
      node = handleRotations(node);
      updateAll(node); //updating new subtree root
    }

    return node;
  }

  // Return a node with maximum key in subtree rooted at given node.
  private Node<K, V> max(Node<K, V> node) {
    Node<K, V> curr = node.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
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
   * long as we use recursive insert/remove helpers.
   *
   * @param <K> Type for keys.
   * @param <V> Type for values.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height;
    int balanceFactor;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      height = 0;
      balanceFactor = 0;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }

    // Feel free to add whatever you want to the Node class (e.g. new fields).
    // Just avoid changing any existing names, deleting any existing variables, or modifying the overriding methods.
  }

  //Taken from BinarySearchTreeMap
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
}
