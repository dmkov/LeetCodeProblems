package string.medium.longest_substring_without_repeating_characters_3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 3. Longest Substring Without Repeating Characters
 https://leetcode.com/problems/longest-substring-without-repeating-characters/

 Given a string, find the length of the longest substring without repeating characters.

 Example 1:
 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.

 Example 2:
 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.

 Example 3:
 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.

 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 ---

 1. Complexity
    1.1 Time Complexity is O(2n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The solution is based on the sliding window approach.
    2.2 Implementation
        2.2.1 Check if input string is valid, otherwise return 0.
        2.2.2 Define two pointers i and j and HashSet with characters.
        2.2.2 Iterating through the string check if current char exists in the set.
             2.2.2.1 If it does, calculate distance from i to j and compare it with the previous max value. Remove
                element from set and increment j for the left bound.
             2.2.2.2 If it does not, add new element to the set and increment i for the right bound.
        2.2.3 Do not forget to get the last 'i - j' difference after loop ending and compare it with previous values.
 */

class SlidingWindowSolution {

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (set.contains(ch)) {
                result = Math.max(result, i - j);
                set.remove(s.charAt(j));
                j++;
            } else {
                set.add(ch);
                i++;
            }
        }

        return Math.max(result, i - j);
    }

}
