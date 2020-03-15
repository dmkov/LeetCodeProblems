package array.medium.four_sum_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
     2.1 We should find a pair of 4 numbers. Instead of 4 loops, lets sort the initial array and use 2 pointers approach
        for the last two levels. We will move pointers until they cross and sorting will help to define which side
        we should move to. Also it helps to avoid duplicates.
 3 Implementation
     3.1 Check if input array is valid. Sort it.
     3.2 Iterate i pointer from 0 .. length-3:
        3.2.1 Iterate j pointer from i + 1 .. length-2:
            3.2.1.1 Set k pointer as j + 1
            3.2.1.2 Set m pointer as length-1
            3.2.1.3 While k less than m:
                3.2.1.3.1 If sum of all 4 numbers equals to the target, add them to the set to avoid duplicates
                3.2.1.3.2 If sum of all 4 numbers is less than target, move left pointer k to increase the sum
                3.2.1.3.3 If sum of all 4 numbers is greater than target, move right pointer m to reduce the sum
     3.3 Return a list of results.
 */

class TwoPointersSolution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>(res);
        }
        Arrays.sort(nums);

        int l = nums.length;
        for (int i = 0; i < l - 3; i++) {
            for (int j = i + 1; j < l - 2; j++) {
                int k = j + 1;
                int m = l - 1;
                while (k < m) {
                    int sum = nums[k] + nums[m] + nums[i] + nums[j];
                    if (sum == target) {
                        res.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k], nums[m]}));
                        k++;
                        m--;
                    } else if (sum > target) {
                        m--;
                    } else if (sum < target) {
                        k++;
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }
}
