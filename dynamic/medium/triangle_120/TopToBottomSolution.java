package dynamic.medium.triangle_120;

import java.util.Arrays;
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
    2.1 The approach is more complex than BottomToTop version, however it uses iterator instead of get() method in List interfaces.
        It has O(2n) space complexity because of array copy.
 2.2 Implementation
    2.2.1 Check if input list is not empty.
    2.2.2 Initialize `sum` array with length of the triangle. Iterate all rows from first to the last.
    2.2.3 Sum for the first element will be the value itself, for others - we need to check if 'j-1' and 'j' of previous level
        exist. If they are, find the min of them both and add the value.
    2.2.4 Return sum element with the minimal sum, it will be the answer.
 */

class TopToBottomSolution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int length = triangle.size();
        int[] sum = new int[length];

        int i = 0;
        for (List<Integer> row : triangle) {
            int j = 0;
            int[] prev = Arrays.copyOfRange(sum, 0, length);
            for (Integer num : row) {
                if (i == 0) {
                    sum[j] = num;
                } else {
                    sum[j] = num + Math.min(
                            (j > 0) ? prev[j - 1] : Integer.MAX_VALUE,
                            (j < i) ? prev[j] : Integer.MAX_VALUE
                    );
                }
                j++;
            }
            i++;
        }

        int min = Integer.MAX_VALUE;
        for (int num : sum) {
            min = Math.min(min, num);
        }

        return min;
    }
}
