package array.hard.russian_doll_envelopes_354;

import java.util.Arrays;

/**
 354. Russian Doll Envelopes
 https://leetcode.com/problems/russian-doll-envelopes/

 You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Note:
 Rotation is not allowed.

 Example:
 Input: [[5,4],[6,4],[6,7],[2,3]]
 Output: 3
 Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

 --------

 1. Complexity
     1.1 Time Complexity is O(nlogn + n^2)
     1.2 Space Complexity is O(n)
 2. Approach
    2.1 Sort array to have continously not-decreasing list
    2.2 For every element iterate past items and count how many smaller items were before it
        - for every element tracks the counter, if array is bigger -> take this counter and increment it
 */

class DPSolution {

    public int maxEnvelopes(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        Arrays.sort(arr, (a, b) -> compare(a, b));

        int max = 0;
        int[] dp = new int[arr.length];
        for (int j = 0; j < n; j++) {
            int[] pair = arr[j];
            for (int k = 0; k < n; k++) {
                if (j == k) {
                    continue;
                }
                if (arr[k][0] < pair[0] && arr[k][1] < pair[1] && dp[j] < dp[k] + 1) {
                    dp[j] = dp[k] + 1;
                    max = Math.max(max, dp[j]);
                }
            }
        }

        return max + 1;
    }

    private int compare(int[] arr1, int[] arr2) {
        if (arr1[0] == arr2[0]) {
            return arr2[1] - arr1[1];
        }
        return arr1[0] - arr2[0];
    }
}


/**

 1. The width and heigth is actually intergers? Numbers from 0 to MAX_VALUE
 2. There is always a pair of these values?
 3. We can put one pair into another if it is smaller (strict) than the parent.
 4. How many pairs do I have?
 5. What is the answer? The max possible number.


     1. Brute force solution:
         - for every pair find the number of other pairs that can fit (one pair over a time),
         O(2^n*n)
     2. Sort pair and backtrack each number (consider and narrow limits or not).

         [7,1], [6,7], [6,4], [5,4], [2,3], [1,7], [1,1]
         - sort elements by w, then by h
         - for every element check the rest of the list to possible fit


        [6,7], [1,7], [6,4], [5,4], [2,3], [7,1], [1,1]

        O(nlogn + 2^n)

     3. Matrix n, iterate n times
         sort elements
         for every element - check the max of fit elements in the matrix and add +1, get the max with the current value
         end when no items were changed

         O(nlogn + n * n), space O(n)

 */
