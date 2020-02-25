package dynamic.medium.unique_paths_ii_63;

/**
 62. Unique Paths
 https://leetcode.com/problems/unique-paths-ii/

 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 Note: m and n will be at most 100.

 Example 1:
 Input:
     [
         [0,0,0],
         [0,1,0],
         [0,0,0]
     ]
 Output: 2

 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 The idea is to populate matrix with combinations where the next element is a sum of top and right combinations.
        If we find obstacle - set this cell to 0 so it does not affect next calculations
 2.2 Implementation
    2.2.1 Check if both input variables are valid.
    2.2.2 Set first element to 1 (we will do in-place calculations)
    2.2.3 Start two loops. In the first we will check rows, in the second - columns
    2.2.4 If we find a '1' and it is not the first element - set it to 0 and continue the next iteration.
    2.2.5 Otherwise set to the cell sum of dp[i - 1][j] + dp[i][j - 1].
 */

class DPSolution {
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0][0] == 1) {
            return 0;
        }

        grid[0][0] = 1;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0) && grid[i][j] == 1) {
                    grid[i][j] = 0;
                    continue;
                }

                if (i > 0) {
                    grid[i][j] += grid[i - 1][j];
                }
                if (j > 0) {
                    grid[i][j] += grid[i][j - 1];
                }
            }
        }

        return grid[m - 1][n - 1];
    }
}
