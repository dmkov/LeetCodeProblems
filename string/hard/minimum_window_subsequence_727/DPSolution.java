package string.hard.minimum_window_subsequence_727;

/**
 727. Minimum Window Subsequence
 https://leetcode.com/problems/minimum-window-subsequence/

 Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

 If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

 Example 1:
 Input:
 S = "abcdebdde", T = "bde"
 Output: "bcde"
 Explanation:
 "bcde" is the answer because it occurs before "bdde" which has the same length.
 "deb" is not a smaller window because the elements of T in the window must occur in order.

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m), where n is the length of the s1 and m is length of s2
    1.2 Space Complexity is O(n*m)
 2. Approach
    dp[i][j] = s2[0..i] in s1[0..j]
    increasing window in both string put into dp table start index when the substring is matched
 */

class DPSolution {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] prev = new int[s.length() + 1];
        int[] curr = new int[s.length() + 1];

        prev[0] = -1;
        char ch = t.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (ch == s.charAt(i)) {
                prev[i + 1] = i;
            } else {
                prev[i + 1] = prev[i];
            }
        }
        for (int j = 1; j < t.length(); j++) {
            curr[0] = -1;
            ch = t.charAt(j);
            for (int i = 0; i < s.length(); i++) {
                if (ch == s.charAt(i)) {
                    curr[i + 1] = prev[i];
                } else {
                    curr[i + 1] = curr[i];
                }
            }
            prev = curr;
            curr = new int[s.length() + 1];
        }
        /**
         abcdebdde
         prev = ---------1
         */

        int pos = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prev.length; i++) {
            if (prev[i] != -1 && i - prev[i] < min) {
                pos = prev[i];
                min = i - prev[i];
            }
        }

        return (pos != -1) ? s.substring(pos, pos + min) : "";
    }
}

/**

     abbcdfefef , bef
     -> bcdfef

     abbcdfefbef , bef
     -> bef

     -- Find the min window containing all characters from s2 in the same order

     1. Naive approach. Iterate chars in S, if char matches first char in T - check others.
     Repeat for every next character, get the min interval.
        Time complexity is O(s*s)

     2. Two pointers. After getting all chars with the right pointer - move the left pointer to
     reduce the window.
        Time complexity is O(2*s)

     3. DP approach
            abcdebdde
           0012345678
         b 0011115555
         d 0000111555
         e 0000011115
         e 0000000001

           abcd
         a-0000
         b--000

         1. Populate the first row with -1 and matched indexes for the first character
         2. For every next row (next T char), look for the match and assign i-1 value from the top
         3. At the very end - iterate all values and check the min interval
        Time complexity - O(s*t), space complexity - O(2*s)

     abbcdfefef , bef
     |       |   | |


 */
