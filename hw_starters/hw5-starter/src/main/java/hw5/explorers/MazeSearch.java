package hw5.explorers;

import hw5.structures.Maze;
import hw5.structures.MazeCell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An abstract base class for maze search algorithms.
 * This class implements the template method pattern, providing a generic maze search
 * algorithm that can be specialized by subclasses.
 * The specific behavior is determined by the Frontier data structure
 * provided by concrete subclasses.
 */
public abstract class MazeSearch {
  protected final Maze maze;
  protected Frontier<MazeCell> frontier;

  /**
   * Constructor that initializes the maze to be searched.
   *
   * @param maze The maze instance to be explored.
   */
  public MazeSearch(Maze maze) {
    this.maze = maze;
  }

  /**
   * A protected interface that defines the contract for a frontier data structure.
   * This abstraction allows the search algorithm to be independent of the
   * specific stack or queue implementation.
   * @param <T> generic type for frontier cells
   */
  protected interface Frontier<T> {
    /**
     * Add a new element to the frontier.
     * @param element addition
     */
    void add(T element);

    /**
     * Remove element from the frontier.
     * @return removed element
     */
    T remove();

    /**
     * Determine if any elements are in the frontier.
     * @return if frontier is empty
     */
    boolean isEmpty();

    /**
     * Get the number of elements in the frontier.
     * @return count
     */
    int size();
  }

  /**
   * Abstract method that concrete subclasses must implement to provide a specific
   * frontier data structure (e.g., a stack-based or queue-based one).
   * @return An implementation of the Frontier interface.
   */
  protected abstract Frontier<MazeCell> createFrontier();

  /**
   * Finds a path from a start to a goal cell using the traversal strategy
   * defined by the Frontier implementation.
   *
   * @param start The starting cell.
   * @param goal The goal cell.
   * @return A list of cells representing the path, or an empty list if no path is found.
   */
  public final List<MazeCell> findPath(MazeCell start, MazeCell goal) {
    frontier = createFrontier();
    Map<MazeCell, MazeCell> predecessors = new HashMap<>();
    Set<MazeCell> visited = new HashSet<>();

    frontier.add(start);
    visited.add(start);
    predecessors.put(start, null);

    boolean found = false;
    while (!frontier.isEmpty()) {
      MazeCell current = frontier.remove();
      current.setVisited(true);

      if (current.equals(goal)) {
        found = true;
        break;
      }

      visitNeighbors(current, predecessors, visited);
    }

    return reconstructPath(predecessors, start, goal, found);
  }

  private void visitNeighbors(MazeCell current, Map<MazeCell, MazeCell> predecessors, Set<MazeCell> visited) {
    List<MazeCell> neighbors = maze.getNeighbors(current);
    for (MazeCell neighbor : neighbors) {
      if (neighbor != null && !visited.contains(neighbor)) {
        visited.add(neighbor);
        predecessors.put(neighbor, current);
        frontier.add(neighbor);
      }
    }
  }


  // Reconstructs the path from the goal back to the start using the predecessors map.
  private List<MazeCell> reconstructPath(Map<MazeCell, MazeCell> predecessors, MazeCell start,
      MazeCell goal, boolean found) {
    List<MazeCell> path = new ArrayList<>();
    if (!found) {
      return path;
    }
    MazeCell current = goal;
    while (current != null) {
      path.add(0, current);
      if (current.equals(start)) {
        break;
      }
      current = predecessors.get(current);
    }
    return path;
  }
}
