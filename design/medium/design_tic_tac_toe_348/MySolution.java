package design.medium.design_tic_tac_toe_348;

/**
 348. Design Tic-Tac-Toe
 https://leetcode.com/problems/design-tic-tac-toe/

 Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

 Example:
 Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

 TicTacToe toe = new TicTacToe(3);

 toe.move(0, 0, 1); -> Returns 0 (no one wins)
 |X| | |
 | | | |    // Player 1 makes a move at (0, 0).
 | | | |

 toe.move(0, 2, 2); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 2 makes a move at (0, 2).
 | | | |

 toe.move(2, 2, 1); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 1 makes a move at (2, 2).
 | | |X|

 toe.move(1, 1, 2); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 2 makes a move at (1, 1).
 | | |X|

 toe.move(2, 0, 1); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 1 makes a move at (2, 0).
 |X| |X|

 toe.move(1, 0, 2); -> Returns 0 (no one wins)
 |X| |O|
 |O|O| |    // Player 2 makes a move at (1, 0).
 |X| |X|

 toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 |X| |O|
 |O|O| |    // Player 1 makes a move at (2, 1).
 |X|X|X|

 Follow up:
 Could you do better than O(n2) per move() operation?

 --------

 1. Complexity
    1.1 Time Complexity is O(1)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to use separate arrays for horizontal and vertical lines, left and right diagrams for each user.
        When we make a move, increment line/diagonal for the current user and set "-1" for the opposite one (since he can not
        use this line/diagonal anymore). User wins when he has N (or N - 1 if we check it before the incrementing) in the line/diagonal.
 */

class MySolution {

    int[][] h, v; // horizontal and vertical lines
    int[] lD, rD; // right and left diagonales
    int n;

    /** Initialize your data structure here. */
    public MySolution(int n) {
        this.n = n;
        this.h = new int[2][n];
        this.v = new int[2][n];
        this.lD = new int[2];
        this.rD = new int[2];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (row > n - 1 || row < 0 || col > n - 1 || col < 0
                || player < 1 || player > 2) {
            return 0;
        }

        if (checkLine(h, row, player) || checkLine(v, col, player)
                || (col == row && checkDiagonale(lD, player))
                || (col == n - row - 1 && checkDiagonale(rD, player))) {
            return player;
        }

        updateLine(h, row, player);
        updateLine(v, col, player);
        if (col == row) {
            updateDiagonale(lD, player);
        }
        if (col == n - row - 1) {
            updateDiagonale(rD, player);
        }

        return 0;
    }

    private boolean checkLine(int[][] arr, int i, int player) {
        if (arr[current(player)][i] == n - 1) {
            return true;
        }

        return false;
    }

    private boolean checkDiagonale(int[] arr, int player) {
        if (arr[current(player)] == n - 1) {
            return true;
        }

        return false;
    }

    private void updateLine(int[][] arr, int i, int player) {
        arr[opposite(player)][i] = -1;
        if (arr[current(player)][i] != -1) {
            arr[current(player)][i]++;
        }
    }

    private void updateDiagonale(int[] arr, int player) {
        arr[opposite(player)] = -1;
        if (arr[current(player)] != -1) {
            arr[current(player)]++;
        }
    }

    private int current(int player) {
        return player - 1;
    }

    private int opposite(int player) {
        if (player == 1) {
            return current(2);
        } else {
            return current(1);
        }
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
