package array.medium.subarray_sum_equals_k_560;

import java.util.HashMap;
import java.util.Map;

/**
 560. Subarray Sum Equals K
 https://leetcode.com/problems/subarray-sum-equals-k/

 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2

 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea behind this approach: If the cumulative sum (represented by sum[i] for sum up to i index)
        up to two indices is the same, the sum of the elements lying in between those indices is zero.
        Extending the same thought further, if the cumulative sum upto two indices, say i and j is at a difference
        of k i.e. if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.
 2.2 Implementation
    2.2.1 Check if input array is valid
    2.2.2 Create a hash map, put the first (0, 1) element (0 is a sum and 1 is occurrence)
    2.2.3 Iterate all elements. Calculate cumulative sum for every element and check if (sum - k) exists in the hah map
        (means it was added previously). If it exists, add value from hash map to the result number.
 */

public class CumulativeSumHashSolution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.get(sum - k) != null) {
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}
