package greedy.medium.reorganize_string_767;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 767. Reorganize String
 https://leetcode.com/problems/reorganize-string/

 Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

 If possible, output any possible result.  If not possible, return the empty string.

 Example 1:
 Input: S = "aab"
 Output: "aba"

 Example 2:
 Input: S = "aaab"
 Output: ""

 Note:
 S will consist of lowercase letters and have length in range [1, 500].

 --------

 1. Complexity
    1.1 Time Complexity is O(nlog), because of using heap
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to split string and sort characters in the Max Heap to get every time element with the max number of occurrences.
 2.2 Implementation
    2.2.1 Check if given string is valid.
    2.2.2 Create a hash map to count number of every character in the string. Populate hash map from given string.
    2.2.3 Create a PriorityQueue weth descending order and populate it with values from hash map. If any of characters has length
        more than (N+1)/2 it is not possible to reorganize the string, return "".
    2.2.4 While queue is not empty poll from it two elements. Check if the first is not the element appended to the string
        previously (if it is, just skip it - that is why we need two polling). Append elements to the string and decrese their
        numbers in the hash map. If number of occurrences is larger than 0, push the character to the queue back at the end.
 */

public class PriorityQueueSolution {
    public String reorganizeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int length = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Character ch = s.charAt(i);
            Integer num = map.getOrDefault(ch, 0);
            num++;
            map.put(ch, num);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) ->
                map.get(a).equals(map.get(b)) ? a - b : map.get(b) - map.get(a));
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (map.get(entry.getKey()) >= Math.ceil(length/2.0) + 1) {
                return "";
            }
            pq.add(entry.getKey());
        }

        Character prev = ' ';
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            Character first = pq.poll();
            if (first != prev) {
                sb.append(first);
                prev = first;
                map.put(first, map.get(first) - 1);
            }
            if (pq.size() > 0) {
                Character second = pq.poll();
                sb.append(second);
                prev = second;
                map.put(second, map.get(second) - 1);
                if (map.get(second) > 0) {
                    pq.add(second);
                }
            }
            if (map.get(first) > 0) {
                pq.add(first);
            }
        }

        return sb.toString();
    }
}
