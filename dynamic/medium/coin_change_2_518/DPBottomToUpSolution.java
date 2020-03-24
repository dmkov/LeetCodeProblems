package dynamic.medium.coin_change_2_518;

/**
 518. Coin Change 2
 https://leetcode.com/problems/coin-change-2/

 You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 Example 1:
 Input: amount = 5, coins = [1, 2, 5]
 Output: 4

 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1

 Example 2:
 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.

 Example 3:
 Input: amount = 10, coins = [10]
 Output: 1

 --------

 1. Complexity
     1.1 Time Complexity is O(c*n), where c is amount and n is number of coins
     1.2 Space Complexity is O(n)
 2. Approach
    https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
     2.1 The idea is to convert it to knapsack problem where i is amount and j is coins - so for every cell we sum up
        the number of options from previous row (if we do not take current coin) and number of options from [j-coin] cell
        (we take it, so we need to reduce the sum and get the max possible value from the reduced sum).
     2.2 Since there is only left to right iteration, we can optimize the space by only using one-dimension array.
 3 Implementation
     3.1 Check if input array is valid.
     3.2 Create dp array with the amount+1 length. Populate initial element [0] with 1 (as empty base option).
     3.2 Iterate for i from 1 to num of coins. Iterate j from 0 to the amount:
        3.2.1 For every cell, check if j is bigger or equals the coin on this row.
            3.2.2.1 If it does - update the value of dp[j] as the sum of prev dp[j] and dp[j-coins[i-1]] element.
            3.2.2.2 If not - do nothing
     3.3 Return dp[amount] as the answer.
 */

class DPBottomToUpSolution {

    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i-1]) {
                    dp[j] = dp[j] + dp[j-coins[i-1]];
                }
            }
        }

        return dp[amount];
    }
}
