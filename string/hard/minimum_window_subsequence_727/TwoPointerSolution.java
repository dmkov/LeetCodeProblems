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
    1.1 Time Complexity is O(n*m), where n is the length of the s1 and m is length of s2 (or length of s3)
    1.2 Space Complexity is O(n*m)
 2. Approach
    https://leetcode.com/problems/minimum-window-subsequence/discuss/109356/JAVA-two-pointer-solution-(12ms-beat-100)-with-explaination
    2.1 we can conduct two steps by using two pointers for this problem:
        - check feasibility from left to right
        - check optimization from right to left
    2.2 we can traverse from left to right, find a possible candidate until reach the first ending character of T
        eg: for the string s = abcdebdde and t = bde, we should traverse s string until we find first e,
        i.e. abcde, then traverse back from current "e" to find if we have other combination of bde with smaller
        length.
 */

class TwoPointerSolution {

    public String minWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length() || s2.length() == 0) {
            return "";
        }

        int n1 = s1.length();
        int n2 = s2.length();

        int l1 = 0;
        int r1 = 0;
        int p2 = 0;

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        while (r1 < n1) {
            if (s1.charAt(r1) == s2.charAt(p2)) {
                p2++;
            }
            if (p2 == n2) {
                p2--;
                l1 = r1;
                while (p2 >= 0) {
                    if (s1.charAt(l1) == s2.charAt(p2)) {
                        p2--;
                    }
                    l1--;
                }
                l1++;

                // pos from r1 and l1
                if (r1 - l1 < min) {
                    min = r1 - l1;
                    start = l1;
                    end = r1 + 1;
                }

                r1 = l1;
                p2 = 0;
            }

            r1++;
        }

        return (min == Integer.MAX_VALUE) ? "" : s1.substring(start, end);
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

     3. Two pointers sliding window
       - When we found a window with the right pointer, try to optimize it moving from right to left with another pointer
 */
