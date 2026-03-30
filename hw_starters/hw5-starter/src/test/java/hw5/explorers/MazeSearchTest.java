package hw5.explorers;

import hw5.explorers.MazeSearch;
import hw5.structures.LinkedMaze;
import hw5.structures.Maze;
import hw5.structures.MazeCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An abstract base class for testing MazeSearch implementations.
 */
public abstract class MazeSearchTest {

    protected Maze maze;
    protected MazeSearch searcher;

    /**
     * Factory method for creating an instance of the MazeSearch implementation under test.
     */
    public abstract MazeSearch createSearcher(Maze maze);

    @BeforeEach
    public void setUp() {
        maze = new LinkedMaze(5, 5);
        searcher = createSearcher(maze);
    }

    @Test
    public void testFindsAPathWhenOneExists() {
        MazeCell start = maze.getCell(0, 0);
        MazeCell goal = maze.getCell(2, 2);

        List<MazeCell> path = searcher.findPath(start, goal);

        assertFalse(path.isEmpty(), "A path should be found in a simple open maze.");
        assertEquals(start, path.get(0), "Path should start at the start cell.");
        assertEquals(goal, path.get(path.size() - 1), "Path should end at the goal cell.");
    }

    @Test
    public void testFindsNoPathWhenBlocked() {
        // Wall off the goal cell completely
        maze.setWall(0, 1, true);
        maze.setWall(1, 0, true);
        maze.setWall(1, 1, true);

        MazeCell start = maze.getCell(0, 0);
        MazeCell goal = maze.getCell(1, 1);

        List<MazeCell> path = searcher.findPath(start, goal);

        assertTrue(path.isEmpty(), "Should not find a path to a completely blocked cell.");
    }

    @Test
    public void MazeTestWithLongAndShortPath() {
        System.out.println("\nTesting: MazeTestWithLongAndShortPath");
        maze.setWall(1, 1, true);
        maze.setWall(1, 2, true);
        maze.setWall(1, 3, true);
        maze.setWall(2, 3, true);
        maze.setWall(3, 3, true);
        maze.setWall(4, 0, true);
        maze.setWall(4, 1, true);
        MazeCell start = maze.getCell(3, 0);
        MazeCell goal = maze.getCell(1, 0);

        /* This is the maze that is set up.
         *          ooooo
         *  end     oxxxo
         *          oxoxo
         * start    oooxo
         *          xxooo
         */

        LinkedMaze castMaze = (LinkedMaze) maze;
        List<MazeCell> path = searcher.findPath(start, goal);
        String type = (searcher instanceof StackBasedMazeSearch) ? "StackBasedMazeSearch" : "QueueBasedMazeSearch";
        System.out.println("------ Current Search Type: " + type + "-------");
        System.out.println("Path size: " + path.size());
        System.out.println("Cells visited: " + castMaze.countVisited());

    }

    @Test
    public void MazeTestWithIntersectingPaths() {
        System.out.println("\nTesting: MazeTestWithIntersectingPaths");
        maze.setWall(0, 2, true);
        maze.setWall(2, 0, true);
        maze.setWall(2, 1, true);
        maze.setWall(2, 3, true);
        maze.setWall(3, 3, true);
        maze.setWall(4, 0, true);
        maze.setWall(4, 1, true);
        MazeCell start = maze.getCell(3, 0);
        MazeCell goal = maze.getCell(1, 0);

        /* This is the maze that is set up.
         *          ooxoo
         * end      ooooo
         *          xxoxo
         * start    oooxo
         *          xxooo
         */

        LinkedMaze castMaze = (LinkedMaze) maze;
        List<MazeCell> path = searcher.findPath(start, goal);
        String type = (searcher instanceof StackBasedMazeSearch) ? "StackBasedMazeSearch" : "QueueBasedMazeSearch";
        System.out.println("------ Current Search Type: " + type + "-------");
        System.out.println("Path size: " + path.size());
        System.out.println("Cells visited: " + castMaze.countVisited());

    }

    @Test
    public void MazeTestWithStartEndOppositeCorners() {
        System.out.println("\nTesting: MazeTestWithStartEndOppositeCorners");
        maze.setWall(0, 2, true);
        maze.setWall(2, 0, true);
        maze.setWall(2, 1, true);
        maze.setWall(2, 3, true);
        maze.setWall(3, 3, true);
        maze.setWall(4, 0, true);
        maze.setWall(4, 1, true);
        MazeCell start = maze.getCell(0, 0);
        MazeCell goal = maze.getCell(4, 4);

        /* This is the maze that is set up.
         *   start  ooxoo
         *          ooooo
         *          xxoxo
         *          oooxo
         *          xxooo end
         */

        LinkedMaze castMaze = (LinkedMaze) maze;
        List<MazeCell> path = searcher.findPath(start, goal);
        String type = (searcher instanceof StackBasedMazeSearch) ? "StackBasedMazeSearch" : "QueueBasedMazeSearch";
        System.out.println("------ Current Search Type: " + type + "-------");
        System.out.println("Path size: " + path.size());
        System.out.println("Cells visited: " + castMaze.countVisited());

    }
}
