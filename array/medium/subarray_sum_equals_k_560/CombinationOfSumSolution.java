package array.medium.subarray_sum_equals_k_560;

/**
 560. Subarray Sum Equals K
 https://leetcode.com/problems/subarray-sum-equals-k/

 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2

 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 For every element calculate sum with remaining items. When sum equals to k it means we get the required sub sum.
 2.2 Implementation
    2.2.1 Check if input array is valid
    2.2.2 Iterate from first element to the last in the list. For every iteration, initialize sum with '0'.
    2.2.3 Start adding sum (cumulative) and compare it with k. If sum equals k, increment the result counter.
 */

public class CombinationOfSumSolution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }

        return result;
    }
}
