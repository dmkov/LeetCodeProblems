package dynamic.easy.house_robber_198;

import java.util.Arrays;

/**
 198. House Robber
 https://leetcode.com/problems/house-robber/

 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:
 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.

 Example 2:
 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n) because of additional hash array
 2. Approach
    2.1 A robber has 2 options: a) rob current house i; b) don't rob current house.
    If an option "a" is selected it means he can't rob previous i-1 house but can safely proceed to the one before
    previous i-2 and gets all cumulative loot that follows.
    If an option "b" is selected robber gets all the possible loot from robbery of i-1 and all the previous buildings..
 2.2 Implementation
    2.2.1 Create additional memo array and max variable.
    2.2.2 Iterate through numbers. If i-2 exists then add it to the current number. If i-1 exists then compare for
    maximum current and i-1 number.
    2.2.3 Store maximum for the current number in memo array for future usage.
 */

class Solution {
    public int rob(int[] nums) {
        int max = 0;
        int[] memo = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i - 2 >= 0) {
                num += memo[i - 2];
            }
            int prev = 0;
            if (i - 1 >= 0) {
                prev = memo[i - 1];
            }
            max = Math.max(num, prev);
            memo[i] = max;
        }

        return max;
    }
}
