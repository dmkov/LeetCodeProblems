package array.easy.maximum_average_subarray_i_643;

/**
 643. Maximum Average Subarray I
 https://leetcode.com/problems/maximum-average-subarray-i/

 Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

 Example 1:
 Input: [1,12,-5,-6,50,3], k = 4
 Output: 12.75
 Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 We need to define sum of the first possible subarray. Save it to the max variable.
    2.2 The reduce amount of the first number and add next number to the sum. Compare it to the max variable.
    2.2 At the end return max variable and do not forget to divide it by k to get an average value.
 */

public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0 || nums.length < k)
            return 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return (double)maxSum/k;
    }
}
