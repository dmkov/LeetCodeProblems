package string.medium.decode_ways_91;

import java.util.HashMap;
import java.util.Map;

/**
 91. Decode Ways
 https://leetcode.com/problems/decode-ways/

 A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:
 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).

 Example 2:
 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 --------

 1. Complexity
    1.1 Time Complexity is O(n), with memoization we proceed every index once
    1.2 Space Complexity is O(n) for memoization and for function stack
 2. Approach
    2.1 If character is '1' or '2' we can consider alternative way - skip the next character and add result to the
        regular iteration. Summing all branches at the end will give us a result
 */

class BacktrackingSolution {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Integer, Integer> memo = new HashMap<>();
        return backtracking(s, 0, memo);
    }

    private int backtracking(String str, int pos, Map<Integer, Integer> memo) {
        if (pos == str.length()) {
            return 1;
        }
        if (pos > str.length() || str.charAt(pos) == '0') {
            return 0;
        }

        Integer cache = memo.get(pos);
        if (cache != null) {
            return cache;
        }

        int res = backtracking(str, pos + 1, memo);
        if ((str.charAt(pos) == '1' || str.charAt(pos) == '2') && pos + 1 < str.length()) {
            if (str.charAt(pos) == '1' || (str.charAt(pos + 1) >= '0' && str.charAt(pos + 1) <= '6')) {
                res += backtracking(str, pos + 2, memo);
            }
        }

        memo.put(pos, res);
        return res;
    }

}
