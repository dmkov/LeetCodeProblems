package dynamic.medium.partition_array_for_maximum_sum_1043;

import java.util.Arrays;

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
     2.1 For every element we need to consider K possible cases: 1) if we take only this element, 2) this and the next one,
        ... k) this, the next one ... and this + k -1 element. For every set define the max number and calculate the sub sum,
        then execute the backtracking method for the rest of elements.
 3 Implementation
     3.1 Check if input array of string is valid. Create memo array and fill it with -1.
     3.2 Start recursive function at the first element:
        3.2.1 If position overflow the array length, return 0 as the answer for the current range.
        3.2.2 Check if requested position is store in the memo array. If it is - return the value as the result of the method.
        3.2.3 For i = 0..k-1:
            3.2.3.1 Get the max number so far
            3.2.3.2 Calculate the sub sum as max * (i + 1)
            3.2.3.3 Get the max res as sum of sub sum and backtracking method for remaining items
        3.2.4 Store result to memo array and return it as the method result.
 */

class BacktrackingSolution {

    public int maxSumAfterPartitioning(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] memo = new int[arr.length];
        Arrays.fill(memo, -1);

        return backtracking(0, arr, K, memo);
    }

    private int backtracking(int pos, int[] arr, int k, int[] memo) {
        if (pos == arr.length) {
            return 0;
        }
        if (memo[pos] != -1) {
            return memo[pos];
        }

        int max = arr[pos], subSum = arr[pos];
        int res = arr[pos];
        for (int i = 0; i < k && pos + i < arr.length; i++) {
            max = Math.max(max, arr[pos + i]);
            subSum = max * (i + 1);

            res = Math.max(res, backtracking(pos + i + 1, arr, k, memo) + subSum);
        }

        memo[pos] = res;

        return res;
    }
}
