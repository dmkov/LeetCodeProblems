package dynamic.medium.ones_and_zeroes_474;

import java.util.HashMap;

/**
 474. Ones and Zeroes
 https://leetcode.com/problems/ones-and-zeroes/

 In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 Note:
 The given numbers of 0s and 1s will both not exceed 100
 The size of given string array won't exceed 600.

 Example 1:
 Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 Output: 4

 Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

 Example 2:
 Input: Array = {"10", "0", "1"}, m = 1, n = 1
 Output: 2

 Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 --------

 1. Complexity
     1.1 Time Complexity is O(length * m * n) with memoization and O(2^n) without it
     1.2 Space Complexity is O(length * m * n)
 2. Approach
     2.1 For every element we need to consider two cases: 1) if we take it into account or 2) if we skip it.
        Take max value from these two options and use memoization with computed key for position_m_n string in the hash map.
 3 Implementation
     3.1 Check if input array of string is valid. Create empty maps for string and memo object.
     3.2 Start recursive function at the first element:
        3.2.1 In the recursive method check if key position_m_n was computed previously. If it was, then return the stored value.
        3.2.2 Calculate number of '1' and '0' in the string. Also use a hash map to reduce work on repeated strings.
        3.2.3 Consider first option if m-'0's and n-'1's are greater or equal to 0. Get the value from method after extracting
            m and n values for the next index. Also add +1 to the result for the current element.
        3.2.4 Execute method for the next index without changes in m and n variables.
        3.2.5 Get the max value of both executions. Store it in the map and return it as the result of the method.
 */

class BacktrackingSolution {

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        HashMap<String, int[]> map = new HashMap<>();
        HashMap<String, Integer> memo = new HashMap<>();

        return find(strs, 0, m, n, map, memo);
    }

    private int find(String[] strs, int pos, int m, int n, HashMap<String, int[]> map, HashMap<String, Integer> memo) {
        if (pos == strs.length) {
            return 0;
        }

        String key = pos + "_" + m + "_" + n;
        if (memo.get(key) != null) {
            return memo.get(key);
        }

        String str = strs[pos]; // 10
        int[] occur = map.get(str);
        if (occur == null) {
            occur = new int[2];
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    occur[0]++;
                } else if (str.charAt(i) == '1') {
                    occur[1]++;
                }
            }
            map.put(str, occur);
        }

        int taken = -1;
        if (m - occur[0] >= 0 && n - occur[1] >= 0) {
            taken = find(strs, pos + 1, m - occur[0], n - occur[1], map, memo) + 1;
        }
        int notTaken = find(strs, pos + 1, m, n, map, memo);
        int max = Math.max(taken, notTaken);

        memo.put(key, max);

        return max;
    }
}
