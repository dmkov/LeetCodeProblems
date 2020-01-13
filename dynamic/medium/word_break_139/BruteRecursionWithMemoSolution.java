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
    1.1 Time Complexity is O(n^2), size of recursion tree can go up to n^2
    1.2 Space Complexity is O(n), n is the depth of the recursion
 2. Approach
    2.1 For every character in the string we check if it forms the word with previous ones and if it does,
        then recursively call the method again for the remaining string part. The trick does memoization and checking that
        we do not pass the max number of characters in the word list.
 2.2 Implementation
    2.2.1 Check if both given string and dictionary are valid.
    2.2.2 Create a HashSet from word list. For every word check if it larger than the maximum length checked before.
    2.2.3 Call method check() with string, start point, maximum found length, set and memoization array
    2.2.4 The result of check() method will be true when we call it for empty string (start point is equal to length),
        otherwise check memoization object if this substring was already viewed.
    2.2.5 If substring was not viewed before, check all possible subsets from the start point and call for them check()
        method recursively again.
 */

public class BruteRecursionWithMemoSolution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int max = 0;
        Set<String> set = new HashSet<>();
        for (String str: wordDict) {
            max = Math.max(max, str.length());
            set.add(str);
        }
        boolean[] checked = new boolean[s.length()];

        return check(s, 0, max, set, checked);
    }

    private boolean check(String s, int start, int max, Set<String> set, boolean[] checked) {
        int length = s.length();
        if (start == length) {
            return true;
        }
        if (checked[start]) {
            return false;
        }

        String sub = "";
        for (int i = start; (i < length && i - start <= max); i++) {
            sub += s.charAt(i);
            if (set.contains(sub) && check(s, i + 1, max, set, checked)) {
                return true;
            }
        }

        checked[start] = true;
        return false;
    }
}
