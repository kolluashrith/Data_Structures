package hw5.explorers;

import hw5.adapters.DequeQueue;
import hw5.adapters.Queue;
import hw5.structures.ArrayDeque;
import hw5.structures.Maze;
import hw5.structures.MazeCell;

/**
 * A search algorithm that explores the maze using a queue-based (FIFO) approach.
 * It extends the abstract MazeSearch class and provides a queue-based frontier.
 */
public class QueueBasedMazeSearch extends MazeSearch {

  /**
   * Constructor that initializes the maze to be searched.
   *
   * @param maze The maze instance to be explored.
   */
  public QueueBasedMazeSearch(Maze maze) {
    super(maze);
  }

  /**
   * Private inner class implementing the Frontier interface with a DequeQueue.
   */
  private static class QueueFrontier implements Frontier<MazeCell> {
    private final Queue<MazeCell> queue = new DequeQueue<>(new ArrayDeque<>());

    @Override
    public void add(MazeCell element) {
      queue.enqueue(element);
    }

    @Override
    public MazeCell remove() {
      MazeCell front = queue.front();
      queue.dequeue();
      return front;
    }

    @Override
    public boolean isEmpty() {
      return queue.isEmpty();
    }

    @Override
    public int size() {
      return queue.size();
    }
  }

  @Override
  protected Frontier<MazeCell> createFrontier() {
    return new QueueFrontier();
  }
}
