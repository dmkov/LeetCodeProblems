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
    1.1 Time Complexity is O(n*m), where n is the length of the s1 and m is length of s2 (or length of s3)
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 https://www.youtube.com/watch?v=ih2OZ9-M3OM
    2.2 https://leetcode.com/articles/interleaving-strings/
    2.3 The idea is that i+j element from s3 should be equal to s2(j) or s1(i) character. The second condition
        is that previous state for i or j respectively should be valid in dp[i-1][] or dp[i][j-1].
        Otherwise dp[i][j] = false
 3 Implementation
    3.1 Return false if sum of two strings does not equal to the third string
    3.2 Start two nested loops for i and j from 0 to s.length() (to include empty string as the first character):
        3.2.1 For dp[0][0] set true without computation.
        3.2.2 For other cases check that s3.charAt(i+j-1) equal:
            3.2.2.1 To s1.charAt(i - 1) with i > 0 and dp[i-1][j] == true
            3.2.2.2 To s2.charAt(j - 1) with j > 0 and dp[i][j-1] == true
    3.3 Return the last cell dp[s1.length()][s2.length()] from the matrix
 */

class DPSolution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                char s3Char = s3.charAt(i+j-1);
                dp[i][j] = (i > 0 && s3Char == s1.charAt(i - 1) && dp[i-1][j])
                        || (j > 0 && s3Char == s2.charAt(j - 1) && dp[i][j-1]);

            }
        }

        return dp[s1.length()][s2.length()];
    }

}
