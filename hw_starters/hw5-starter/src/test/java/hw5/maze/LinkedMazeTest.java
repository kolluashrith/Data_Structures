package hw5.maze;

import hw5.structures.LinkedMaze;
import hw5.structures.Maze;

public class LinkedMazeTest extends MazeTest {

    @Override
    public Maze createMaze(int width, int height) {
        return new LinkedMaze(width, height);
    }
}
