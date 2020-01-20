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
    2.1 The idea is to check only indexes where center of palindromes are possible
        ([i, i] for odd substrings and [i, i + 1] for even).
 2.2 Implementation
    2.2.1 Check if given string is valid.
    2.2.2 Set counter with max value to -1 and result string to "".
    2.2.3 Iterate from 0 to length and check two possible cases for every index. If substring is odd the center will be
        in the current element, if it is even - between the current and the next element.
    2.2.4 In a separate method check if characters at "start" and "end" indexes are the same, update max counter and result
        string if needed. Increase end and decrease start indexes. If they are in the string length, continue the process
        while characters s[start] == s[end].
 */

public class ExpandCenterSolution {
    private String result = "";
    private int max = -1;

    public String longestPalindrome(String s) { // baba
        if (s == null || s.length() == 0) {
            return "";
        }

        int length = s.length();
        for (int i = 0; i < length; i++) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }

        return result;
    }

    private void checkPalindrome(String s, int start, int end) {
        int length = s.length();
        while (start >= 0 && end < length && s.charAt(start) == s.charAt(end)) {
            if (end - start > max) {
                max = end - start;
                result = s.substring(start, end + 1);
            }
            start--;
            end++;
        }
    }
}
