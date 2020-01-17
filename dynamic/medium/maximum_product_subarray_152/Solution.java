package dynamic.medium.maximum_product_subarray_152;

/**
 152. Maximum Product Subarray
 https://leetcode.com/problems/maximum-product-subarray/

 Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:
 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.

 Example 2:
 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 The idea is to track the min and the max values so far. If we have negative value, our min became the max after
        multiplying and vice versa
     2.2 Implementation
        2.2.1 Check if input array is valid.
        2.2.2 Set both negative and positive numbers to the first element. Also set first element as the max element so far.
        2.2.3 Start iteration the array. If number is less than 0, swap negative and positive numbers.
        2.2.4 Then do usual Math.max with the current value and stored product for positive and Math.min for the negative
            to continue tracking it. Get the maximum as Math.max from stored max and current positive value.
 */

class Solution {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int max = nums[0];
        int positive = nums[0];
        int negative = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] < 0) {
                int temp = positive;
                positive = negative;
                negative = temp;
            }

            positive = Math.max(nums[i], positive * nums[i]);
            negative = Math.min(nums[i], negative * nums[i]);

            max = Math.max(max, positive);
        }

        return max;
    }
}
