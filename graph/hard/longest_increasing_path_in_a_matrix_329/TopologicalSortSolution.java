package graph.hard.longest_increasing_path_in_a_matrix_329;

import java.util.ArrayDeque;
import java.util.Deque;

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
    2.1 Using a topological sort (Kahn's sort) take the smallest nodes from the matrix and count height of possible
        moves from this node.
 */

public class TopologicalSortSolution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        Deque<int[]> q = new ArrayDeque<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] in = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int children = 0;
                for (int[] dir : dirs) {
                    int k = i + dir[0];
                    int m = j + dir[1];
                    if (k >= 0 && k < matrix.length
                            && m >= 0 && m < matrix[0].length
                            && matrix[i][j] > matrix[k][m]) {
                        children++;
                    }
                }
                in[i][j] = children;
                if (children == 0) {
                    q.addLast(new int[]{i, j});
                }
            }
        }

        int height = 0;
        while (q.size() > 0) {
            height++;
            for (int s = q.size(); s > 0; s--) {
                int[] indx = q.removeFirst();
                for (int[] dir : dirs) {
                    int k = indx[0] + dir[0];
                    int m = indx[1] + dir[1];
                    if (k >= 0 && k < matrix.length
                            && m >= 0 && m < matrix[0].length
                            && matrix[k][m] > matrix[ indx[0] ][ indx[1] ]) {
                        in[k][m]--;

                        if (in[k][m] == 0) {
                            q.addLast(new int[]{k, m});
                        }
                    }
                }
                in[ indx[0] ][ indx[1] ] = -1;
            }
        }

        return height;
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
