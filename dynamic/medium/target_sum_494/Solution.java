package dynamic.medium.target_sum_494;

/**
 494. Target Sum
 https://leetcode.com/problems/target-sum/

 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5

 Explanation:
 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 --------

 1. Complexity
    1.1 Time Complexity is O(nlog) because of sorting
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 After sorting both lists go through the children list and match greater or equal cookie values.
 2.2 Implementation
    2.2.1 Sort both lists. Iterate children. Find the first value in cookies that greater or equal current child.
        Then increment both pointers. Otherwise break the loop.
 */

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] opts = new int[nums.length][];
        opts[0] = new int[]{nums[0], -nums[0]};

        for (int i = 1; i < nums.length; i++) {
            int size = opts[i - 1].length * 2;
            int[] current = new int[size];

            for (int k = 0; k < size; k = k + 2) {
                int val = opts[i - 1][k / 2];
                current[k] = val + nums[i];
                current[k + 1] = val - nums[i];
            }
            opts[i] = current;
        }

        int result = 0;
        for (int sum: opts[opts.length - 1]) {
            if (sum == S) {
                result++;
            }
        }
        return result;
    }
}
