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
     2.1 It is more simple than DP. The middle of the palindrome could be in one of 2N - 1 positions: either at letter or between two letters.
        So we need to check all possible positions and extend pointers if the current value is palindrome.
     2.2 Implementation
        2.2.1 Check if input string is valid.
        2.2.2 Move k from 0 to 2N - 1, i will be k / 2 and j will be i + k % 2.
        2.2.3 While palindrome is valid s.charAt(i) == s.charAt(j) - extend j and i and increase the counter.
 */

class ExpandCenterSolution {

    public int countSubstrings(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        int length = s.length();
        for (int k = 0; k < length * 2; k++) {
            int i = k / 2;
            int j = i + k % 2;
            while (i >= 0 && j < length && s.charAt(i) == s.charAt(j)) {
                result++;
                i--;
                j++;
            }
        }

        return result;
    }
}
