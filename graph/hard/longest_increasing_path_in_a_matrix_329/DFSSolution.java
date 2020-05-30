package graph.hard.longest_increasing_path_in_a_matrix_329;

/**
 329. Longest Increasing Path in a Matrix
 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

 Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:
 Input: nums =
     [
         [9,9,4],
         [6,6,8],
         [2,1,1]
     ]
 Output: 4
 Explanation: The longest increasing path is [1, 2, 6, 9].

 Example 2:
 Input: nums =
     [
         [3,4,5],
         [3,2,6],
         [2,2,1]
     ]
 Output: 4
 Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

 --------

 1. Complexity
    1.1 Time Complexity is O(n), where n is row*col, since we need to check every vertex once
    1.2 Space Complexity is also O(n)
 2. Approach
    2.1 Use memoization to store DFS results for every node
 */

public class DFSSolution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int[][] paths = new int[matrix.length][matrix[0].length];
        int max = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, backtracking(matrix, i, j, paths));
            }
        }

        return max;
    }

    private int backtracking(int[][] arr, int i, int j, int[][] paths) {
        if (paths[i][j] != 0) {
            return paths[i][j];
        }

        paths[i][j] = 1;
        if (i > 0 && arr[i][j] > arr[i - 1][j]) {
            paths[i][j] = Math.max(paths[i][j], backtracking(arr, i - 1, j, paths) + 1);
        }
        if (i < arr.length - 1 && arr[i][j] > arr[i + 1][j]) {
            paths[i][j] = Math.max(paths[i][j], backtracking(arr, i + 1, j, paths) + 1);
        }
        if (j > 0 && arr[i][j] > arr[i][j - 1]) {
            paths[i][j] = Math.max(paths[i][j], backtracking(arr, i, j - 1, paths) + 1);
        }
        if (j < arr[0].length - 1 && arr[i][j] > arr[i][j + 1]) {
            paths[i][j] = Math.max(paths[i][j], backtracking(arr, i, j + 1, paths) + 1);
        }

        return paths[i][j];
    }
}


/**
 1. So we are given a 2d matrix? Is it matrix of integers? Negative and zero values alloowed?
 2. Increasing path - do we count only numbers bigger then current one? Or >= ?
 3. Should I return the number of steps or path itself?
 4. Do we count the number itself as one step?


     1 4 5
     3 1 6
     1 2 1

     1. Brute force approach:
         For every cell - find the max increasing path
         O(4^n) where n is m*k

     2. Backtracking with memoization (DFS)
        O(n)

 */
