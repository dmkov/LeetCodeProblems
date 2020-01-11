package array.easy.two_sum_less_than_k_1099;

import java.util.Arrays;

/**
 1099. Two Sum Less Than K
 https://leetcode.com/problems/two-sum-less-than-k/

 Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

 Example 1:
 Input: A = [34,23,1,24,75,33,54,8], K = 60
 Output: 58
 Explanation:
 We can use 34 and 24 to sum 58 which is less than 60.

 Example 2:
 Input: A = [10,20,30], K = 15
 Output: -1
 Explanation:
 In this case it's not possible to get a pair sum less that 15.


 Note:
 1 <= A.length <= 100
 1 <= A[i] <= 1000
 1 <= K <= 2000

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogn) because of sorting and further binary search for every element
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 We can sort array and then find best option for every element using binary search. It will give us n * log n
 2.2 Implementation
    2.2.1 Check if array is valid and not empty. Sort it.
    2.2.2 Add two pointers - at the start and end of the array. While first pointer is less then second do a sum of pointed
        elements. If it is less than K, then store result and move the start pointer. If it is larger than K, then decrement
        larger number by moving right pointer.
    2.2.3 Compare result with previous values, leave the maximum one.
 */

class TwoPontersSolution {
    public int twoSumLessThanK(int[] A, int K) {
        int result = -1;
        if (A == null || A.length == 0) {
            return result;
        }

        Arrays.sort(A);

        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            if (A[i] + A[j] < K) {
                result = Math.max(result, A[i] + A[j]);
                i++;
            } else {
                j--;
            }
        }

        return result;
    }
}
