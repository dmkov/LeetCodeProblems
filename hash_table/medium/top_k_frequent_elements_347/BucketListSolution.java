package hash_table.medium.top_k_frequent_elements_347;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Calculate number of occurrences for every element using hash map, then use separate bucket array
        to get all elements by occurrences.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty. Calculate number of occurrences for every element
        iterating the given array and incrementing values in the hash map.
    2.2.2 Create a bucket array where indexes are numbers of element occurrences and values is the list of
        linked elements. Iterate through the map and populate new bucket array.
    2.2.3 At the end of the bucket array we already have the most frequent elements. Iterate from end to
        the first element and populate result list with elements until k is reached.
 */

class BucketListSolution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        Map<Integer, Integer> map = getOccurrences(nums);
        if (map.size() < k) {
            return result;
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }

        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null ) {
                result.addAll(bucket[i]);
            }
        }

        return result;
    }

    private Map<Integer, Integer> getOccurrences(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            Integer occur = map.getOrDefault(num, 0);
            map.put(num, ++occur);
        }
        return map;
    }
}
