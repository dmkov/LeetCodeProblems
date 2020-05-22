package array.hard.split_array_largest_sum_410;

/**
 410. Split Array Largest Sum
 https://leetcode.com/problems/split-array-largest-sum/

 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:

 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:
 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2 * m)
     1.2 Space Complexity is O(n^2)
 2. Approach
    2.1 Define dp[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts
    2.2 Consider the jth subarray. We can split the array from a smaller index k to i to form it.
        Thus dp[i][j] can be derived from max(f[k][j - 1], nums[k + 1] + ... + nums[i]).
        For all valid index k, dp[i][j] should choose the minimum value of the above formula.
 */

class DPSolution {

    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m < 1) {
            return 0;
        }
        if (m > nums.length) {
            m = nums.length;
        }

        int[][] dp = new int[nums.length+1][m+1];
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        int[] sum = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        // [7,2,5,10,8]
        // sum = [0,7,9,14,24,36]

        // dp[i][j] = Math.min(dp[i-k][j-1], sum of nums[i-k+1]...nums[i-k+2]...nums[i])

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                for (int k = 0; k < i; k++) {
                    int subSum = sum[i] - sum[k];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], subSum));

                }
            }
        }

        return dp[nums.length][m];
    }
}


/**
        0 1  2  3
     -  0 0  0  0
     7  0 7  0  0
     2  0 9  7  +
     5  0 14 7  7
     10 0 24 14 10
     8  0 36 18 14



    // [7,2,5,10,8]
    // 7,2,5,10   8
    // 7,2,5   10,8
    // 7,2   5,10,8
    // 7   2,5,10,8



     - Start with first position, return backtracking result
     -   for each number starting the pos and ending length-m:
         - add it to local sum
         - get the max of local sum and (backtracking result for the next pos and m-1) if m>1
         - return the min of all possible maxs

     O(n^m) without memoization

     pos_m as a cache key
     O(length * m)

 */
