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
    1.1 Time Complexity is O(nlogn)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The solution is based on a divide and conquer approach. We split matrix to 4 smaller squares and check
        smallest (top left) and biggest (bottom right) numbers in the square. If target is between them - continue
        splitting squares until you get only one number.
    2.2 Implementation
        2.2.1 Check if matrix is empty or any of it is dimension equals to 0 (if it is - return false).
        2.2.2 Define a center point int the matrix and call method to check 4 squares from bottom and top,
            left and right of it.
        2.2.3 For every square check if left point does not cross right and point at the top - the bottom one.
            If there is only one number - compare it with the target. Otherwise, check the smallest (top left)
            and biggest (bottom right) numbers in the square.
        2.2.4 Split current area and repeat checking for every 4 inner square.
 */

class DivideAndConquerSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;

        return checkSquare(matrix, target, 0, bottom / 2, 0, right / 2)
                || checkSquare(matrix, target, (bottom / 2) + 1, bottom, 0, right / 2)
                || checkSquare(matrix, target, 0, bottom / 2, (right / 2) + 1, right)
                || checkSquare(matrix, target, (bottom / 2) + 1, bottom, (right / 2) + 1, right);
    }

    private boolean checkSquare(int[][] matrix, int target, int top, int bottom, int left, int right) {
        if (left > right || top > bottom) {
            return false;
        }
        if (left == right && top == bottom) {
            return matrix[top][left] == target;
        }
        if (matrix[bottom][right] < target || matrix[top][left] > target) {
            return false;
        }

        int midX = left + (right - left) / 2;
        int midY= top + (bottom - top) / 2;

        return checkSquare(matrix, target, top, midY, left, midX)
                || checkSquare(matrix, target, top, midY, midX + 1, right)
                || checkSquare(matrix, target, midY + 1, bottom, left, midX)
                || checkSquare(matrix, target, midY + 1, bottom, midX + 1, right);
    }
}
