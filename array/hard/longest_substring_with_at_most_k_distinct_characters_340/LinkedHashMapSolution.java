package array.hard.longest_substring_with_at_most_k_distinct_characters_340;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 340. Longest Substring with At Most K Distinct Characters
 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

 Given a string, find the length of the longest substring T that contains at most k distinct characters.

 Example 1:
 Input: s = "eceba", k = 2
 Output: 3
 Explanation: T is "ece" which its length is 3.

 Example 2:
 Input: s = "aa", k = 1
 Output: 2
 Explanation: T is "aa" which its length is 2.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use linked hashmap to track the first character to remove if number of allowed characters is overflowed
    2.2 With every new adding, instead of increasing counter, remove element and add it again with the new index
 */

class LinkedHashMapSolution {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        int left = 0, right = 0;
        int max = 0;
        Map<Character, Integer> map = new LinkedHashMap<>();
        while (right < s.length()) {
            char charRight = s.charAt(right);
            if (map.size() < k || (map.size() == k && map.containsKey(charRight))) {
                map.remove(charRight);
                map.put(charRight, right);
                right++;
                max = Math.max(max, right - left);
            } else {
                Character first = map.keySet().iterator().next();
                left = map.get(first) + 1;
                map.remove(first);
            }
        }

        return max;
    }
}



/**
     ababcdaabab , k=2 -> 5
           |
                |
     a:2
     b:2
     max=1

     abaa , k=3 -> 4
     "", k=2 -> 0
     abc, k<=0 -> 0

     1. There can be duplicated characters in the substring? yes
     2. What should I return, the substring or its length?
     3. Is it ASCII string? should upper/smaller cases be considered as a different chars?
     4. If number of unique characters are smaller than K in whole string, could it be an answer?


     1. Brute force - for every char count how many upcoming characters are unique and get the max substring
        O(n^2)
     2. Sliding window
         1. Set left and right pointers to the first char
         2. While right pointer < str.length()
             add right element to the map
             if map.size() <= k or element was already added
                 move right pointer
                 get the interval length, count the max
             else
                 remove left char from map
                 move left pointer
 */
