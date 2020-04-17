package string.hard.minimum_window_substring_76;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 76. Minimum Window Substring
 https://leetcode.com/problems/minimum-window-substring/

 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 Example:
 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"
 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 --------

 1. Complexity
    1.1 Time Complexity is O(2*S.length())
    1.2 Space Complexity is O(S.length())
 2. Approach
    2.1 The idea is to use two pointers to find matched window. When window matches - move the left pointer to reduce
        the window, otherwise - move right pointer to find the window.
    2.2 The tricky part is in the way to track matched window - use map with remaining characters and numbers. When the map is
        empty, we found the window.
    2.3 After moving left pointer, restore the map but use another map to keep restored elements.
 */

class SlidingWindowSolution {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> restore = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (char ch : t.toCharArray()) {
            set.add(ch);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (right < s.length() || (right != left && map.size() == 0)) {

            if (map.size() == 0) {
                char ch = s.charAt(left);
                if (set.contains(ch)) {
                    if (restore.containsKey(ch)) {
                        decrementEntry(restore, ch);
                    } else {
                        map.put(s.charAt(left), 1);
                    }
                }
                left++;
            } else {
                char ch = s.charAt(right);
                if (set.contains(ch)) {
                    Integer count = map.get(ch);
                    if (count != null) {
                        decrementEntry(map, ch);
                    } else {
                        restore.put(ch, restore.getOrDefault(ch, 0) + 1);
                    }
                }
                right++;
            }
            if (map.size() == 0 && right - left < min) {
                // valid state
                start = left;
                end = right;
                min = right - left;
            }
        }

        if (start == end) {
            return "";
        } else {
            return s.substring(start, end);
        }
    }

    private void decrementEntry(Map<Character, Integer> map, Character ch) {
        Integer count = map.get(ch);
        if (count != null) {
            if (count > 1) {
                map.put(ch, count - 1);
            } else {
                map.remove(ch);
            }
        }
    }

}

/**

 1. Can it be duplicates in T? yes
 2. Is it ASCII string?
 3. What is the possible length of both strings?
 4. What is the allowed list of characters?
 5. The order of how string appear does not matter?
 6. If there are two intervals which one should be returned?

 AKBCAB , ABC -> "AKBC", "CAB"
 AAAA, A -> A
 ABC, ""

 Iterate string with two pointers:
     - if substring match T -> move left ,
        otherwise -> move right
     - to define match:
        map<Char, Occurences> && set<Char>

 */
