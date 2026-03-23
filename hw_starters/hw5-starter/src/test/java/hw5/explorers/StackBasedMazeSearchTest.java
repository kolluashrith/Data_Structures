package hw5.explorers;

import hw5.explorers.MazeSearch;
import hw5.explorers.StackBasedMazeSearch;
import hw5.structures.Maze;

public class StackBasedMazeSearchTest extends MazeSearchTest {

    @Override
    public MazeSearch createSearcher(Maze maze) {
        return new StackBasedMazeSearch(maze);
    }
}
