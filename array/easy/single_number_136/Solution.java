package array.easy.single_number_136;

import java.util.Arrays;

/**
 136. Single Number
 https://leetcode.com/problems/single-number/

 Given a non-empty array of integers, every element appears twice except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:
 Input: [2,2,1]
 Output: 1

 Example 2:
 Input: [4,1,2,1,2]
 Output: 4

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The solution is based on XOR operator. a XOR 0 = a and a XOR a = 0.
        So a XOR b XOR a = (a XOR a) XOR b = 0 XOR b = b
 2.2 Implementation
    2.2.1 Iterate all numbers in the list and apply them with XOR to the result
 */

class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int result = 0;
        for (int num: nums) {
            result ^= num;
        }

        return result;
    }
}
