package sort.easy.largest_perimeter_triangle_976;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 976. Largest Perimeter Triangle (easy)
 https://leetcode.com/problems/largest-perimeter-triangle/

 Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

 If it is impossible to form any triangle of non-zero area, return 0.

 Example 1:
 Input: [2,1,2]
 Output: 5

 Example 2:
 Input: [1,2,1]
 Output: 0

 Example 3:
 Input: [3,2,3,4]
 Output: 10

 Example 4:
 Input: [3,6,2,3]
 Output: 8

 Note:
 3 <= A.length <= 10000
 1 <= A[i] <= 10^6

---

 1. Complexity
    1.1 Time Complexity is O(nlog n) because of sorting
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The longest length of the triangle should be less than sum of two other lengths
    2.2 Sort array and check from longest to shortest pairs of lengths

 */
public class Solution {
    public int largestPerimeter(int[] A) {
        if (A == null || A.length < 3) return 0;
        Arrays.sort(A);

        for (int i = A.length - 1; i > 1; i--)
            if (A[i] < A[i - 1] + A[i - 2])
                return A[i] + A[i - 1] + A[i - 2];
        return 0;
    }
}
