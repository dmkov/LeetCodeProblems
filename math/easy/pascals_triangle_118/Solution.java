package math.easy.pascals_triangle_118;

import java.util.ArrayList;
import java.util.List;

/**
 118. Pascal's Triangle
 https://leetcode.com/problems/pascals-triangle/

 Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 In Pascal's triangle, each number is the sum of the two numbers directly above it.

 Example:
 Input: 5
 Output:
 [
      [1],
     [1,1],
    [1,2,1],
   [1,3,3,1],
  [1,4,6,4,1]
 ]

 ---

 1. Complexity
    1.1 Time Complexity is O(n^2)
    1.2 Space Complexity is O(n^2)
 2. Approach
    2.1 There are some points to consider: first and last elements in the row are always 1,
        every j element is a sum of j-1 and j of the previous row
    2.2 Implementation
        2.2.1 Iterate from 0 to numRows-1. Create ArrayList or every row
        2.2.2 Iterate inside the row. If element has first or last index assign 1 to it, otherwise calculate
            sum of j-1 and j of the previous row.
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows < 1) return result;

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
