package dynamic.medium.coin_change_322;

import java.util.LinkedList;

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
     1.1 Time Complexity is O(n^3)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 For every possible step from 0 to amount, check other possible steps for every available coin while amount
        is not reached. I used queue to track the next steps but it also can be implemented with iteration from 1..amount with
        the 1 as a step. More actions but no extra space.
 3 Implementation
     3.1 Check if input array is valid and amount is bigger than 0.
     3.2 Create queue to track next steps to consider. Add '0' as the first point there.
     3.3 While queue is not empty, get the amount from it, increment for all coins and if sum is not bigger as total amount -
        store new points to the queue. In additional dp array track the min number of steps.
 */

class DPBottomToUpSolution {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || amount < 1) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);

        while(queue.size() > 0) {
            int num = queue.remove();
            for (int coin : coins) {
                if (amount - num - coin >= 0
                        && (dp[num + coin] == 0 || dp[num + coin] > dp[num] + 1)) {
                    dp[num + coin] = dp[num] + 1;
                    queue.add(num + coin);
                }
            }
        }

        return (dp[amount] == 0) ? -1 : dp[amount];
    }
}
