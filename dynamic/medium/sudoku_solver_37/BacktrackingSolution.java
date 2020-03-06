package dynamic.medium.sudoku_solver_37;

import java.util.HashSet;
import java.util.Set;

/**
 37. Sudoku Solver
 https://leetcode.com/problems/sudoku-solver/

 Write a program to solve a Sudoku puzzle by filling the empty cells.
 A sudoku solution must satisfy all of the following rules:

 Each of the digits 1-9 must occur exactly once in each row.
 Each of the digits 1-9 must occur exactly once in each column.
 Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 Empty cells are indicated by the character '.'.

 A sudoku puzzle...
 ...and its solution numbers marked in red.

 Note:
 The given board contain only digits 1-9 and the character '.'.
 You may assume that the given Sudoku puzzle will have a single unique solution.
 The given board size is always 9x9.

 --------

 1. Complexity
     1.1 Time Complexity is O((9!)^9)
     1.2 Space Complexity is O(81)
 2. Approach
     2.1 For every empty element on the board we need to check all possible numbers in the current combination and
        start DFS for all of them. If there are no possible combinations at that point - return false.
 3 Implementation
     3.1 Check if input array of digits is valid.
     3.2 Create three arrays of sets to keep the existing data - for rows, for cols and for 9 boxes. Populate these sets
        with existing data on the board.
     3.2 Start recursive function at the first element:
        3.2.1 If row index overflows 8 - return true, that is the end of combinations.
        3.2.2 If col index overflows 8 - return recursively method for the next row and '0' column in it.
        3.2.3 If current element is different from '.' - skip this cell and recursively returns the next element in this row.
        3.2.4 Otherwise, iterate numbers from 1 to 9:
            3.2.4.1 Check if this number is available for the current row, column and sub-box.
            3.2.4.2 If yes, put this number into all sets and start backtracking method for the next index.
            3.2.4.3 If no, skip the current index and move to the next element. At the end return false if there is
                no success combination.
            3.2.4.4 If any of backtracking methods return true, break the cycle and return true as the result of the current stage.
 */

class BacktrackingSolution {

    public void solveSudoku(char[][] board) {
        if (board == null || board[0].length != 9) {
            return;
        }

        Set<Character>[] rows = new Set[9];
        Set<Character>[] cols = new Set[9];
        Set<Character>[] boxes = new Set[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rows[i].add(board[i][j]);
                    cols[j].add(board[i][j]);
                    int boxId = (i/3)*3 + (j/3);
                    boxes[ boxId ].add(board[i][j]);
                }
            }
        }

        backtracking(board, 0, 0, rows, cols, boxes);

    }

    private boolean backtracking(char[][] board, int i, int j, Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] boxes) {
        if (i > 8) {
            return true;
        }
        if (j > 8) {
            return backtracking(board, i + 1, 0, rows, cols, boxes);
        }
        if (board[i][j] != '.') {
            return backtracking(board, i, j + 1, rows, cols, boxes);
        }

        for (int num = 1; num <= 9; num++) {
            char ch = (char)('0' + num);
            int boxId = (i/3)*3 + (j/3);
            if (isNumberAvailable(ch, rows[i], cols[j], boxes[boxId])) {
                board[i][j] = ch;
                rows[i].add(ch);
                cols[j].add(ch);
                boxes[boxId].add(ch);

                if (backtracking(board, i, j + 1, rows, cols, boxes)) {
                    return true;
                }

                board[i][j] = '.';
                rows[i].remove(ch);
                cols[j].remove(ch);
                boxes[boxId].remove(ch);

            }
        }

        return false;
    }

    private boolean isNumberAvailable(char ch, Set<Character> rows, Set<Character> cols, Set<Character> boxes) {
        return !(rows.contains(ch) || cols.contains(ch) || boxes.contains(ch));
    }

    public static void main(String[] args) {
        BacktrackingSolution sol = new BacktrackingSolution();
        sol.solveSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        });
    }
}
