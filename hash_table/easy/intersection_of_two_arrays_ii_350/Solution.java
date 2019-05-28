package hash_table.easy.intersection_of_two_arrays_ii_350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 350. Intersection of Two Arrays II
 https://leetcode.com/problems/intersection-of-two-arrays-ii/

 Given two arrays, write a function to compute their intersection.

 Example 1:
 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]

 Example 2:
 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.

 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[nums2.length];
        int pointer = 0;

        for (int key2: nums2) {
            Integer val1 = map1.get(key2);
            if (val1 != null && val1 > 0) {
                result[pointer] = key2;
                pointer++;

                map1.put(key2, val1 - 1);
            }
        }

        return Arrays.copyOfRange(result, 0, pointer);
    }
}
