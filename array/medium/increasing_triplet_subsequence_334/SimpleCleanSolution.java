package array.medium.increasing_triplet_subsequence_334;

/**
 334. Increasing Triplet Subsequence
 https://leetcode.com/problems/increasing-triplet-subsequence/

 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

 Formally the function should:
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Example 1:
 Input: [1,2,3,4,5]
 Output: true

 Example 2:
 Input: [5,4,3,2,1]
 Output: false

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Start with two largest values, as soon as we find a number bigger than both,
            while both have been updated - return true.
 3 Implementation
    3.1 Validate input array. Set first and second variables to Integer.MAX_VALUE.
    3.2 Iterate over the array:
        3.1 If number is less than the first var - update the first
        3.2 Else if it is less than second - update second
        3.3 Else - return true (means both were updated and third number is bigger than second)
 */

class SimpleCleanSolution {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else if (num > second) {
                return true;
            }
        }

        return false;

    }

}
