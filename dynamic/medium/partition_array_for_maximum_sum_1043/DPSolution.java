package dynamic.medium.partition_array_for_maximum_sum_1043;

/**
 1043. Partition Array for Maximum Sum
 https://leetcode.com/problems/partition-array-for-maximum-sum/

 Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.

 Return the largest sum of the given array after partitioning.

 Example 1:
 Input: A = [1,15,7,9,2,5,10], K = 3
 Output: 84
 Explanation: A becomes [15,15,15,9,10,10,10]

 Note:
 1 <= K <= A.length <= 500
 0 <= A[i] <= 10^6

 --------

 1. Complexity
     1.1 Time Complexity is O(length * k) with memoization and O(2^length)? without it
     1.2 Space Complexity is O(length)
 2. Approach
     2.1 dp[i] is the maximum of dp[i-j] + max(A[i-1], ..., A[i-j]) * j
     2.2 On every step we check the previous max sum and compare it with sub sum for past K elements. If any of results
        is bigger than the previously stored sum - update it.
 3 Implementation
     3.1 Check if input array of string is valid. Create dp array to store max sum on every step
     3.2 Populate first dp[0] element as arr[0]
     3.2 For all others (i = 1..arr.length-1):
        3.2.1 Set temporary max as i-th element
        3.2.2 Update dp[i] as sum of dp[i-1] and arr[i]
        3.2.3 For k = 1..K-1 && (i - k) >= 0:
            3.2.3.1 Update max as Math.max(max, arr[i - k]) - max element in k last numbers
            3.2.3.2 Calculate the sub sum as max * (k + 1)
            3.2.3.3 Update dp[i] as max of previous dp[i] or dp[i-k-1] + sub sum (also do not forget about smaller bound)
        3.2.4 Return the last dp[] as the result of the method.
 */

class DPSolution {

    public int maxSumAfterPartitioning(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int max = arr[i];
            dp[i] = dp[i - 1] + arr[i];
            for (int k = 1; k < K && (i - k) >= 0; k++) {
                max = Math.max(max, arr[i - k]);
                int sub = max * (k + 1);
                dp[i] = Math.max(sub + ((i - k - 1 >= 0) ? dp[i - k - 1] : 0), dp[i]);
            }
        }

        return dp[arr.length - 1];
    }
}
