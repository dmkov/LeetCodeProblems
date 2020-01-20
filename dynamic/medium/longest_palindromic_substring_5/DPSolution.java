package dynamic.medium.longest_palindromic_substring_5;

/**
 5. Longest Palindromic Substring
 https://leetcode.com/problems/longest-palindromic-substring/

 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:
 Input: "cbbd"
 Output: "bb"

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2), because of two loops to fill dp array n^2
    1.2 Space Complexity is O(n^2)
 2. Approach
    2.1 The idea is to compute and check every next level of palindromes not moving to the previous checking,
        so if X...X was marked as palindrome, YX...XY will be also palindrome if last and first characters are the same.
 2.2 Implementation
    2.2.1 Check if given string is valid.
    2.2.2 Set counter with max value to -1 and result string to "". Create a boolean dp[length][] array to populate [i][j]
        element, where i is start of the substring and j - its end.
    2.2.3 Iterate from 0 to length to increase shifting with the every new step. First we will check string of one chaarcter ([i][j]),
        then [i][j + 1], [i][j + 2] and so on.
    2.2.4 If i == j it means we enter row first time, create an array for j elements and set [i][j] to true (diagonal).
    2.2.5 If j - i == 1 (substring of two characters) set [i][j] to true, if character s[i] == s[j].
    2.2.6 Otherwise, check the previous substring dp[i + 1][j - 1] and characters s[i] == s[j].
    2.2.7 In case if max counter was exceeded, update it and result substring.
 */

public class DPSolution {
    public String longestPalindrome(String s) { // baba
        if (s == null || s.length() == 0) {
            return "";
        }

        int max = -1;
        String result = "";

        int length = s.length();
        boolean[][] dp = new boolean[length][];

        for (int k = 0; k < length; k++) { // 0
            for (int i = 0, j = i + k; j < length; i++, j++) {
                if (i == j) {
                    dp[i] = new boolean[length];
                    dp[i][j] = true;
                } else if (j - i == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else if (j - i > 1) {
                    dp[i][j] = (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
                }
                if (dp[i][j] && j - i > max) {
                    max = j - i;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}
