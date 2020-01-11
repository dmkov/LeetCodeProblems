package hash_table.medium.group_anagrams_49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 49. Group Anagrams
 https://leetcode.com/problems/group-anagrams/

 Given an array of strings, group anagrams together.

 Example:
 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]

 Note:
 All inputs will be in lowercase.
 The order of your output does not matter.

 --------

 1. Complexity
     1.1 Time Complexity is O(n*k) where n is number of strings and k is the number of characters
     1.2 Space Complexity is O(n*k)
 2. Approach
     2.1 The solution is based on the idea of hash map. Calculate number of characters in every word and then compare
        hash maps to find anagrams.
 2.2 Implementation
    2.2.1 Check if input array is not empty.
    2.2.2 Iterate every string and get a hash map with characters and numbers of occurrences for them. Store hash maps in
        another map where the value is the list of matched strings
    2.2.3 Iterate Map.Entries to collect all linked lists for every anagram group.
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        Map<Map<Character, Integer>, List<String>> map = new HashMap<>();
        for (String str: strs) {
            Map<Character, Integer> strMap = getStrMap(str);
            List<String> list = map.getOrDefault(strMap, new ArrayList<String>());
            list.add(str);
            map.put(strMap, list);
        }

        for (Map.Entry entry: map.entrySet()) {
            result.add((List<String>) entry.getValue());
        }
        return result;
    }

    private Map<Character, Integer> getStrMap(String str) {
        Map<Character, Integer> result = new HashMap<>();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            Character ch = str.charAt(i);
            Integer num = result.getOrDefault(ch, 0);
            result.put(ch, ++num);
        }

        return result;
    }
}
