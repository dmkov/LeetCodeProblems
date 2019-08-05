package array.easy.majority_element_169;

import java.util.HashMap;
import java.util.Map;

/**
 169. Majority Element
 https://leetcode.com/problems/majority-element/

 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Example 1:
 Input: [3,2,3]
 Output: 3

 Example 2:
 Input: [2,2,1,1,1,2,2]
 Output: 2

 --------

 1. Complexity
    1.1 Time Complexity is O(n) for hashmap and Boyer-Moore voting algorithm and O(nlog) for sorting
    1.2 Space Complexity is O(n) for hashmap and O(1) for sorting and Boyer-Moore voting algorithm
 2. Approach
    2.1 The solution is based on the idea to count all number appearances, store result in HashMap and then return
        the max value
    2.2 Another approach is based on sorting. If majority element is guaranteed, it will be on n/2 or n/2+1 position
    2.3 The last one is based on Boyer-Moore Voting Algorithm (more efficient) -
        http://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html
 2.2 Implementation
    2.2.1 Iterate through the array, add number and occurrences to the hash map
    2.2.2 Iterate hashmap and check the max number. It will be an answer.
 */

public class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = nums[0];
        int target = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > target) {
                result = num;
                break;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return result;
    }

//    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length/2];
//    }
//
//    public int majorityElement(int[] nums) {
//        int count = 0;
//        Integer candidate = null;
//
//        for (int num : nums) {
//            if (count == 0) {
//                candidate = num;
//            }
//            count += (num == candidate) ? 1 : -1;
//        }
//
//        return candidate;
//    }


}
