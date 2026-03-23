package hw5.maze;

import hw5.structures.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract base class for testing implementations of the Maze interface.
 */
public abstract class MazeTest {

    protected Maze maze;
    protected final int DEFAULT_WIDTH = 10;
    protected final int DEFAULT_HEIGHT = 8;

    /**
     * Factory method for creating an instance of the Maze implementation under test.
     * @param width The width of the maze.
     * @param height The height of the maze.
     * @return A new maze instance.
     */
    public abstract Maze createMaze(int width, int height);

    @BeforeEach
    public void setup() {
        maze = createMaze(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Test
    public void testConstructorCreatesProperDimensions() {
        assertEquals(DEFAULT_WIDTH, maze.getWidth());
        assertEquals(DEFAULT_HEIGHT, maze.getHeight());
    }

    // TODO: Add more tests
}
