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
        // TODO: Implement this
        return false;
    }

    @Override
    public void setCell(int row, int col, boolean isOpen) {
        int targetIndex = row*width + col;
        
    }

    @Override
    public void clear(boolean defaultValue) {
        this.defaultValue = defaultValue;
        head.next = null;
        storedCellCount = 0;
    }

    @Override
    public Iterator<Boolean> iterator() {
        // TODO: Implement this
        return null;
    }

}
