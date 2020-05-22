package string.hard.edit_distance_72;

/**
 72. Edit Distance
 https://leetcode.com/problems/edit-distance/

 Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:
 Insert a character
 Delete a character
 Replace a character

 Example 1:
 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')

 Example 2:
 Input: word1 = "intention", word2 = "execution"
 Output: 5

 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')

 --------

 1. Complexity
    1.1 Time Complexity is O(n1*n2)
    1.2 Space Complexity is O(n1*n2)
 2. Approach
    2.1 Edit distance is solved using Levenshtein distance algorithm
    2.2 If word1[i] == word2[j]
        f(i, j) = f(i - 1, j - 1)
    2.2 If word1[i] != word2[j]
        f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
         f(i, j - 1) represents insert operation
         f(i - 1, j) represents delete operation
         f(i - 1, j - 1) represents replace operation
 */

class DPSolution {

    public int minDistance(String word1, String word2) {
        if (word1 == null) {
            word1 = "";
        }
        if (word2 == null) {
            word2 = "";
        }

        int n1 = word1.length();
        int n2 = word2.length();

        int[][] dp = new int[n1+1][n2+1];
        for (int j = 0; j <= n2; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]
                    ) + 1;
                }
            }
        }

        return dp[n1][n2];
    }
}


/**

        horse
       012345
     r 112234
     o 221234
     s 332323

     if match -> take previous diagonal
           != -> min(left+1,top+1,diagonal+1)

 */
