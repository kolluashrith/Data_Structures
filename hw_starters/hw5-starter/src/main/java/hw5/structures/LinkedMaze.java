package hw5.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * A linked-list implementation of a rectangular maze.
 * See {@link Maze} for details on the interface.
 */
public class LinkedMaze implements Maze {
  // NOTE: Do not remove or rename any of the provided fields.
  private final int width;
  private final int height;
  private final LinkedMazeCell topLeft;

  // A private, nested implementation of the MazeCell interface.
  // This class contains the actual grid structure and pointers.
  private static class LinkedMazeCell implements MazeCell {
    // NOTE: Do not remove or rename any of the provided fields.
    final int row;
    final int col;
    boolean isWall;
    boolean isVisited;

    LinkedMazeCell north;
    LinkedMazeCell south;
    LinkedMazeCell east;
    LinkedMazeCell west;

    LinkedMazeCell(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean isWall() {
      return isWall;
    }

    @Override
    public void setWall(boolean isWallIn) {
      this.isWall = isWallIn;
    }

    @Override
    public boolean isVisited() {
      return isVisited;
    }

    @Override
    public void setVisited(boolean visited) {
      this.isVisited = visited;
    }

    // You may add additional methods here if needed
    // But do not add any new fields
  }

  /**
   * Creates a linked maze with the specified dimensions. 
   *
   * @param width the number of columns in the maze, must be positive
   * @param height the number of rows in the maze, must be positive
   * @throws IllegalArgumentException if width or height is non-positive
   */
  public LinkedMaze(int width, int height) {
    // TODO: Update this implementation
    this.width = width;
    this.height = height;
    this.topLeft = null;
    // Hint: When you construct the maze, for each cell you create,
    // you must make sure to set its pointers to its neighbors.
    // Likewise, you must set the neighbors' pointers back to this cell.
    // This is crucial for the maze to function correctly.
    // The complexity of this constructor should be O(width * height).
    // with an auxiliary space complexity of O(1).
  }

  @Override
  public int getWidth() {
    // TODO: Implement me!
    return 0;
  }

  @Override
  public int getHeight() {
    // TODO: Implement me!
    return 0;
  }

  @Override
  public MazeCell getCell(int row, int col) {
    // TODO: Implement me!
    return null;
  }

  @Override
  public boolean isWall(int row, int col) {
    // TODO: Implement me!
    return false;
  }

  @Override
  public void setWall(int row, int col, boolean isWall) {
    // TODO: Implement me!
  }

  @Override
  public List<MazeCell> getNeighbors(MazeCell cell) {
    List<MazeCell> neighbors = new ArrayList<>(4);
    // TODO: Complete this implementation!
    return neighbors;
  }

  @Override
  public void resetVisited() {
    // TODO: Implement me!
  }
}
