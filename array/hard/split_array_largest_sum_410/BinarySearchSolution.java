package array.hard.split_array_largest_sum_410;

/**
 410. Split Array Largest Sum
 https://leetcode.com/problems/split-array-largest-sum/

 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:

 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:
 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.

 --------

 1. Complexity
     1.1 Time Complexity is O(n * log(sum of all elements))
     1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to define max and min possible answers (the max element and sum of all elements)
    2.2 Then we can do a binary search in the range and try to split arrays using the given subsum
 */

class BinarySearchSolution {

    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m < 1) {
            return 0;
        }
        if (m > nums.length) {
            m = nums.length;
        }

        int min = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            min = Math.max(min, num);
        }

        int left = min; // 10
        int right = sum; // 14
        int res = min;
        while (left <= right) {
            int mid = left + (right - left) / 2; //12

            int counter = 1; // 4
            int subSum = 0;
            for (int num : nums) {
                if (subSum + num > mid) {
                    subSum = num;
                    counter++;
                } else {
                    subSum += num;
                }
            }

            if (counter <= m) {
                res = mid; // 15
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return res;
    }
}


/**
        0 1  2  3
     -  0 0  0  0
     7  0 7  0  0
     2  0 9  7  +
     5  0 14 7  7
     10 0 24 14 10
     8  0 36 18 14



    // [7,2,5,10,8]
    // 7,2,5,10   8
    // 7,2,5   10,8
    // 7,2   5,10,8
    // 7   2,5,10,8



     - Start with first position, return backtracking result
     -   for each number starting the pos and ending length-m:
         - add it to local sum
         - get the max of local sum and (backtracking result for the next pos and m-1) if m>1
         - return the min of all possible maxs

     O(n^m) without memoization

     pos_m as a cache key
     O(length * m)

 */
