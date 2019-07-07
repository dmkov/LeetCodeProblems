package search.medium.number_of_islands_200;

/**
 200. Number of Islands
 https://leetcode.com/problems/number-of-islands/

 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:
 Input:
 11110
 11010
 11000
 00000
 Output: 1

 Example 2:
 Input:
 11000
 11000
 00100
 00011
 Output: 3

---

 1. Complexity
    1.1 Time Complexity is O(n*m) where n and m is the size of 2D array
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on DFS.
    2.2 Implementation
        2.2.1 Iterate through all elements and if current one is '1' then mark it and all bonded as '0' so they are
            inspected before. Instead of tracking visited items in the additional array, the current one will have all
            '0' elements at the end.
 */

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    markAsVisited(grid, i, j);
                }
            }
        }
        return islands;
    }

    private void markAsVisited(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if (i > 0 && grid[i-1][j] == '1') {
            markAsVisited(grid, i - 1, j);
        }
        if (j > 0 && grid[i][j-1] == '1') {
            markAsVisited(grid, i, j - 1);
        }
        if (i + 1 < grid.length && grid[i+1][j] == '1') {
            markAsVisited(grid, i + 1, j);
        }
        if (j + 1 < grid[i].length && grid[i][j+1] == '1') {
            markAsVisited(grid, i, j + 1);
        }
    }
}
