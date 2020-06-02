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

    public String minWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length() || s2.length() == 0) {
            return "";
        }

        int n1 = s1.length();
        int n2 = s2.length();
        int[] dp = new int[n1];

        for (int i = 0; i < n1; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i] = 1;
            }
        }
        for (int j = 1; j < n2; j++) {
            boolean found = false;
            int[] newDp = new int[n1];
            int counter = Integer.MAX_VALUE;
            for (int i = 0; i < n1; i++) {
                if (s1.charAt(i) == s2.charAt(j) && found) {
                    newDp[i] = counter;
                }

                if (dp[i] != 0) {
                    counter = Math.min(counter, dp[i]);
                    found = true;
                }
                if (found) {
                    counter++;
                }
            }
            dp = newDp;
        }

        int min = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = n1 - 1; i >= 0; i--) {
            if (dp[i] != 0 && min >= dp[i]) {
                min = dp[i];
                pos = i;
            }
        }

        String res = "";
        if (pos != -1) {
            res = s1.substring(pos - min + 1, pos + 1);
        }

        return res;
    }
}

/**

     abbcdfefef , bef
     -> bcdfef

     abbcdfefbef , bef
     -> bef

     -- Find the min window containing all characters from s2 in the same order

     1. Naive approach
        - for every first character of s2 in s1 - try to get a subsequence and return the minimal one

     2. DP approach
        dp[i][j] = s2[0..i] in s1[0..j]

     abbcdfefef , bef
     |       |   | |


       abbcdfefbeebf
     b 0110000010010
     f 0000040600002
     e 0000005008900


 */
