package string.medium.decode_ways_91;

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
    2.1 Every next item in DP array is a sum of previous element (if current char is not 0)
        and the one before it (if previous char is '1' or '2' with the combination of '0-6' in the current char)
 */

class DPSolution {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = 0;
            char current = s.charAt(i - 1);
            if (current != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1) {
                char prev = s.charAt(i - 2);
                if (prev == '1' || (prev == '2' && current >= '0' && current <= '6')) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[s.length()];
    }

}
