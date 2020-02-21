package dynamic.medium.partition_to_k_equal_sum_subsets_698;

import java.util.Arrays;
import java.util.HashMap;

/**
 698. Partition to K Equal Sum Subsets
 https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

 Note:
 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.

 --------

 1. Complexity
     1.1 Time Complexity is O(k^(N-k)*k!) ~ O(k!) where k is given and N is length of the nums
     1.2 Space Complexity is O(N)
 2. Approach
     2.1 For every cell consider options to put all available elements. Repeat it recursively until you have numbers
        and there are remaining cells to populate. After the active cell is populated - reset the local sum and reduce the size
        of remaining k.
 3 Implementation
     3.1 Check if input array is valid. Calculate total sum and size = sum / k. The partitioning is possible if max number
        is smaller or equal to size and if sum % k = 0.
     3.2 To speed up the work sort the array and always start iteration from the last index (bigger number).
     3.3 Create a boolean array to store indexes of used numbers.
     3.3 Start recursive function and pass there required number of buckets (k) with the sum for the active bucket so far (0):
        3.3.1 In the recursive method check if sum for the active bucket equals required size, we did the job for the bucket -
            so lets move to the next one. Decrement k and set sum for the bucket to 0.
        3.3.2 Check if k equals 0 - we successfully populated all numbers and can return true for the method.
        3.3.3 Iterate all available numbers in the array. If boolean list shows that number is not visited and
            active sum with this number does not overflow the required size - set number as visited and call recursive method
            with updated size. If it returns true - break the execution and return true for the current method, otherwise -
            restore the visited element and try another one in the loop.
        3.2.4 If none of elements do not work - return false at the end.
 */

class BacktrackingSolution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return false;
        }

        int sum = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int size = sum / k;
        if (max > size || sum % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];

        return check(nums, k, 0, size, used);
    }

    private boolean check(int[] nums, int k, int sum, int size, boolean[] used) {
        if (sum == size) {
            k = k - 1;
            sum = 0;
        }
        if (k == 0) {
            return true;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (!used[i] && sum + nums[i] <= size) {
                used[i] = true;
                if (check(nums, k, sum + nums[i], size, used)) {
                    return true;
                }
                used[i] = false;
            }
        }

        return false;
    }
}
