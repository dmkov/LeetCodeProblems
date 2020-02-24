package dynamic.medium.ones_and_zeroes_474;

/**
 474. Ones and Zeroes
 https://leetcode.com/problems/ones-and-zeroes/

 In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 Note:
 The given numbers of 0s and 1s will both not exceed 100
 The size of given string array won't exceed 600.

 Example 1:
 Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 Output: 4

 Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

 Example 2:
 Input: Array = {"10", "0", "1"}, m = 1, n = 1
 Output: 2

 Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 --------

 1. Complexity
     1.1 Time Complexity is O(length * m * n)
     1.2 Space Complexity is O(length * m * n)
 2. Approach
     2.1 Like in backpack solution we need to compare options of taking the word or leave it. To store result we use
        3D array with i row for every element, and j/k for '0' and '1'
 3 Implementation
     3.1 Check if input array of string is valid. Create empty maps for string and memo object.
     3.2 Create 3D array and start iterating over it. First dimension is strs.length, then m and n.
        Use +1 element in size to store 0 in the base case.
     3.3 For every string - parse it to get number of '0' and '1'.
     3.4 Iterate remaining two dimensions from 0 to m and from 0 to n:
        3.4.1 If both j and k are bigger than number of '0' and '1' in the string, get the max of:
            1) same j and k indexes at the previous row (i - 1) -- case when we do not take the current string
            2) j - '0's and k - '1's numbers at the previous row (i - 1) + 1 for the current string -- case when we
                take the current string and increase the total number
        3.4.2 Otherwise, put 0 to the matrix.
     3.5 Return dp[length][m][n] as the result of the function.
 */

class DPSolution {

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] str = parseString(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= str[0] && k >= str[1]) {
                        dp[i][j][k] = Math.max(dp[i - 1][j - str[0]][k - str[1]] + 1, dp[i - 1][j][k]);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[length][m][n];
    }

    private int[] parseString(String str) {
        int[] occur = new int[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                occur[0]++;
            } else if (str.charAt(i) == '1') {
                occur[1]++;
            }
        }

        return occur;
    }
}
