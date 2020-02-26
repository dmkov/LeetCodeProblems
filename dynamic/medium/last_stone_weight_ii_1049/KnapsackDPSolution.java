package dynamic.medium.last_stone_weight_ii_1049;

import java.util.HashMap;
import java.util.Map;

/**
 1049. Last Stone Weight II
 https://leetcode.com/problems/last-stone-weight-ii/

 We have a collection of rocks, each rock has a positive integer weight.

 Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

 If x == y, both stones are totally destroyed;
 If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

 Example 1:
 Input: [2,7,4,1,8,1]
 Output: 1
 Explanation:
 We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.


 Note:
 1 <= stones.length <= 30
 1 <= stones[i] <= 100

 --------

 1. Complexity
     1.1 Time Complexity is O(K * n)
     1.2 Space Complexity is O(K * n)
 2. Approach
     2.1 The idea behind solution is that every number can go into two different groups that should be subtracted one
        from another at the end. So we need to find the most efficient way to pack the first half to maximise the sum.
        In this way it is similar to knapsack problem.
 3 Implementation
     3.1 Check if input array is valid, create memo hash map.
     3.2 Calculate total sum of all elements and get half of this sum - it is our ideal target.
     3.3 Create a dp array with 0..number_of_elements in the row and 0..half for columns
     3.4 Iterate two loops to populate knapsack:
        3.4.1 If j (sum) is less than value of stone - dp[i][j] will be value of dp[i-1][j]
        3.4.3 Otherwise, we need to get max value of previous element in the chain + current value or sum if we do not use
            the current element.
     3.5 Return difference between total sum and two 'populated' knapsacks.
 */

class KnapsackDPSolution {
    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        int n = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int half = sum / 2;

        int[][] dp = new int[n + 1][half + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= half; j++) {
                if (j < stones[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }

        return sum - dp[n][half] * 2;
    }
}
