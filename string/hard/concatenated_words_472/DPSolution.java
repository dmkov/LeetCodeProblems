package string.hard.concatenated_words_472;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 472. Concatenated Words
 https://leetcode.com/problems/concatenated-words/

 Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

 Example:
 Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

 Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

 Note:
 The number of elements of the given array will not exceed 10,000
 The length sum of elements in the given array will not exceed 600,000.
 All the input string will only include lower case letters.
 The returned elements order does not matter.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to look for concatenation for every word in the list
    2.2 Create a dp array where each index shows if we can get to it by concating words from the list
        Technically it means that we have substring from the last true dp presented in the list (use set for the quick lookup)
 */

class DPSolution {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> res = new HashSet<>();
        if (words == null || words.length == 0) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (check(word, word, set)) {
                res.add(word);
            }
        }

        return new ArrayList<>(res);
    }

    private boolean check(String word, String origin, Set<String> words) {
        if (word.length() == 0) {
            return false;
        }

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(word.substring(j, i)) && !word.equals(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[word.length()];
    }

}
