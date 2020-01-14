package dynamic.medium.triangle_120;

import java.util.List;

/**
 120. Triangle
 https://leetcode.com/problems/triangle/

 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 For example, given the following triangle

   [
        [2],
       [3,4],
      [6,5,7],
     [4,1,8,3]
   ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m) because of two loops iterating every element
    1.2 Space Complexity is O(n) where n is number of max element in the row (or number of rows)
 2. Approach
    2.1 The approach is a bit tricky and can not be accepted for 100% because it uses get() method in List interfaces.
        However, it is more simpler and short than top to bottom traverse.
 2.2 Implementation
    2.2.1 Check if input list is not empty.
    2.2.2 Initialize `sum` array with length + 1 to simplify bounding for the last right element. Iterate from last row
        to the first. In every row do another horizontal cycle for elements.
    2.2.3 Sum for the element will be the element itself and min value of 'j' or 'j+1' element in the previous (or
        technically the next) row.
    2.2.4 Return sum[0] at the end of processing.
 */

class BottomToTopSolution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int length = triangle.size();
        int[] sum = new int[length + 1];

        for (int i = length - 1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            int size = row.size();
            for (int j = 0; j < size; j++) {
                sum[j] = Math.min(sum[j], sum[j + 1]) + row.get(j);
            }
        }

        return sum[0];
    }
}
