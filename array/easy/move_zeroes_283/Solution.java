package array.easy.move_zeroes_283;

import java.util.Arrays;

/**
 26. Remove Duplicates from Sorted Array
 https://leetcode.com/problems/remove-duplicates-from-sorted-array/

 283. Move Zeroes
 https://leetcode.com/problems/move-zeroes/

 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:
 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 ------------------------

 1. Complexity
     1.1 Time Complexity is O(n) - where n is the number of elements in the list
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 This solution is based on iterating the list with two pointers
     2.2 Implementation
         2.2.1 Set both pointers at the beginning of the list
         2.2.2 Iterate till you find first zero and number elements
         2.2.3 Check if they should be swapped (number is greater than zero) otherwise increment number index by 1.
 */

public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int zero = 0;
        int index = 0;
        while (index < nums.length) {
            while (nums[zero] != 0 && zero < nums.length - 1) zero++;
            while (nums[index] == 0 && index < nums.length - 1) index++;
            if (index > zero) {
                swap(nums, index, zero);
            }
            index++;
        }
    }

    private void swap(int[] nums, int index, int zero) {
        int temp = nums[index];
        nums[index] = nums[zero];
        nums[zero] = temp;
    }
}
