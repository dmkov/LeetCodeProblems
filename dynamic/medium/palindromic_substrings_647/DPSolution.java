package dynamic.medium.palindromic_substrings_647;

/**
 647. Palindromic Substrings
 https://leetcode.com/problems/palindromic-substrings/

 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".

 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 Note:
 The input string length won't exceed 1000.

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 The idea is to populate 2D matrix for every start-end point in the string. However, we optimize definition of
        palindrome for dp[i, j] = dp[i + 1, j - 1] && s.charAt(i) == s.charAt(j)
     2.2 Implementation
        2.2.1 Check if input string is valid.
        2.2.2 Create 2D dp array and populate it with initial false values.
        2.2.3 The trick is with the iterator. We should move by diagonal, so we increment k from 0..length and do internal loop
            for i 0..i+k<length. The j will be i+k in this approach.
        2.2.4 If i == k (diagonal) or s.charAt(i) == s.charAt(j) (2 char element) or additionally dp[i, j] = dp[i + 1, j - 1]
            for all others - set dp[i, j] to true and increment the result.
 */

class DPSolution {

    public int countSubstrings(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        int length = s.length();
        boolean[][] dp = new boolean[length][];
        for (int i = 0; i < length; i++) {
            dp[i] = new boolean[length];
        }

        for (int k = 0; k < length; k++) { // 1
            for (int i = 0; i + k < length; i++) {
                int j = i + k;
                if (i == j
                        || (j - i == 1 && s.charAt(i) == s.charAt(j))
                        || (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j))) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }

        return result;
    }
}
