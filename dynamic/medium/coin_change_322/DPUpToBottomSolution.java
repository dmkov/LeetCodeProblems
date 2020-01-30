package dynamic.medium.coin_change_322;

/**
 322. Coin Change
 https://leetcode.com/problems/coin-change/

 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1

 Example 2:
 Input: coins = [2], amount = 3
 Output: -1
 Note:
 You may assume that you have an infinite number of each kind of coin.

 --------

 1. Complexity
     1.1 Time Complexity is O(c*n), where c is amount and n is number of coins
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Reduce the problem by extracting from the current amount every coin and call checking method recursively again.
 3 Implementation
     3.1 Check if input array is valid and amount is bigger than 0.
     3.2 For every coin, reduce the current amount and if a new amount is bigger than 0, call function recursively for all
        reduced amounts.
     3.3 Select the min result and store it to the memo array. Return the min number of steps to get to the current amount.
 */

class DPUpToBottomSolution {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || amount < 1) {
            return -1;
        }

        return search(coins, amount, new int[amount + 1]);
    }

    public int search(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }

        int result = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int steps = -1;
            if (coins[i] <= amount)
                steps = search(coins, amount - coins[i], memo) + 1;
            if (steps > 0)
                result = Math.min(result, steps);
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        memo[amount] = result;

        return result;
    }
}
