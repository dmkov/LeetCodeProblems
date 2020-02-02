package array.medium.game_of_life_289;

/**
 289. Game of Life
 https://leetcode.com/problems/game-of-life/

 According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

 Example:
 Input:
 [
     [0,1,0],
     [0,0,1],
     [1,1,1],
     [0,0,0]
 ]
 Output:
 [
     [0,0,0],
     [1,0,1],
     [0,1,1],
     [0,1,0]
 ]
 Follow up:
 Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 We encode the current state of the cell to be not dependant on neighbors. After that we iterate and update every cell
        to its modified value without checking neighbor cells.
 3 Implementation
    3.1 Check if given array is valid, iterate all elements and encode them.
    3.2 If current value is 0 - update it to '-1' and decrease value for every neighbor
    3.3 If current value is 1 - update it to '1' and increase value for every neighbor
    3.4 Iterate all items and apply rules based on negative or positive values (0 or 1) and shifting value (number of neighbors).
 */

public class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                encode(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    if (board[i][j] < 3)
                        board[i][j] = 0;
                    else if (board[i][j] >= 3 && board[i][j] <= 4)
                        board[i][j] = 1;
                    else if (board[i][j] > 4)
                        board[i][j] = 0;
                }
                if (board[i][j] < 0) {
                    if (board[i][j] == -4)
                        board[i][j] = 1;
                    else
                        board[i][j] = 0;
                }
            }
        }
    }

    private void encode(int[][] board, int i, int j) {
        board[i][j] = (board[i][j] > 0) ? 1 : -1;
        if (i > 0) {
            if (board[i - 1][j] > 0)
                increment(board, i, j);
            if (j > 0 && board[i - 1][j - 1] > 0)
                increment(board, i, j);
            if (j < board[i - 1].length - 1 && board[i - 1][j + 1] > 0)
                increment(board, i, j);
        }
        if (i < board.length - 1) {
            if (board[i + 1][j] > 0)
                increment(board, i, j);
            if (j > 0 && board[i + 1][j - 1] > 0)
                increment(board, i, j);
            if (j < board[i + 1].length - 1 && board[i + 1][j + 1] > 0)
                increment(board, i, j);
        }
        if (j > 0 && board[i][j - 1] > 0)
            increment(board, i, j);
        if (j < board[i].length - 1 && board[i][j + 1] > 0)
            increment(board, i, j);
    }

    private void increment(int[][] board, int i, int j) {
        if (board[i][j] > 0)
            board[i][j]++;
        else
            board[i][j]--;
    }
}
