package hash_table.medium.before_and_after_puzzle_1181;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 1181. Before and After Puzzle
 https://leetcode.com/problems/before-and-after-puzzle

 Given a list of phrases, generate a list of Before and After puzzles.

 A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.

 Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase is the same as the first word of the second phrase.

 Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases matters, we want to consider both orders.

 You should return a list of distinct strings sorted lexicographically.

 Example 1:
 Input: phrases = ["writing code","code rocks"]
 Output: ["writing code rocks"]

 Example 2:
 Input: phrases = ["mission statement",
     "a quick bite to eat",
     "a chip off the old block",
     "chocolate bar",
     "mission impossible",
     "a man on a mission",
     "block party",
     "eat my words",
     "bar of soap"]
 Output: ["a chip off the old block party",
     "a man on a mission impossible",
     "a man on a mission statement",
     "a quick bite to eat my words",
     "chocolate bar of soap"]

 Example 3:
 Input: phrases = ["a","b","a"]
 Output: ["a"]

 Constraints:
 1 <= phrases.length <= 100
 1 <= phrases[i].length <= 100

 --------

 1. Complexity
    1.1 Time Complexity is O(n logn) because of the sorting at the end
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to create a hash map of first word in the phrase and indexes of these elements. Then iterate all
        elements and get indexes where the last word can be matched. Use set to exclude duplicates and sort list at the end.
 3 Implementation
    3.1 Check if input array is valid
    3.2 Iterate all phrases in the array:
        3.2.1 Split phrase into array of words by " ".
        3.2.2 Get the list of indexes for the first word (if it exists) and add the current index there
        3.2.3 In a separate array, store the rest of the phrase, without the first word.
    3.3 Iterate all phrases one more time:
        3.3.1 Split phrase into array of words by " ".
        3.3.2 Get list of indexes for the last word.
        3.3.3 If current index does not equal second index in the list - composite the current word with stored ending
            for the second index. Add result to the set.
    3.4 Construct result list from the set. Sort it to get lexicographically order.
 */

class HashMapSolution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        List<String> result = new LinkedList<>();
        if (phrases == null || phrases.length == 0) {
            return result;
        }

        Map<String, List<Integer>> map = new HashMap<>();
        String[] ends = new String[phrases.length];
        for (int i = 0; i < phrases.length; i++) {
            String[] arr = phrases[i].split(" ");
            List<Integer> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(i);
            map.put(arr[0], list);

            ends[i] = phrases[i].substring(arr[0].length());
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < phrases.length; i++) {
            String[] arr = phrases[i].split(" ");
            String last = arr[arr.length - 1];
            List<Integer> list = map.get(last);
            if (list != null) {
                for (Integer indx : list) {
                    if (!indx.equals(i)) {
                        set.add(phrases[i] + ends[indx]);
                    }
                }
            }
        }

        result = new LinkedList<>(set);
        Collections.sort(result);
        return result;
    }
}
