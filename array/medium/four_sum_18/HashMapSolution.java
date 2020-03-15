package array.medium.four_sum_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 18. 4Sum
 https://leetcode.com/problems/4sum/

 Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note:
 The solution set must not contain duplicate quadruplets.

 Example:
 Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
     [-1,  0, 0, 1],
     [-2, -1, 1, 2],
     [-2,  0, 0, 2]
 ]

 --------

 1. Complexity
     1.1 Time Complexity is O(n^3)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 We should find a pair of 4 numbers. Instead of 4 loops, lets use the hash map with lists of indexes for each element
        to quickly find the last pair. Additional sorting will help to avoid duplicates.
 3 Implementation
     3.1 Check if input array is valid. Sort it.
     3.2 Create a hash map with indexes as a value and numbers as a key.
     3.3 Iterate i pointer from 0 .. length-3:
        3.3.1 Iterate j pointer from i + 1 .. length-2:
            3.3.1.1 Iterate k pointer from j + 1 .. length-1:
                3.3.1.1.1 Get sub sum of i, j and k-th elements.
                3.3.1.1.2 Check if there is remaining sum in the hash map.
                3.3.1.1.3 If it is, iterate all indexes and add all options greater than k. Use hash set to avoid duplicates.
     3.4 Return a list of results.
 */

class HashMapSolution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>(res);
        }

        int l = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new LinkedList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        for (int i = 0; i < l - 3; i++) {
            for (int j = i + 1; j < l - 2; j++) {
                for (int k = j + 1; k < l - 1; k++) {
                    int sub = nums[i] + nums[j] + nums[k];
                    if (map.containsKey(target - sub)) {
                        List<Integer> list = map.get(target - sub);
                        for (int m : list) {
                            if (m > k) {
                                List<Integer> c = Arrays.asList(new Integer[]{nums[i], nums[j], nums[k], nums[m]});
                                Collections.sort(c);
                                res.add(c);
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }
}
