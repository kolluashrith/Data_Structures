package hw2;

import exceptions.CellIndexOutOfBoundsException;
import exceptions.DimensionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sparse maze implementation using a linked list to store non-default cell values.
 */
public class SparseMaze implements Maze {
  protected Node head; // Sentinel node

  // You should maintain this count of stored (non-default) cells
  // So every time you add or remove a stored cell (i.e., a node), update this count.
  protected int storedCellCount;

  private final int width;
  private final int height;
  private boolean defaultValue;

  /**
   * Node in the sparse linked list storing non-default cell values.
   * Uses linear indexing for ordered storage and efficient traversal.
   */
  protected static class Node {
    public boolean value;
    public int linearIndex;
    public Node next;

    /**
     *Node inner class to hold location of individual non-default cells in maze.
     * @param value the boolean value (non-default) of the stored cell
     * @param linearIndex the flattened index at which cell exists (row * width + col)
     */
    public Node(boolean value, int linearIndex) {
      this.value = value;
      this.linearIndex = linearIndex;
      next = null;
    }
  }

  /**
   * Constructs a SparseMaze with specified dimensions and default cell value.
   * 
   * @param width the width of the maze
   * @param height the height of the maze
   * @param defaultValue the default value for cells in the maze - true for open, false for blocked
   * @throws DimensionException if width or height are non-positive
   */
  public SparseMaze(int width, int height, boolean defaultValue) throws DimensionException {

    if (height <= 0 || width <= 0) {
      throw new DimensionException(width, height);
    }

    this.width = width;
    this.height = height;
    this.defaultValue = defaultValue;
    this.storedCellCount = 0;
    this.head = new Node(false, -1);
    //Hopefully this constructor is done
  }

  /**
   * Constructs a SparseMaze with specified dimensions and default cell value of true (open).
   * 
   * @param width the width of the maze
   * @param height the height of the maze
   * @throws DimensionException if width or height are non-positive
   */
  public SparseMaze(int width, int height) {
    this(width, height, true);
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public boolean getDefaultValue() {
    return defaultValue;
  }

  @Override
  public boolean isOpen(int row, int col) {

    if (row < 0 || row >= height || col >= width || col < 0) {
      throw new CellIndexOutOfBoundsException(row, col, width, height);
    }

    Node nodeOneBefore = goToOneBefore(row, col);
    int targetIndex = row * width + col;
    if (nodeOneBefore.next != null && nodeOneBefore.next.linearIndex == targetIndex) {
      return nodeOneBefore.next.value;
    } else {
      return defaultValue;
    }
  }

  @Override
  public void setCell(int row, int col, boolean isOpen) {

    if (row < 0 || row >= height || col >= width || col < 0) {
      throw new CellIndexOutOfBoundsException(row, col, width, height);
    }

    Node oneBefore = goToOneBefore(row, col);
    int targetIndex = row * width + col;
    boolean nodeNeeded = (defaultValue != isOpen);

    if (nodeNeeded) { //Case we need to add a node to store non-default value
      handleNodeNeeded(targetIndex, oneBefore, isOpen);
    } else { //Case that we need to delete nodes
      handleNodeDelete(targetIndex, oneBefore, isOpen);
    }
    return;
  }

  /**
   * Helper function to implement algorithm to add a node when needed.
   * @param targetIndex the index (row + width + col) corresponding to the cell that is being changed
   * @param oneBefore the reference to the node right before where a node with an index of the targetIndex would exist
   * @param isOpen true to make the cell open, false to block it
   */
  private void handleNodeNeeded(int targetIndex, Node oneBefore, boolean isOpen) {
    if (oneBefore.next != null) {  //Make sure we aren't at the end of the Node list

      if (oneBefore.next.linearIndex == targetIndex) { //Handle case that Node already exists
        oneBefore.next.value = isOpen;
      } else { //Handles case we need a new node
        Node newNode = new Node(isOpen, targetIndex);
        newNode.next = oneBefore.next;
        oneBefore.next = newNode;
        storedCellCount++;
      }
    } else { //Handles case that we are at end of node list
      oneBefore.next = new Node(isOpen, targetIndex);
      storedCellCount++;
    }
    return;
  }

  /**
   * Helper function to implement algorithm to delete a node when needed.
   * @param targetIndex the index (row + width + col) corresponding to the cell that is being changed
   * @param oneBefore the reference to the node right before where a node with an index of the targetIndex would exist
   * @param isOpen true to make the cell open, false to block it
   */
  private void handleNodeDelete(int targetIndex, Node oneBefore, boolean isOpen) {
    if (oneBefore.next == null) {
      return; //No action needed if at end of list, implying there is no node
    } else if (oneBefore.next.linearIndex == targetIndex) {
      //Need to remove node if node exists at location
      oneBefore.next = oneBefore.next.next;
      storedCellCount--;
    } //No action needed if no Node exists at coordinate
    return;
  }

  /**
   * Helper function to get reference to the node before the expected node at a coordinate.
   * @param row - the row coordinate, (0-indexed)
   * @param col - the col coordinate, (0-indexed)
   * @return reference to Node before at coordinate, regardless of whether or not a node exists at the coordinate
   */
  private Node goToOneBefore(int row, int col) {
    int targetIndex = row * width + col;
    Node currNode = head;
    while (currNode.next != null) {
      if (targetIndex > currNode.next.linearIndex) {
        currNode = currNode.next;
      } else {
        return currNode;
      }
    }
    return currNode; //This statement only runs if targetIndex is past all existing Nodes, returning last node
  }

  @Override
  public void clear(boolean setdefaultValue) {
    this.defaultValue = setdefaultValue;
    head.next = null;
    storedCellCount = 0;
  }

  @Override
  public Iterator<Boolean> iterator() {
    return new SparseMazeIterator();
  }

  private class SparseMazeIterator implements Iterator<Boolean> {

    int mazeCursor;
    Node nodeCursor;

    SparseMazeIterator() {
      mazeCursor = 0;
      nodeCursor = head.next;
    }

    @Override
    public boolean hasNext() {
      return (mazeCursor < height * width);
    }

    @Override
    public Boolean next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        if ((nodeCursor == null) || (nodeCursor.linearIndex > mazeCursor)) {
          mazeCursor++;
          return defaultValue;
        } else { //Case when nodeCursor.linearIndex == mazeCursor
          mazeCursor++;
          Boolean value = nodeCursor.value;
          nodeCursor = nodeCursor.next;
          return value;
        }
      }
    }
  }
}