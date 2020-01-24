package bit_operations.medium.single_number_iii_260;

import java.util.Arrays;

/**
 260. Single Number III
 https://leetcode.com/problems/single-number-iii/

 Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

 Example:
 Input:  [1,2,1,3,2,5]
 Output: [3,5]

 Note:
 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is size of bigger array
    1.2 Space Complexity is O(1) where m is the size of smaller array
 2. Approach
    2.1 The main idea is that bitwise XOR on the same number twice gives "0" at the end.
 3. Implementation
    3.1 Check if the input arrays are valid
    3.2 Get a mask by applying all numbers from the array using bitwise "XOR". It gives the number where all bits are
        given from two searched numbers.
    3.3 The next trick is to get the one bit from received number. To get it - invert the mask and add one number, then
        do a binary '&' to the mask. So we have only smallest bit checked from it.
    3.4 Iterate all numbers again but this time apply only numbers that have extracted bit selected (by checking '&').
        At the end we have X, apply it to the initial mask and get Y.
 */

class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int mask = 0;
        for (int num : nums) {
            mask = mask ^ num;
        }

        int negative = (~mask) + 1;
        int xMask = mask & negative;

        int x = 0;
        for (int num : nums) {
            if ((num & xMask) != 0) x = x ^ num;
        }
        int y = mask ^ x;

        return new int[]{x, y};
    }
}
