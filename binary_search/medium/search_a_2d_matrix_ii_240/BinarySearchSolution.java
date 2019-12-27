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
    2.1 The solution is based on a binary search. First, we exclude rows that do not match task description
        (the last item is less than target or first item is larger than it). After that we do binary search on
        remaining rows one by one.
 2.2 Implementation
    2.2.1 Check if matrix is empty or any of it is dimension equals to 0 (if it is - return false).
    2.2.2 Get the lower and higher bounds check the first element in the row (it should be no lager than target)
        and last one (it should be no smaller than target). Do it with binary search and separate methods.
    2.2.3 After getting rows where target element can be, do a binary search in these rows one by one.
 */

class BinarySearchSolution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int low = getLowBound(matrix, target);
        int high = getHighBound(matrix, target);

        return searchMatrixWithBounds(matrix, low, high, target);
    }

    private int getLowBound(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][matrix[mid].length - 1] >= target) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int getHighBound(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
                res = mid;
            }
        }
        return res;
    }

    private boolean searchMatrixWithBounds(int[][] matrix, int low, int high, int target) {
        for (int i = low; i <= high; i++) {
            int left = 0;
            int right = matrix[0].length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] > target) {
                    right = mid - 1;
                } else if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
