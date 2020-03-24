package dynamic.medium.coin_change_2_518;

import java.util.HashMap;
import java.util.Map;

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
     2.1 Reduce the problem by extracting from the current amount every coin and call checking method recursively again.
 3 Implementation
     3.1 Check if input array is valid in case if amount is bigger than 0.
     3.2 Execute method recursively for the 0 position and full amount:
        3.2.1 For every coin, reduce the current amount and execute method again for the same index.
        3.2.2 If it is not the last position of elements - execute method for the next element with the original sum.
        3.2.3 In this way we choose one of two ways: 1) apply the coin or 2) move to the next one
        3.2.4 If we reach 0 amount - return 1 (as possible option), if amount is < 0 - return 0.
        3.2.5 Get the sum of all executions and return it as the result
     3.3 Use the position and remaining amount for the key in the memo array.
 */

class DPUpToBottomSolution {

    public int change(int amount, int[] coins) {
        if (coins == null || (coins.length == 0 && amount > 0)) {
            return 0;
        }

        Map<String, Integer> memo = new HashMap<>();
        return traverse(coins, 0, amount, memo);
    }

    private int traverse(int[] coins, int pos, int amount, Map<String, Integer> memo) {
        if (amount <= 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }

        String key = pos + "_" + amount;
        if (memo.get(key) != null) {
            return memo.get(key);
        }

        int sum = traverse(coins, pos, amount - coins[pos], memo);
        if (pos < coins.length - 1) {
            sum += traverse(coins, pos + 1, amount, memo);
        }

        memo.put(pos + "_" + amount, sum);
        return sum;
    }
}
