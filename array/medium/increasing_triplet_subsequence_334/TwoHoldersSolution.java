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
    2.1 It is not the most efficient - start the next solution also.
    2.2 Keep two variables to track two smaller values. If you find a number smaller than the first one - move
        current pair to the backup and update the first var. Then continue checking with backup first.
 3 Implementation
    3.1 Validate input array. Set two arrays for the primary and backup numbers.
    3.2 Iterate over the array:
        3.2.1 Check backup, if it is not empty:
            3.2.1.1 If num is bigger than second n in the backup -> return true
            3.2.1.1 If num is between two numbers - update the second
        3.2.2 Check the primary pair:
            3.2.2.1 If second is not null:
                3.2.2.1.1 Num is bigger than it - return true.
                3.2.2.1.2 Num is between first and second - update second.
                3.2.2.1.3 Num is smaller than the first element - make backup and update first element
            3.2.2.2 If second is null - update it or first element depending on the num value
 */

class TwoHoldersSolution {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        Integer[] primary = new Integer[2];
        Integer[] back = new Integer[2];

        for (int n : nums) {
            if (back[1] != null) {
                if (n > back[1]) {
                    return true;
                } else if (n > back[0] && n < back[1]) {
                    back[1] = n;
                }
            }

            if (primary[1] == null) {
                if (primary[0] != null && n > primary[0]) {
                    primary[1] = n;
                } else {
                    primary[0] = n;
                }
            } else {
                if (n > primary[1]) {
                    return true;
                } else if (n > primary[0] && n < primary[1]) {
                    primary[1] = n;
                } else {
                    back[0] = primary[0];
                    back[1] = primary[1];
                    primary[0] = n;
                    primary[1] = null;
                }
            }

        }

        return false;

    }

}
