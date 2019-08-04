package array.medium.product_of_array_except_self_238;

/**
 238. Product of Array Except Self
 https://leetcode.com/problems/product-of-array-except-self/

 Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:
 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 For every element in the array we need to calculate product of all elements before it and after it.
    2.2 Using result list compute product of all past elements for every number in the list.
    2.3 Move from right to left and compute product of 'right' elements multiplying them on result values (left side)
 2.2 Implementation
    2.2.1 Check input list for null and length >= 2
    2.2.2 Create result list, iterate through all numbers and compute products of elements before the current. First
        result item will be always "1"
    2.2.3 Iterate from end to start. Compute product of 'right' items and multiple them on result value which has
        product of everything on the left of the current item.
 */

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int[] result = new int[nums.length];

        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int temp = nums[nums.length - 1];
        result[nums.length - 1] = result[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            result[i] *= temp;
            temp *= nums[i];
        }
        result[0] = temp;

        return result;
    }
}
