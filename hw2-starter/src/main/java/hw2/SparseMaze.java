package hw2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import exceptions.CellIndexOutOfBoundsException;
import exceptions.DimensionException;

/**
 * Sparse maze implementation using a linked list to store non-default cell values.
 */
@SuppressWarnings("all")
public class SparseMaze implements Maze {
    protected Node head; // Sentinel node
    private final int width;
    private final int height;
    private boolean defaultValue;

    // You should maintain this count of stored (non-default) cells
    // So every time you add or remove a stored cell (i.e., a node), update this count.
    protected int storedCellCount;

    /**
     * Node in the sparse linked list storing non-default cell values.
     * Uses linear indexing for ordered storage and efficient traversal.
     */
    protected static class Node {
        public boolean value;
        public int linearIndex;
        public Node next;

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
     * @param defaultValue the default value for cells in the maze
     *        true for open, false for blocked
     * @throws DimensionException if width or height are non-positive
     */
    public SparseMaze(int width, int height, boolean defaultValue) {
        this.width = width;
        this.height = height;
        this.defaultValue = defaultValue;
        this.storedCellCount = 0;
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
        Node oneBefore = goToOneBefore(row, col);
        int targetIndex = row*width + col;
        if (oneBefore.next != null && oneBefore.next.linearIndex == targetIndex) {
            return oneBefore.next.value;
        } else {
            return defaultValue;
        }
    }

    @Override
    public void setCell(int row, int col, boolean isOpen) {
        Node oneBefore = goToOneBefore(row, col);
        int targetIndex = row*width + col;
        boolean nodeNeeded = (defaultValue != isOpen);
        if (nodeNeeded) {

            //Make sure we aren't at the end of the Node list
            if (oneBefore.next != null) {

                //Handle case that Node already exists
                if (oneBefore.next.linearIndex == targetIndex) {
                    oneBefore.next.value = isOpen;
                    return;
                } else { //Handles case we need a new node
                    Node newNode = new Node(isOpen, targetIndex);
                    newNode.next = oneBefore.next;
                    oneBefore.next = newNode;
                    storedCellCount++;
                    return;
                }
            } else { //Handles case that we are at end of node list
                Node newNode = new Node(isOpen, targetIndex);
                oneBefore.next = newNode;
                storedCellCount++;
                return;
            }
        } else { //Case that we need to delete nodes
            if (oneBefore.next == null) {
                return; //No action needed if at end of list, implying there is no node
            } else if (oneBefore.next.linearIndex == targetIndex) {
                //Need to remove node if node exists at location
                oneBefore.next = oneBefore.next.next;
                storedCellCount--;
                return;
            } else {
                return; //No action needed if no Node exists at coordinate
            }
        }
    }

    //Helper function

    /**
     * Helper function to get reference to the node before the expected node at a coordinate
     * @param row - the row coordinate, (0-indexed)
     * @param col - the col coordinate, (0-indexed)
     * @return reference to Node before at coordinate, regardless of whether or not a node exists at the coordinate
     */
    private Node goToOneBefore(int row, int col) {
        int targetIndex = row*width + col;
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
    public void clear(boolean defaultValue) {
        this.defaultValue = defaultValue;
        head.next = null;
        storedCellCount = 0;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new SparseMazeIterator();
    }

    private class SparseMazeIterator implements Iterator<Boolean> {

        Node cur;
        
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Boolean next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Boolean currentData = cur.value;
                cur = cur.next;
                return currentData;
            }
        }
    }

}
