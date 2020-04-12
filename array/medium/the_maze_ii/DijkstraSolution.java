package array.medium.the_maze_ii;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
    1.1 Time Complexity is O(n*m*log(n*m))
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 For every point traverse 4 possible directions, if result is different - add it as a new point.
        Track already visited points with number of steps and do not enter there again if previous steps is less than
        the current counter.
    The difference with common BFS approach is min heap that gives us a log(n*m) product instead of Max()
 */

public class DijkstraSolution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        if (maze == null || maze.length == 0 || start == null || dest == null) {
            return -1;
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] steps = new int[maze.length][maze[0].length];
        for (int i = 0; i < steps.length; i++) {
            Arrays.fill(steps[i], Integer.MAX_VALUE);
        }
        steps[start[0]][start[1]] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> steps[a[0]][a[1]] - steps[b[0]][b[1]]);
        heap.add(start);
        while (heap.size() > 0) {
            int[] cell = heap.poll();
            visit(cell, maze, heap, steps, visited);
        }

        return (visited[dest[0]][dest[1]]) ? steps[dest[0]][dest[1]] : -1;
    }

    private void visit(int[] cell, int[][] maze, PriorityQueue<int[]> heap, int[][] steps, boolean[][] visited) {
        int[][] dirs = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        for (int[] dir : dirs) {
            int[] point = getNextCell(cell, dir, maze);
            if (point[0] != cell[0] || point[1] != cell[1]) {
                int cur = Math.abs(cell[0] - point[0])
                        + Math.abs(cell[1] - point[1]) + steps[cell[0]][cell[1]];
                if (!visited[point[0]][point[1]] || cur < steps[point[0]][point[1]]) {
                    heap.add(point);
                    visited[point[0]][point[1]] = true;
                    steps[point[0]][point[1]] = Math.abs(cell[0] - point[0])
                            + Math.abs(cell[1] - point[1]) + steps[cell[0]][cell[1]];
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
