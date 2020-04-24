package array.medium.shortest_word_distance_ii_244;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 244. Shortest Word Distance II
 https://leetcode.com/problems/shortest-word-distance-ii/

 Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

 Example:
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Input: word1 = “coding”, word2 = “practice”
 Output: 3
 Input: word1 = "makes", word2 = "coding"
 Output: 1
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 --------

 1. Complexity
    1.1 Time Complexity is O(n + m)
    1.2 Space Complexity is O(n + m)
 2. Approach
    2.1 Pre-process all strings with their positions into a hash map.
    2.2 Having two lists we need to find the smallest diff between possible numbers. Use two pointers and increment
        the smallest one every time, since we do not need to check smaller values with next bigger numbers.
 */

public class Solution {
    Map<String, List<Integer>> d = new HashMap<>();

    public Solution(String[] words) {
        for (int i = 0; i < words.length; i++) {
            List<Integer> indexes = d.getOrDefault(words[i], new ArrayList<>());
            indexes.add(i);
            d.put(words[i], indexes);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = d.get(word1);
        List<Integer> list2 = d.get(word2);

        int min = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
            if (min == 1) {
                return min;
            }
        }

        return min;
    }

    /**

     1. What is the shortest distance? Should it be distance in indexes?
     2. Can I have duplicates in the list? And in this case I should return the closets, right?
     3. What is the max size of the array?
     4. What should I return if array is empty or words are not in the list?


         "a", "b", "a" ,"c" -> (a,c) - 1, (b,c) - 2
         a c b a m c -> (a, c) - 1

         0, 3
         |
         1, 5
         |

     1. Store all indexes into the list in hashmap for the string.
     - Using two pointers, select two first items. Move the smallest pointer and check the min difference.
     O(m+n)

         1 5 7 9
         6 10 20

     - Select a smaller list, do a binary search in the remaining one
     O(m*logn) ---- not so efficient

     */

}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
