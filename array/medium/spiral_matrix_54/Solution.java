package array.medium.spiral_matrix_54;

import java.util.ArrayList;
import java.util.List;

/**
 54. Spiral Matrix
 https://leetcode.com/problems/spiral-matrix/

 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 Example 1:
 Input:
     [
         [ 1, 2, 3 ],
         [ 4, 5, 6 ],
         [ 7, 8, 9 ]
     ]
 Output: [1,2,3,6,9,8,7,4,5]

 Example 2:
 Input:
     [
         [1, 2, 3, 4],
         [5, 6, 7, 8],
         [9,10,11,12]
     ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to traverse 4 sides of rectangle n times, where n is the min of (cols-1)/2 or (rows-1)/2.
        It is a modelling of intuitive traverse but additionally we need to exclude duplicates in corners.
 3 Implementation
     3.1 Check if input array is valid.
     3.2 Get number of cols, rows and calculate number of rounds as min of (cols-1)/2 or (rows-1)/2.
     3.3 For k = 0 .. number of rounds:
        3.3.1 for (j = 0+k .. cols-k-1) col -> traverse i+k row
        3.3.2 for (i = 0+k+1 .. rows-k-2) row -> traverse cols-k-1 col
        3.3.3 for (j = cols-k-1 .. 0+k) col -> traverse rows-k-1 rows. Only if (k != rows-k-1) to exclude duplicates
        3.3.3 for (i = rows-k-2 .. 0+k-1) row -> traverse k col. Only if (k != cols-k-1) to exclude duplicates
     3.4 Store elements to the result list and return it at the end
 */

class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int rounds = (Math.min(rows, cols) - 1) / 2;

        for (int k = 0; k <= rounds; k++) {
            for (int j = 0+k; j < cols-k; j++) {
                result.add(matrix[k][j]);
            }
            for (int i = 0+k+1; i < rows-k-1; i++) {
                result.add(matrix[i][cols-k-1]);
            }
            if (k != rows-k-1) {
                for (int j = cols-k-1; j >= k; j--) {
                    result.add(matrix[rows-k-1][j]);
                }
            }
            if (k != cols-k-1) {
                for (int i = rows-k-2; i > 0+k; i--) {
                    result.add(matrix[i][k]);
                }
            }
        }

        return result;



        // 1. int c = Math.min(rows, cols);
        // 2. for k = 0..c:
        //   for (j = 0+k .. cols-k-1) -> traverse i+k row
        //   for (i = 0+k+1 .. rows-k-2) -> traverse cols-k-1 col
        //   for (j = cols-k-1 .. 0+k) -> traverse rows-k-1 rows
        //   for (i = rows-k-2 .. 0+k+1) -> traverse k col


    }
}
