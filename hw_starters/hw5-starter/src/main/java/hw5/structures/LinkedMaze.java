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
  public LinkedMaze(int width, int height) throws IllegalArgumentException {
    this.width = width;
    this.height = height;

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }

    this.topLeft = new LinkedMazeCell(0, 0);
    createRowZero(topLeft);

    int currRow = 0;
    LinkedMazeCell currRowBase = topLeft;

    while (currRow < width - 1) {
      createNextRow(currRowBase);
      currRowBase = currRowBase.south;
      currRow++;
    }

    // Hint: When you construct the maze, for each cell you create,
    // you must make sure to set its pointers to its neighbors.
    // Likewise, you must set the neighbors' pointers back to this cell.
    // This is crucial for the maze to function correctly.
    // The complexity of this constructor should be O(width * height).
    // with an auxiliary space complexity of O(1).
  }

  //Creates top row of maze
  private void createRowZero(LinkedMazeCell zeroCell) {
    int curr = 1;
    LinkedMazeCell currCell = zeroCell;
    while (curr < width) {
      currCell.east = new LinkedMazeCell(0, curr);
      currCell.east.west = currCell;
      currCell = currCell.east;
      curr++;
    }
  }

  //Create subsequent rows of maze
  private void createNextRow(LinkedMazeCell baseCell) {
    int nextRowIndex = baseCell.row + 1;
    LinkedMazeCell currCellAbove = baseCell;
    int curr = 1;

    baseCell.south = new LinkedMazeCell(nextRowIndex, 0);
    baseCell.south.north = baseCell;
    LinkedMazeCell currCell = baseCell.south;

    while (curr < width) {
      currCell.east = new LinkedMazeCell(nextRowIndex, curr);
      currCell.east.west = currCell;
      currCell.east.north = currCellAbove.east;
      currCellAbove.east.south = currCell.east;

      currCell = currCell.east;
      currCellAbove = currCellAbove.east;
      curr++;
    }
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
  public MazeCell getCell(int row, int col) {
    if (row < 0 || col < 0 || row >= height || col >= width) {
      throw new IndexOutOfBoundsException();
    }

    LinkedMazeCell cellPointer = topLeft;
    for (int i = 0; i < row; i++) {
      cellPointer = cellPointer.south;
    }
    for (int i = 0; i < col; i++) {
      cellPointer = cellPointer.east;
    }
    return cellPointer;
  }

  @Override
  public boolean isWall(int row, int col) {
    if (row < 0 || col < 0 || row >= height || col >= width) {
      throw new IndexOutOfBoundsException();
    }

    MazeCell cell = getCell(row, col);
    return cell.isWall();
  }

  @Override
  public void setWall(int row, int col, boolean isWall) {
    if (row < 0 || col < 0 || row >= height || col >= width) {
      throw new IndexOutOfBoundsException();
    }

    MazeCell cell = getCell(row, col);
    cell.setWall(isWall);
    return;
  }

  @Override
  public List<MazeCell> getNeighbors(MazeCell cell) {
    //TODO: Complete this implementation!
    List<MazeCell> neighbors = new ArrayList<>(4);
    return neighbors;
  }

  @Override
  public void resetVisited() {

    LinkedMazeCell currCellRowBase = topLeft;
    while (currCellRowBase != null) {
      LinkedMazeCell currCell = currCellRowBase;

      while (currCell != null) {
        currCell.setVisited(false);
        currCell = currCell.east;
      }
      currCellRowBase = currCellRowBase.south;
    }
  }
}
