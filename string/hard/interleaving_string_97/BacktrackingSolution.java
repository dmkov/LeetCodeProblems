package string.hard.interleaving_string_97;

import java.util.HashMap;
import java.util.Map;

/**
 97. Interleaving String
 https://leetcode.com/problems/interleaving-string/

 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 Example 1:
 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true

 Example 2:
 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false

 --------

 1. Complexity
    1.1 Time Complexity is O(2^(n+m)), where n is the length of the s1 and m is length of s2 (or length of s3)
    1.2 Space Complexity is O(n*m) for memoization and n+m for function stack
 2. Approach
    2.1 When one of the character is equal to str3 - we simply move our pointer. If both chars equal, we need to
        consider both possible movements and do a backtracking of the result.
 3 Implementation
    3.1 Return false if sum of two strings does not equal to the third string
    3.2 Start recursive function for pointers at 0 positions in all strings:
        3.2.1 If first pointer matches the third pointer - call function recursively and move first and third pointers.
        3.2.2 If second pointer matches the third pointer - move the second and the third pointers in the function.
        3.2.3 Return false or true if any of previous calls were successful.
    3.4 Add memoization with all 3 positions to reduce number of duplicated calls.
 */

class BacktrackingSolution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        Map<String, Boolean> memo = new HashMap<>();
        return backtracking(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean backtracking(String s1, String s2, String s3, int p1, int p2, int p3, Map<String, Boolean> memo) {
        if (p2 == s2.length() && p1 == s1.length()) {
            return true;
        }
        String key = p1 + "_" + p2 + "_" + p3;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean res = false;
        if (p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3)) {
            res = backtracking(s1, s2, s3, p1 + 1, p2, p3 + 1, memo);
        }
        if (p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3)) {
            res = res || backtracking(s1, s2, s3, p1, p2 + 1, p3 + 1, memo);
        }

        memo.put(key, res);
        return res;
    }

}
