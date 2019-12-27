package binary_search.medium.search_a_2d_matrix_ii_240;

/**
 240. Search a 2D Matrix II
 https://leetcode.com/problems/search-a-2d-matrix-ii/

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.

 Example:
 Consider the following matrix:

 [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.
 Given target = 20, return false.

 --------

 1. Complexity
    1.1 Time Complexity is O(n+m) - the best option!
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The solution is based on the idea that all columns and rows are sorted. We start from the bottom left
        point. If element is less than target - move pointer to the right. If it is bigger that target - move pointer
        to the top until we reach required element or move out the square.
    2.2 Implementation
        2.2.1 Check if matrix is empty or any of it is dimension equals to 0 (if it is - return false).
        2.2.2 Define row and col variables at the left bottom corner of the matrix
        2.2.3 Until we go out the matrix (row is less than 0 or col is greater than the last index) compare active
            element with the target. If element is less than target - increase col (move to the right), if element
            is bigger than target - decrease the row (move to the top).
        2.2.4 If we are out of the matrix - return false. If element is found - return true.
 */

class SearchSpaceReductionSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }
}
