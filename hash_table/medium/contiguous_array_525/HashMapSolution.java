package hash_table.medium.contiguous_array_525;

import java.util.HashMap;
import java.util.Map;

/**
 525. Contiguous Array
 https://leetcode.com/problems/contiguous-array/

 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

 Example 1:
 Input: [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

 Example 2:
 Input: [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 Note: The length of the given binary array will not exceed 50,000.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to encode every state by number (every 0 is -1 to the sum and 1 is +1). When we faced
        the same number that was before - it means we have the same number of 1s and 0s and can subtract indexes.
 3 Implementation
    3.1 Check if input array is valid
    3.2 Create a hash map, put 0 key with -1 index
    3.3 Iterate over all numbers:
        3.3.1 If number is 1, increase sum, otherwise - decrease it.
        3.3.2 Check if sum is already in the hash map. If yes - compute the difference, otherwise,
            put the current index there.
    3.4 Get the max distance between same values and return it as the result.
 */

class HashMapSolution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int res = 0;
        int sum = 0;

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }

            if (memo.containsKey(sum)) {
                res = Math.max(res, i - memo.get(sum));
            } else {
                memo.put(sum, i);
            }
        }

        return res;
    }
}
