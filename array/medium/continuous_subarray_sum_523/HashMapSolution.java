package array.medium.continuous_subarray_sum_523;

import java.util.HashMap;
import java.util.Map;

/**
 523. Continuous Subarray Sum
 https://leetcode.com/problems/continuous-subarray-sum/

 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 For every number get cumulative (sum % k). At every position check if cumulative sum is in hash map.
        In this case we have a cycle equal to k*x.
 */

class HashMapSolution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (k != 0) {
                sum = sum % k;
            }

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }

    /**
     1. Naive approach
         For every number check all ongoing subsets and check their sum % k
         O(n^2)


         23, 2, 6, 7, 4

         5   2  0  1  4

         1 | 2
         1 | 2 | 0
         2 | 3 | 1 | 1
         6

         11 | 6 | 4

     2. For every number get cumulative (sum % k). At every position check if cumulative sum is in hash set.
     In this case we have a cycle equal to k*x

        O(n)

     */
}
