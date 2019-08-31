package dynamic.medium.maximal_square_221;

/**
 221. Maximal Square
 https://leetcode.com/problems/maximal-square/

 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 Example:
 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 Output: 4

 --------

 1. Complexity
    1.1 Time Complexity is O(mn)
    1.2 Space Complexity is O(mn)
 2. Approach
    2.1 The idea is that we can count current element as a part of square only if [i][j-1], [i-1][j-1] and [i-1][j] are
        not empty. We will need an additional matrix to track the positions and increase number only if the condition
        met. We need to set the min+1 of all three elements to the current one. The answer will be square of maximum
        element in the new array.
 2.2 Implementation
    2.2.1 Create a temporary has matrix with the same size
    2.2.2 Iterate through all elements. If original element is not zero, check elements around the original index -
        [i][j-1], [i-1][j-1] and [i-1][j]. select the minimal value from them.
    2.2.3 Current element should be min + 1. Store it to the hash matrix.
 */

class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSquare = 0;
        if (matrix == null || matrix.length == 0) {
            return maxSquare;
        }
        int[][] hash = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int min = 0;
                    if (i > 0 && j > 0) {
                        min = Math.min(hash[i-1][j-1], Math.min(hash[i-1][j], hash[i][j-1]));
                    }
                    hash[i][j] = min + 1;
                    maxSquare = Math.max(hash[i][j] * hash[i][j], maxSquare);
                }
            }
        }
        return maxSquare;
    }
}
