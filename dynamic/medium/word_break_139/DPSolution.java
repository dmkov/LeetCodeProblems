package dynamic.medium.word_break_139;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 139. Word Break
 https://leetcode.com/problems/word-break/

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:
 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.

 Example 1:
 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".

 Example 2:
 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.

 Example 3:
 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2), because of two loops to fill dp array n^2
    1.2 Space Complexity is O(n), n is the length of the string
 2. Approach
    2.1 Explanation - https://www.youtube.com/watch?v=hLQYQ4zj0qg (the second part).
        The idea is to split problem with checking on possible cases for the previous character in the string
 2.2 Implementation
    2.2.1 Check if both given string and dictionary are valid.
    2.2.2 Create a HashSet from word list. Create a boolean dp[length + 1] array,
        set dp[0] to true since empty string is valid.
    2.2.3 Iterate from 1 to length to fill dp[1..length] array. The current step is valid if any of previous
        dp[j] is valid and s.substring(j, i) contains in the set (the remaining part of the string).
    2.2.4 To check this we need another internal loop from 0 to i. If condition is true, update dp array and break the loop.

 */

public class DPSolution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(wordDict);
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[length];
    }
}
