package math.easy.projection_area_of_3d_shapes_883;

/**
 883. Projection Area of 3D Shapes
 https://leetcode.com/problems/projection-area-of-3d-shapes/

 On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.
 Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 Now we view the projection of these cubes onto the xy, yz, and zx planes.
 A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.
 Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.

 Return the total area of all three projections.

 Example 1:
 Input: [[2]]
 Output: 5

 Example 2:
 Input: [[1,2],[3,4]]
 Output: 17

---

 1. Complexity
    1.1 Time Complexity: O(N^2), where N is the length of grid.
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Projection on XY is the number of non-empty cubes
    2.2 Two other projections are max numbers in the column and row of the array
    2.3 Create three functions to find all three sums
 */
public class Solution {
    public int projectionArea(int[][] grid) {
        return projectXY(grid) + projectXZ(grid) + projectYZ(grid);
    }

    private int projectXY(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    private int projectXZ(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(max, grid[i][j]);
            }
            result += max;
        }
        return result;
    }

    private int projectYZ(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(max, grid[j][i]);
            }
            result += max;
        }
        return result;
    }
}
