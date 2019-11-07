package array.easy.pascals_triangle_ii_119;

import java.util.ArrayList;
import java.util.List;

/**
 119. Pascal's Triangle II
 https://leetcode.com/problems/pascals-triangle-ii/

 Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

 Note that the row index starts from 0.
 In Pascal's triangle, each number is the sum of the two numbers directly above it.

 Example:
 Input: 3
 Output: [1,3,3,1]

 Follow up:
 Could you optimize your algorithm to use only O(k) extra space?

 ------------------------

 1. Complexity
     1.1 Time Complexity is O(n^2) - where n is the number of elements in the list
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 In the first approach we populate triangle from the first element. The second approach uses recursion for
        generating previous row.
     2.2 Implementation
        2.2.1 Start with the list of one element. Create loop and add for every element its previous value.
        2.2.2 Add a new "1" element at 0 index at the end of the every iteration.
 */

public class Solution {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < result.size() - 1; j++) {
                result.set(j, result.get(j) + result.get(j + 1));
            }
            result.add(0, 1);
        }

        return result;
    }

//    public List<Integer> getRow(int level) {
//        if (level < 0 || level > 33) {
//            return null;
//        }
//        if (level == 0) {
//            return new ArrayList<>(Collections.singletonList(1));
//        }
//        if (level == 1) {
//            return new ArrayList<>(Arrays.asList(1, 1));
//        }
//
//        List<Integer> result = getRow(level - 1);
//        result.add(1);
//
//        for (int i = result.size() - 2; i > 0; i--) {
//            result.set(i, result.get(i - 1) + result.get(i));
//        }
//
//        return result;
//    }
}
