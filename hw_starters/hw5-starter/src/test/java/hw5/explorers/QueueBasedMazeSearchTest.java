package hw5.explorers;

import hw5.explorers.MazeSearch;
import hw5.explorers.QueueBasedMazeSearch;
import hw5.structures.Maze;

public class QueueBasedMazeSearchTest extends MazeSearchTest {

    @Override
    public MazeSearch createSearcher(Maze maze) {
        return new QueueBasedMazeSearch(maze);
    }
}
