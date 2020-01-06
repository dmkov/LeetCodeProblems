package hash_table.medium.top_k_frequent_elements_347;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 347. Top K Frequent Elements
 https://leetcode.com/problems/top-k-frequent-elements/

 Given a non-empty array of integers, return the k most frequent elements.

 Example 1:
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]

 Example 2:
 Input: nums = [1], k = 1
 Output: [1]

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogm) where is n - number of elements and m is the depth of max heap
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Calculate number of occurrences for every element using hash map, then use PriorityQueue to poll
        required number os elements to the result.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty. Calculate number of occurrences for every element
        iterating the given array and incrementing values in the hash map.
    2.2.2 Create PriorityQueue with reverse comparator. Populate it with custom NumElement objects created
        from hash map numbers and occurrences.
    2.2.3 While k is not 0, poll next item from PriorityQueue and populate it to the result. Decrement k.
 */

class MaxHeapSolution {
    class NumElement {
        int number;
        int occurrence;

        public NumElement(int number, int occurrence) {
            this.number = number;
            this.occurrence = occurrence;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Map<Integer, Integer> map = getOccurrences(nums);
        if (map.size() < k) {
            return result;
        }

        PriorityQueue<NumElement> queue = new PriorityQueue<>(
                new Comparator<NumElement>() {
                    public int compare(NumElement a, NumElement b) {
                        return b.occurrence - a.occurrence;
                    }
                }
        );

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            queue.add(new NumElement(entry.getKey(), entry.getValue()));
        }

//        we can use entry object to store in the heap and do not create own class
//        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
//            queue.add(entry);
//        }

        while(k > 0) {
            NumElement el = queue.poll();
            result.add(el.number);
            k--;
        }

        return result;
    }

    private Map<Integer, Integer> getOccurrences(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            Integer occur = map.get(num);
            if (occur == null) {
                occur = 0;
            }
            map.put(num, ++occur);
        }
        return map;
    }
}
