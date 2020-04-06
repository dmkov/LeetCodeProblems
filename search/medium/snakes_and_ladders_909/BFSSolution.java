package search.medium.snakes_and_ladders_909;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 909. Snakes and Ladders
 https://leetcode.com/problems/snakes-and-ladders/

 On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.

 You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:

 You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
 (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations, regardless of the size of the board.)
 If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
 A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].

 Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`, because you do not continue moving to `4`.)

 Return the least number of moves required to reach square N*N.  If it is not possible, return -1.

 Example 1:
 Input: [
 [-1,-1,-1,-1,-1,-1],
 [-1,-1,-1,-1,-1,-1],
 [-1,-1,-1,-1,-1,-1],
 [-1,35,-1,-1,13,-1],
 [-1,-1,-1,-1,-1,-1],
 [-1,15,-1,-1,-1,-1]]
 Output: 4

 Explanation:
 - At the beginning, you start at square 1 [at row 5, column 0].
 - You decide to move to square 2, and must take the ladder to square 15.
 - You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
 - You then decide to move to square 14, and must take the ladder to square 35.
 - You then decide to move to square 36, ending the game.
 It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.

 Note:
 2 <= board.length = board[0].length <= 20
 board[i][j] is between 1 and N*N or is equal to -1.
 The board square with number 1 has no snake or ladder.
 The board square with number N*N has no snake or ladder.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2) because of traversing all matrix
    1.2 Space Complexity is O(n^2) - since we keep extra queue
 2. Approach
    2.1 The idea is to do a BFS until we reach the end. To avoid cycles we track visited nodes
 3 Implementation
    3.1 Check if input array is valid
    3.2 Put first element to the queue and start traversing with buckets to count steps
    3.3 If cell was not visited before - visit next 6 elements and evaluate their values. If value is different from -1,
        put the value instead of index to the queue.
    3.4 The tricky part here is that we do not need recursion and should count every transmission as a step
 */

class BFSSolution {
    public int snakesAndLadders(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length != grid.length) {
            return -1;
        }

        int n = grid.length;
        int t = n*n;
        int[] visited = new int[t+1];
        Arrays.fill(visited, -1);

        int step = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        add(1, grid, queue, visited, step);

        while (visited[t] == -1 && queue.size() > 0) {
            int bucket = queue.size();
            step++;
            for (int j = 0; j < bucket && visited[t] == -1; j++) {
                int cell = queue.remove();
                if (cell + 6 >= t) {
                    visited[t] = step;
                    break;
                }
                for (int k = 1; k <= 6; k++) {
                    add(cell + k, grid, queue, visited, step);
                }
            }
        }

        return visited[t];
    }

    private void add(int cell, int[][] grid, List<Integer> queue, int[] visited, int step) {
        if (visited[cell] != -1 && visited[cell] <= step) {
            return;
        }
        visited[cell] = step;
        int[] arr = decode(cell, grid.length);
        int target = grid[ arr[0] ][ arr[1] ];
        if (target == -1) {
            queue.add(cell);
        } else {
            int exit = grid.length * grid.length;
            if (target == exit) {
                visited[exit] = step;
                return;
            }

            queue.add(target);
            // by idea here should be a recursive approach but test case failed
            // add(target, grid, queue, visited, step);
        }
    }

    private int[] decode(int cell, int n) {
        int row = (cell - 1) / n;
        int pos = (cell - 1) - n*row;
        int col = pos;
        if (row % 2 == 1) {
            col = n - pos - 1;
        }

        return new int[]{n-row-1, col};
    }
}
