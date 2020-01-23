package search.easy.rotting_oranges_994;

import java.util.LinkedList;
import java.util.Stack;

/**
 994. Rotting Oranges
 https://leetcode.com/problems/rotting-oranges/

 In a given grid, each cell can have one of three values:
 the value 0 representing an empty cell;
 the value 1 representing a fresh orange;
 the value 2 representing a rotten orange.

 Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

 Example 1:
 Input: [[2,1,1],[1,1,0],[0,1,1]]
 Output: 4

 Example 2:
 Input: [[2,1,1],[0,1,1],[1,0,1]]
 Output: -1
 Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

 Example 3:
 Input: [[0,2]]
 Output: 0
 Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

---

 1. Complexity
    1.1 Time Complexity is O(n*m) where n is number of cols and m is number of rows
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 We need to iterate all matrix using BFS to find neighbour items on every step.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Create a Position class to track col,row pair. It also can be done with `new int[]{row,col}` on every step.
    3.3 Iterate all grid and put positions of rotten oranges to queue. If there is no rotten items - the result is 0.
    3.4 While queue is not empty get elements from it and mark all fresh neighbours as rotten. Increment steps
    3.5 At the end check if we still have any fresh oranges (it means there is no direct path) - return -1,
        otherwise - number of steps.
 */

class Solution {
    class Position {
        int col, row;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int orangesRotting(int[][] grid) {
        int result = -1;
        if (grid == null || grid.length == 0) {
            return result;
        }

        LinkedList<Position> queue = new LinkedList<>();

        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j< cols; j++) {
                if (grid[i][j] == 2)
                    queue.add(new Position(i, j));
            }
        }
        if (queue.size() == 0) {
            result = 0;
        }

        while (queue.size() > 0) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Position pos = queue.remove();
                rotting(grid, queue, pos.row - 1, pos.col);
                rotting(grid, queue, pos.row + 1, pos.col);
                rotting(grid, queue, pos.row, pos.col + 1);
                rotting(grid, queue, pos.row, pos.col - 1);
            }
            result++;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j< cols; j++) {
                if (grid[i][j] == 1) {
                    result = -1;
                    break;
                }

            }
        }
        return result;
    }

    private void rotting(int[][] grid, LinkedList<Position> queue, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && grid[row][col] == 1) {
            grid[row][col] = 2;
            queue.add(new Position(row, col));
        }
    }
}
