package dynamic.medium.number_of_dice_rolls_with_target_sum_1155;

import java.util.Arrays;

/**
 1155. Number of Dice Rolls With Target Sum
 https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/

 You have d dice, and each die has f faces numbered 1, 2, ..., f.
 Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

 Example 1:
 Input: d = 1, f = 6, target = 3
 Output: 1
 Explanation:
 You throw one die with 6 faces.  There is only one way to get a sum of 3.

 Example 2:
 Input: d = 2, f = 6, target = 7
 Output: 6
 Explanation:
 You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

 Example 3:
 Input: d = 2, f = 5, target = 10
 Output: 1
 Explanation:
 You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.

 Example 4:
 Input: d = 1, f = 2, target = 3
 Output: 0
 Explanation:
 You throw one die with 2 faces.  There is no way to get a sum of 3.

 Example 5:
 Input: d = 30, f = 30, target = 500
 Output: 222616187
 Explanation:
 The answer must be returned modulo 10^9 + 7.

 Constraints:

 1 <= d, f <= 30
 1 <= target <= 1000

 --------

 1. Complexity
     1.1 Time Complexity is O(d*f*target)
     1.2 Space Complexity is O(d*target)
 2. Approach
     2.1 The idea is to move from first dice to the last and populate number of steps to reach the every step in the next dice.
 3 Implementation
     3.1 Check if input array is valid.
     3.2 Create dp array and fill the first row with '1' at possible f positions.
     3.3 Iterate all dice, inside iterate from 0 to target and if dp[i, j] is not 0 (means it is possible to reach it),
        make another loop for all possible f values.
     3.4 Populate next row for the current element as sum of the existing value in the next cell and the current value.
     3.5 Do not forget to get modulo from the result.
     3.6 At the end return the last row at target index.
 */

class BottomToTopDPSolution {

    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        for (int i = 1; i <= f && target - i >= 0; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < target; j++) {
                if (dp[i][j] != 0) {
                    for (int k = 1; k <= f && j + k <= target; k++)
                        dp[i + 1][j + k] = (dp[i][j] + dp[i + 1][j + k]) % (1000000000 + 7);
                }
            }
        }

        return dp[d - 1][target];
    }

}
