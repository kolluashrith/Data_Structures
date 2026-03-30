package hw5.maze;

import hw5.structures.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void testGetCellRowBelowZeroThrowsIndexOutOfBoundsException() {
        try {
            maze.getCell(-1, 0);
            fail("getCell should throw IndexOutOfBoundsException when row is below zero.");
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    @Test
    public void testGetCellRowAboveHeightThrowsIndexOutOfBoundsException() {
        try {
            maze.getCell(maze.getHeight(), 0);
            fail("getCell should throw IndexOutOfBoundsException when row is above height.");
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    @Test
    public void testGetCellColAboveWidthThrowsIndexOutOfBoundsException() {
        try {
            maze.getCell(0, maze.getWidth());
            fail("getCell should throw IndexOutOfBoundsException when col is above width.");
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    @Test
    public void testGetCellColBelowZeroThrowsIndexOutOfBoundsException() {
        try {
            maze.getCell(0, -1);
            fail("getCell should throw IndexOutOfBoundsException when col is below zero.");
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }


    @Test
    public void testConstructorMakesAllCellsNotWalls() {
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                assertFalse(maze.getCell(row, col).isWall());
            }
        }
    }

    @Test
    public void testIsWallThrowsIndexOutOfBoundsExceptionForInvalidIndex() {
        try {
            System.out.println("------testIsWallThrowsIndexOutOfBoundsExceptionForInvalidIndex--------");
            maze.isWall(-1, 0);
            fail("isWall should throw IndexOutOfBoundsException when row is negative.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Negative Row Handled");
        }
        try {
            maze.isWall(0, -1);
            fail("isWall should throw IndexOutOfBoundsException when col is negative.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Negative Col Handled");
        }
        try {
            maze.isWall(maze.getHeight(), 0);
            fail("isWall should throw IndexOutOfBoundsException when row exceeds height.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Row Above Height Handled");
        }
        try {
            maze.isWall(0, maze.getWidth());
            fail("isWall should throw IndexOutOfBoundsException when col exceeds width.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Col Above Width Handled");
        }
    }

    @Test
    public void testSetWallThrowsIndexOutOfBoundsExceptionForInvalidIndex() {
        try {
            System.out.println("------testSetWallThrowsIndexOutOfBoundsExceptionForInvalidIndex--------");
            maze.setWall(-1, 0, true);
            fail("setWall should throw IndexOutOfBoundsException when row is negative.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Negative Row Handled");
        }
        try {
            maze.setWall(0, -1, true);
            fail("setWall should throw IndexOutOfBoundsException when col is negative.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Negative Col Handled");
        }
        try {
            maze.setWall(maze.getHeight(), 0, true);
            fail("setWall should throw IndexOutOfBoundsException when row exceeds height.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Row Above Height Handled");
        }
        try {
            maze.setWall(0, maze.getWidth(), true);
            fail("setWall should throw IndexOutOfBoundsException when col exceeds width.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Col Above Width Handled");
        }
    }

    @Test
    public void testSetWallMakesOnlyDesignatedCellsWalls() {
        maze.setWall(0,0, true);
        maze.setWall(4, 3, true);
        maze.setWall(1, 5, true);

        for (int r = 0; r < maze.getHeight(); r++) {
            for (int c = 0; c < maze.getWidth(); c++) {
                if ((r == 0 && c == 0) || (r == 4 && c == 3) || (r == 1 && c == 5)) {
                    assertTrue(maze.getCell(r, c).isWall());
                } else  {
                    assertFalse(maze.getCell(r, c).isWall());
                }
            }
        }
    }

    @Test
    public void testGetNeighborsReturnsListWithAllNeighborsWhenNoWalls() {
        List<?> neighborList = maze.getNeighbors(maze.getCell(1, 1));

        assertSame(neighborList.get(0), maze.getCell(0, 1)); //North
        assertSame(neighborList.get(1), maze.getCell(2, 1)); //South
        assertSame(neighborList.get(2), maze.getCell(1, 2)); //East
        assertSame(neighborList.get(3), maze.getCell(1, 0)); //West
    }

    @Test
    public void testGetNeighborsReturnsListWithOnlyNonWallNeighbors() {
        maze.setWall(1,0, true); //Remove West
        maze.setWall(2,1, true); //Remove South
        List<?> neighborList = maze.getNeighbors(maze.getCell(1, 1));

        assertSame(neighborList.get(0), maze.getCell(0, 1)); //North
        assertSame(neighborList.get(1), maze.getCell(1, 2)); //East
    }

    @Test
    public void testGetNeighborsReturnsOnlyTwoNeighborsInTopLeftCorner() {
        List<?> neighborList = maze.getNeighbors(maze.getCell(0, 0));

        assertSame(neighborList.get(0), maze.getCell(1, 0)); //South
        assertSame(neighborList.get(1), maze.getCell(0, 1)); //East
    }

    @Test
    public void testGetNeighborsReturnsOnlyTwoNeighborsInTopRightCorner() {
        List<?> neighborList = maze.getNeighbors(maze.getCell(0, maze.getWidth() - 1));

        assertSame(neighborList.get(0), maze.getCell(1, maze.getWidth() - 1)); //South
        assertSame(neighborList.get(1), maze.getCell(0, maze.getWidth() - 2)); //West
    }

    @Test
    public void testGetNeighborsReturnsOnlyTwoNeighborsInBottomLeftCorner() {
        List<?> neighborList = maze.getNeighbors(maze.getCell(maze.getHeight() - 1, 0));

        assertSame(neighborList.get(0), maze.getCell(maze.getHeight() - 2, 0)); //North
        assertSame(neighborList.get(1), maze.getCell(maze.getHeight() - 1, 1)); //East
    }

    @Test
    public void testGetNeighborsReturnsOnlyTwoNeighborsInBottomRightCorner() {
        List<?> neighborList = maze.getNeighbors(maze.getCell(maze.getHeight() - 1, maze.getWidth() - 1));

        assertSame(neighborList.get(0), maze.getCell(maze.getHeight() - 2, maze.getWidth() - 1)); //North
        assertSame(neighborList.get(1), maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2)); //West
    }

    @Test
    public void testGetNeighborsThrowsIllegalArgumentExceptionForMazeCellNotInMaze() {
        Maze maze2 = createMaze(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        try {
            maze2.getNeighbors(maze.getCell(0, 0));
            fail("getNeighbors should throw IllegalArgumentException when argument is a MazeCell belonging to a different maze.");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

}
