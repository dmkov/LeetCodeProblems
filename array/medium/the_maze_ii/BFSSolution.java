package array.medium.the_maze_ii;

import java.util.Arrays;
import java.util.LinkedList;

/**
 505. The Maze II
 https://leetcode.com/problems/the-maze-ii/

 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1:
 Input 1: a maze represented by a 2D array
 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0
 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: 12

 Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

 Example 2:
 Input 1: a maze represented by a 2D array
 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0
 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: -1

 Explanation: There is no way for the ball to stop at the destination.

 Note:
 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m*Max(n,m))
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 For every point traverse 4 possible directions, if result is different - add it as a new point.
        Track already visited points with number of steps and do not enter there again if previous steps is less than
        the current counter.
 3. Implementation
    3.1 Check if given array is valid, create a 2D visited array and populate it with -1.
    3.2 Add a linked list to keep tracking of all points. While list is not empty:
        3.2.1 Get the all possible points from 4 directions
        3.2.2 If they are different from the current point and visited counter is bigger or -1 - add them to the list
    3.3 When all points are visited, return counter at destination
 */

public class BFSSolution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        if (maze == null || maze.length == 0 || start == null || dest == null) {
            return -1;
        }

        int[][] visited = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[start[0]][start[1]] = 0;

        LinkedList<int[]> heap = new LinkedList<>();
        heap.add(start);
        while (heap.size() > 0) {
            int[] cell = heap.remove();
            visit(cell, maze, heap, visited);
        }

        return visited[dest[0]][dest[1]];
    }

    private void visit(int[] cell, int[][] maze, LinkedList<int[]> heap, int[][] visited) {
        int[][] dirs = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        for (int[] dir : dirs) {
            int[] point = getNextCell(cell, dir, maze);
            if (point[0] != cell[0] || point[1] != cell[1]) {
                int steps = Math.abs(cell[0] - point[0]) + Math.abs(cell[1] - point[1]) + visited[cell[0]][cell[1]];
                if (visited[point[0]][point[1]] == -1 || visited[point[0]][point[1]] > steps) {
                    heap.add(point);
                    visited[point[0]][point[1]] = steps;
                }
            }
        }
    }

    private int[] getNextCell(int[] cell, int[] diff, int[][] maze) {
        int[] result = new int[]{cell[0], cell[1]};
        int[] point = new int[]{cell[0] + diff[0], cell[1] + diff[1]};
        while (point[0] >= 0 && point[0] <= maze.length - 1 &&
                point[1] >= 0 && point[1] <= maze[0].length - 1
                && maze[ point[0] ][ point[1] ] == 0) {
            result[0] = point[0];
            result[1] = point[1];

            point[0] += diff[0];
            point[1] += diff[1];
        }

        return result;
    }
}
