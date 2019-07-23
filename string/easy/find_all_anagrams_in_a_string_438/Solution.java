package string.easy.find_all_anagrams_in_a_string_438;

import java.util.ArrayList;
import java.util.List;

/**
 438. Find All Anagrams in a String
 https://leetcode.com/problems/find-all-anagrams-in-a-string/

 Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:
 Input:
 s: "cbaebabacd" p: "abc"
 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".

 Example 2:
 Input:
 s: "abab" p: "ab"
 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n) - where n is the length of s
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on sliding window approach
    2.2 Implementation
        2.2.1 Create a map (array) of all required characters.
        2.2.2 Move right pointer everytime, if the character exists in the map, decrease the counter.
        2.2.3 Decrease number associated with the character in the map. If there is no character, add it and go negative
        2.2.4 When the counter is down to 0, means we found the right anagram. Then add window's left to result list
        2.2.5 If we window's size equals to p, then we have to move left (narrow the window) to find the new match window
            Reset counter and increase the number associated in the map with character.
 */

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        int left = 0, right = 0;
        int counter = p.length();
        int[] map = new int[255];
        for (char ch: p.toCharArray()) {
            map[ch]++;
        }
        while (right < s.length()) {
            if (map[s.charAt(right)] >= 1) {
                counter--;
            }
            map[s.charAt(right)]--;

            if (counter == 0) {
                result.add(left);
            }
            if (right - left == p.length() - 1) {
                if (map[s.charAt(left)] >= 0) {
                    counter++;
                }
                map[s.charAt(left)]++;
                left++;
            }
            right++;
        }

        return result;
    }
}
