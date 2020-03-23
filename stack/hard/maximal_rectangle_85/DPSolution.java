package stack.hard.maximal_rectangle_85;

import java.util.Arrays;

/**
 85. Maximal Rectangle
 https://leetcode.com/problems/maximal-rectangle/

 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 Example:
 Input:
     [
         ["1","0","1","0","0"],
         ["1","0","1","1","1"],
         ["1","1","1","1","1"],
         ["1","0","0","1","0"]
     ]
 Output: 6

---
 
 Example:
 https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n)
 2. Approach
    2.2 The idea is to calculate height of '1's in every row and then count how many neighbors before and after that number
        have greater or the same height.
    2.1 Since we know that bar can be raised only for 1 with every row, we can iterate cell by cells and:
        2.1.1 Get height by incrementing previous value if cell is '1', otherwise immediately set it to '0'
        2.1.2 Starting from left, if value is '1' we get the max of top or prev '0' + 1 (where interval started). If
            it is '0' we just put '0' as all possible elements are greater than 0.
        2.1.3 Starting from the right, if value is '1' we get the min of top or prev '0' - 1 index (where interval ended).
        2.1.4 At the end we can calculate area as height * (right - left + 1) and get the max value for each cell.
 3. Implementation
    3.1 Check if the input array is valid. Set empty arrays for height, left and right values.
    3.2 For every row:
        3.2.1 Iterate all columns. If element is '1' - increase the height array column
            (it means we have continuous '1'), otherwise - set it to 0.
        3.2.2 Set prev indx to 0. Iterate all columns from left. If element is '1' - get the max(indx, left[j]).
            (value is an index - we select the closet bound from top -  previous rectangle - or current one),
            otherwise - set it to 0 and update indx to j+1 (as possible interval starts from the next element).
        3.2.3 Set prev indx to length-1. Iterate all columns from right. If element is '1' - get the min(indx, left[j]).
            (value is an index - we select the closet bound from top -  previous rectangle - or current one),
            otherwise - set it to 0 and update indx to j-1 (as possible interval starts from the prev element).
        3.2.4 Iterate over all elements and get the max value of height[j] * (right[j] - left[j] + 1)
    3.3 Return max value as the result of the method.
 */

class DPSolution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int[] height = new int[matrix[0].length];
        int[] left = new int[matrix[0].length];
        int[] right = new int[matrix[0].length];
        Arrays.fill(right, matrix[0].length - 1);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            int indx = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], indx);
                } else {
                    left[j] = 0;
                    indx = j + 1;
                }
            }

            indx = matrix[i].length - 1;
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], indx);
                } else {
                    right[j] = matrix[i].length - 1;
                    indx = j - 1;
                }
            }

            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, height[j] * (right[j] - left[j] + 1));
            }

        }

        return max;
    }
}
