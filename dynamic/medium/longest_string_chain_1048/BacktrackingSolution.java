package dynamic.medium.longest_string_chain_1048;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 1048. Longest String Chain
 https://leetcode.com/problems/longest-string-chain/

 Given a list of words, each word consists of English lowercase letters.

 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

 A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

 Return the longest possible length of a word chain with words chosen from the given list of words.

 Example 1:
 Input: ["a","b","ba","bca","bda","bdca"]
 Output: 4
 Explanation: one of the longest word chain is "a","ba","bda","bdca".

 Note:
 1 <= words.length <= 1000
 1 <= words[i].length <= 16
 words[i] only consists of English lowercase letters.

 --------

 1. Complexity
     1.1 Time Complexity is O(n! or 2^n ?)
     1.2 Space Complexity is O(n*n)
 2. Approach
     2.1 Slow solution. Check DP option for more advanced approach.
        For every element we iterate all non-visited items and check if they can be used as continuation for the current item.
        Then, for all options return the max path length on the current level.
 3 Implementation
     3.1 Check if input array of string is valid.
     2.3 Create 2D dp array and populate it by iterating all items twice: indexes i and j will show if j is continuation of i-th item.
        To calculate if one string is continuation of another - use a separate method where check every character in strings
        and return false if they differ more than once.
     3.2 Create memo hash map, visited array and start backtracking method for every element:
         3.2.1 If key of visited array and the current position is in the memo hash map - return the stored result.
         3.2.2 Otherwise, iterate all non-visited elements and start backtracking method for them. Get the max value from
            comparing all executions.
         3.2.3 Restore visited flag and return the max path length for the current step.
 */

class BacktrackingSolution {

    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        int l = words.length;
        boolean[][] dp = new boolean[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                dp[i][j] = isNext(words[i], words[j]);
            }
        }

        Map<String, Integer> memo = new HashMap<>();
        boolean[] visited = new boolean[l];
        int max = 0;
        for(int i = 0; i < l; i++) {
            max = Math.max(backtracking(dp, i, visited, memo), max);
        }

        return max;
    }

    private int backtracking(boolean[][] dp, int pos, boolean[] visited, Map<String, Integer> memo) {
        String key = pos + "_" + Arrays.toString(visited);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        visited[pos] = true;
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[pos][i] && !visited[i]) {
                max = Math.max(max, backtracking(dp, i, visited, memo) + 1);
            }
        }
        visited[pos] = false;

        memo.put(key, max);
        return max;
    }

    private boolean isNext(String f, String s) {
        if (f.equals(s) || f.length() + 1 != s.length()) {
            return false;
        }
        boolean skipped = false;
        int i = 0, j = 0;
        while (j < s.length()) {
            if (i == f.length() || f.charAt(i) != s.charAt(j)) {
                if (skipped) {
                    return false;
                }
                skipped = true;
                j++;
            } else {
                i++;
                j++;
            }
        }

        return (i == f.length() && j == s.length());
    }
}
