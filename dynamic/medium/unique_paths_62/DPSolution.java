package dynamic.medium.unique_paths_62;

import java.util.Arrays;
import java.util.List;

/**
 62. Unique Paths
 https://leetcode.com/problems/unique-paths/

 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?

 Above is a 7 x 3 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.

 Example 1:
 Input: m = 3, n = 2
 Output: 3
 Explanation:
 From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -> Right -> Down
 2. Right -> Down -> Right
 3. Down -> Right -> Right

 Example 2:
 Input: m = 7, n = 3
 Output: 28

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 The idea is to populate matrix with combinations where the next element is a sum of top and right combinations.
 2.2 Implementation
    2.2.1 Check if both input variables are valid.
    2.2.2 Initialize `dp` array with given rows.
    2.2.3 Start two loops. In the first create array with columns, in the second - populate dp[i][j]
    2.2.4 For the first column and row number of combinations is 1, for others - sum of dp[i - 1][j] + dp[i][j - 1].
 */

class DPSolution {
    public int uniquePaths(int row, int col) {
        if (row < 1 || col < 1) {
            return 0;
        }

        int[][] dp = new int[row][];
        for (int i = 0; i < row; i++) {
            dp[i] = new int[col];
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[row - 1][col - 1];
    }
}
