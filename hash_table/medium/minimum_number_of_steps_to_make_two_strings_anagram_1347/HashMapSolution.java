package hash_table.medium.minimum_number_of_steps_to_make_two_strings_anagram_1347;

import java.util.HashMap;
import java.util.Map;

/**
 1347. Minimum Number of Steps to Make Two Strings Anagram
 https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/

 Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.

 Return the minimum number of steps to make t an anagram of s.

 An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 Example 1:
 Input: s = "bab", t = "aba"
 Output: 1
 Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

 Example 2:
 Input: s = "leetcode", t = "practice"
 Output: 5
 Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.

 Example 3:
 Input: s = "anagram", t = "mangaar"
 Output: 0
 Explanation: "anagram" and "mangaar" are anagrams.

 Example 4:
 Input: s = "xxyyzz", t = "xxyyzz"
 Output: 0

 Example 5:
 Input: s = "friend", t = "family"
 Output: 4

 Constraints:
 1 <= s.length <= 50000
 s.length == t.length
 s and t contain lower-case English letters only.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Simply count the number of characters in both strings. Then if target is bigger than source, sum up the difference
        (this check is required to do not count differences twice).
 2.2 Implementation
    2.2.1 Check if given strings are equal.
    2.2.2 Count number of characters in both strings
    2.2.3 For every character in target, check the difference with source and add it to result if the diff > 0
 */

class HashMapSolution {
    public int minSteps(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i< s.length(); i++) {
            int num = sMap.getOrDefault(s.charAt(i), 0);
            sMap.put(s.charAt(i), num + 1);
        }
        for (int i = 0; i< t.length(); i++) {
            int num = tMap.getOrDefault(t.charAt(i), 0);
            tMap.put(t.charAt(i), num + 1);
        }

        int count = 0;
        for (Map.Entry<Character, Integer> sEntry : sMap.entrySet()) {
            int sNum = sEntry.getValue();
            int tNum = tMap.getOrDefault(sEntry.getKey(), 0);

            count = count + ((sNum > tNum) ? (sNum - tNum) : 0);
        }

        return count;
    }
}
