# Homework 5: Maze Runner

## Discussion on Array-based vs Linked-based Implementation of Maze

Note: I use (width * height) and n interchangeably here when discussing time and space complexities.

An array-based implementation of Maze would contain an inner ArrayMazeCell class that would contain most of the same information as the LinkedMazeCell, but it would exclude the links to the neighboring cells. With arrays, traversal can be done with plain index arithmetic, so just information about height and width would be sufficient to calculate the index of neighboring cells. These still have O(1) space complexity. The constructors for both would require the whole grid to be populated with new MazeCell objects, which is O(width * height) time regardless. The space complexity for both constructors would also be O(1) since only constant number of values are stored at any time in it. Getting width and height are both O(1) time and space since they would both be fields whose values are constant and can be read straight from the field. Getting the MazeCell at a certain row and column would take O(1) time for the array-based implementation since the index can be calculated with arithmetic and accessing elements in the array is constant time. On the other hand, since getting the MazeCell for a linked-list implementation requires the sequential traversal along the rows and columns, it is an O(n) time complexity operation. Both operations are O(1) space complexity since the number of things being stored at any given point is a constant. Checking to see if a cell is a wall or setting it are both O(1) time operations for an array-based implementation since accessing array positions and returning or changing the data in a field are all constant time. They are also O(1) space complexity since the auxiliary data needed is also constant. However, for linked-list implementations, the getting and setting of the wall requires traversal to the specified row and column, which is an O(n) time operation. The space used is constant, O(1). For getting neighbors, the time and space complexity for an array-based implementation is O(1) since it only requires index arithmetic and array accessing with no additional data stored. This operation is O(1) for a linked-list implementation too because the neighbors are directly stored in the MazeCell object, so access is constant time, and only four are stored so it's also constant space. The reset function is O(width * length) for both implementations since every single cell needs to be visited in the maze to reset the boolean. 

Thus, from a time and space complexity standpoint, an array-based implementation would almost always outperform the linked-list based one purely because of the time saved with traversal by index arithmetic. However, there can be cases with complex traversal patterns where it would be faster to just obtain the pointers to the cells in every direction rather than having to recalculate the new index and check bounds for every neighboring cell. You would want the array-based implementation if the maze becomes large, and you want to optimize algorithm running time, but if the maze is small enough, a linked-list implementation results in a cleaner and clearer search algorithm.


## Discussion on Stack-based vs Queue-based Implementation of Maze Explorer

I set up a couple of different mazes in test/java/explorers/MazeSearchTest to test both versions simultaneously and printed the results.

Testing: MazeTestWithIntersectingPaths
------ Current Search Type: QueueBasedMazeSearch-------
Path size: 7
Cells visited: 14

Testing: MazeTestWithIntersectingPaths
------ Current Search Type: StackBasedMazeSearch-------
Path size: 13
Cells visited: 13

Testing: MazeTestWithStartEndOppositeCorners
------ Current Search Type: QueueBasedMazeSearch-------
Path size: 9
Cells visited: 18

Testing: MazeTestWithStartEndOppositeCorners
------ Current Search Type: StackBasedMazeSearch-------
Path size: 9
Cells visited: 9

Testing: MazeTestWithLongAndShortPath
------ Current Search Type: QueueBasedMazeSearch-------
Path size: 3
Cells visited: 4

Testing: MazeTestWithLongAndShortPath
------ Current Search Type: StackBasedMazeSearch-------
Path size: 15
Cells visited: 15

Based on this data, we can see that the QueueBasedMazeSearch always finds a shorter path than StackBasedMazeSearch. In fact, the way the algorithm works is by searching outward from the start, so the first path found is, in fact, the shortest path. On the other hand, StackBasedMazeSearch takes a different approach, traversing completely along a path until it either runs into a dead-end, in which case it backtracks, or finds the end. This can result in a longer discovered path if multiple exist and that longer path happened to be the first to be traversed. We can see this in the MazeTestWithLongAndShortPath results since there were two different paths available, but the stack-based approach took the longer one first while the queue-based approach found the shorter one. However, based on the structure of the maze, the stack-based implementation might find the shortest one anyway, seen in the results of MazeTestWithStartEndOppositeCorners. 

Because of the way they discover paths, the number of cells visited by the two really depends on the shape of the maze. If a maze has the start and end far apart and many dead-ends, then the number of cells visited for the queue-based one would typically be higher. This is because it would explore many of those dead-ends in the middle, spreading outward, until it finally located the end. On the other hand, the stack-based one would explore each path deeply, which may result in the end being reached by chance without having to visit every one of the dead-ends. We see this in the MazeTestWithStartEndOppositeCorners results, where the distance covered made it so the queue-based implementation searched more of the maze than the stack-based one had to. 

The stack-based implementation only stores the current path branches whereas the queue-based one stores all cells on the frontier perimeter. The queue-based implementation can become very memory intensive in cases like where the maze is an open grid, in which case the search perimeter would grow large rather quickly. Even in this case, the stack-based one would search a single path at a time, using less memory on average to find the path. However, there are exceptions to this pattern based on the structure of the maze. In a case like with MazeTestWithLongAndShortPath, there was a path short enough where the queue-based one found it before its search frontier perimeter became too large. In contrast, the stack-based implementation used more memory because of the longer path it discovered. Thus, the memory usage is dependent on the situation, but on average the queue-based implementation will likely use more memory than the stack-based one because of the nature of the search algorithm. 

This was already covered earlier in the response but to recap, the maze structure influences the performance of each algorithm since each performs better on some structures than others. Whereas the queue-based implementation works when there are multiple paths and you want to find the shortest path, the stack-based one works well to simply find if a path exists or if the path is deep. This also determines when you would use one explorer over the other. To find the shortest path, you want to use the queue-based one. To find if a path exists or deal with mazes with deep paths, then a stack-based one would likely perform better. With memory constraints too, you might consider using a stack-based implementation over a queue-based one. 