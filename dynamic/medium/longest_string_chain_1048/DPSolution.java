package dynamic.medium.longest_string_chain_1048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     1.1 Time Complexity is O(?)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Work with two hash maps - one for counter of steps for the every element and another one for all elements with
        the specific length of the string. For every level - check only previous length and if item can be constructed from there,
        update the counter for the item as previous counter + 1.
 3 Implementation
     3.1 Check if input array of string is valid.
     3.2 Create two hash maps for string lengths and string counter. Iterate over all strings and put it in the list
        on required level and also put 1 as the default counter for it.
     3.3 Iterate from 2 to the max level of string length. On every level:
        3.3.1 Check if hash map has elements on this and previous levels.
        3.3.2 For every element on this level, iterate all elements on previous one and check if item can be constructed
            from smaller value. If yes, update the counter for the current item as previous counter + 1.
     3.4 Get the max counter from stored hash map as a result of the method.
 */

class DPSolution {

    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        Map<String, Integer> counter = new HashMap<>();
        Map<Integer, List<String>> levels = new HashMap<>();

        int l = words.length;
        int maxLevel = 0;
        for (int i = 0; i < l; i++) {
            counter.put(words[i], 1);

            List<String> list = levels.getOrDefault(words[i].length(), new ArrayList<>());
            list.add(words[i]);
            levels.put(words[i].length(), list);
            maxLevel = Math.max(maxLevel, words[i].length());
        }

        int max = 1;
        for (int i = 2; i <= maxLevel; i++) {
            if (levels.containsKey(i) && levels.containsKey(i - 1)) {
                for (String str2 : levels.get(i)) {
                    for (String str1 : levels.get(i - 1)) {
                        if (isNext(str1, str2)) {
                            int current = counter.get(str1) + 1;
                            counter.put(str2, current);
                            max = Math.max(max, current);
                        }
                    }
                }
            }
        }

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

        return true;
    }
}
