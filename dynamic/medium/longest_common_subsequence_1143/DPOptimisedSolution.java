package dynamic.medium.longest_common_subsequence_1143;

/**
 1143. Longest Common Subsequence
 https://leetcode.com/problems/longest-common-subsequence/

 Given two strings text1 and text2, return the length of their longest common subsequence.

 A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 If there is no common subsequence, return 0.

 Example 1:
 Input: text1 = "abcde", text2 = "ace"
 Output: 3
 Explanation: The longest common subsequence is "ace" and its length is 3.

 Example 2:
 Input: text1 = "abc", text2 = "abc"
 Output: 3
 Explanation: The longest common subsequence is "abc" and its length is 3.

 Example 3:
 Input: text1 = "abc", text2 = "def"
 Output: 0
 Explanation: There is no such common subsequence, so the result is 0.

 --------

 1. Complexity
     1.1 Time Complexity is O(n*m)
     1.2 Space Complexity is O(max(n,m))
 2. Approach
     2.1 The lines and columns of the matrix represent two words. dp[i][j] shows how many characters in subsequence
        for str1[0..i] and str[0..j].
     2.2 If characters match - we increase counter for previous substrings dp[i-1][j-1]
     2.3 If characters do not match - we take the max from dp[i][j-1] or dp[i-1][j]
 */

class DPOptimisedSolution {

    public int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        int l1 = s1.length();
        int l2 = s2.length();

        int[] prev = new int[l2+1];
        for (int i = 1; i <= l1; i++) {
            int[] cur = new int[l2+1];
            for (int j = 1; j <= l2; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    cur[j] = Math.max(cur[j-1], prev[j]);
                } else {
                    cur[j] = prev[j-1] + 1;
                }
            }
            prev = cur;
        }

        return prev[l2];
    }


    /**

        sqr
       0000000
     q 0011111
     r 0012222

        aba
       0000
     a 0111
     a 0112
     a 0112

        kbaldm
       0000000
     a 0001111
     b 0011111
     c 0011111
     d 0011122
     e 0011122
     m 0011123


        abcdem
       0000000
     k 0000000
     b 0011111
     l 0011111
     d 0011222
     m 0011223

     "qrsvwf"
     "sqrypy"


     1. What is the output result?
     2. In other words, I need to find the max number of elements after the current position
     that present in another word in the same order.

         "abcdem", "kbaldm" -> bdm
         "abcd", "cbd" -> cd || bd
         "ab", "" -> ""
         "abc, "a" -> a
         "aaa", "aba" -> aa

         "qrsvwf", "qrypy" -> qr


     1. If not match -> get the max from top or left
     2. If match -> get the min from top or left and add 1


       abcdd
     c 00111
     b 01111
     d 01122
     d 01123


        abcade
       0000000
     a 0111111
     a 0111222
     c 0112222
     e 0112223


     */
}
