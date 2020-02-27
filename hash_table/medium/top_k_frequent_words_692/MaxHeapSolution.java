package hash_table.medium.top_k_frequent_words_692;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 692. Top K Frequent Words
 https://leetcode.com/problems/top-k-frequent-words/

 Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.

 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.

 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.

 --------

 1. Complexity
    1.1 Time Complexity is O(n logk) where is n - number of words and k is the depth of max heap
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Calculate number of occurrences for every element using hash map, then use PriorityQueue to poll
        required number os elements to the result.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty. Calculate number of occurrences for every element
        iterating the given array and incrementing values in the hash map.
    2.2.2 Create PriorityQueue with reverse comparator. If elements have the same occurrences - compare strings themselves.
    2.2.3 While k is not 0, poll next item from PriorityQueue and populate it to the result. Decrement k.
 */

class MaxHeapSolution {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0) {
            return new LinkedList<>(); // or return null
        }

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
            if (map.get(a).equals(map.get(b))) {
                return a.compareTo(b);
            }
            return map.get(b) - map.get(a);
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            heap.add(entry.getKey());
        }

        List<String> res = new LinkedList<>();
        while (k > 0) {
            res.add(heap.poll());
            k--;
        }

        return res;
    }
}
