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
        from another at the end. In math it means that every number can be written with "+" or "-" sign before it.
        We need to evaluate all possible combinations and then get the min positive value (negative values should be skipped).
 3 Implementation
     3.1 Check if input array is valid, create memo hash map.
     3.2 Call method recursively with the 0 position and 0 sum at the beginning.
     3.3 In the recursive method:
        3.3.1 Check if given pos index equals length - then return sum as the result of the method.
        3.4.2 Create a key for the cache as concat string with pos and sum and check if the result is in the hash map.
        3.4.3 Otherwise, get the min value of the two calls - for the next position with (sum + number) and (sum - number)
        3.4.4 Put the result into hash map.
     3.4 Note, that if result is negative it should not be considered in comparing.
 */

class RecursiveMathSolution {
    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        Map<String, Integer> memo = new HashMap<>();

        return check(0, 0, stones, memo);
    }

    private int check(int pos, int sum, int[] stones, Map<String, Integer> memo) {
        if (pos == stones.length) {
            return (sum < 0) ? Integer.MAX_VALUE : sum;
        }
        String key = sum + "_" + pos;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = Math.min(
                check(pos + 1, sum - stones[pos], stones, memo),
                check(pos + 1, sum + stones[pos], stones, memo)
        );
        memo.put(key, res);

        return res;
    }
}
