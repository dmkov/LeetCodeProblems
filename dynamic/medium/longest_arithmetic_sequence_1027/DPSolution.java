package dynamic.medium.longest_arithmetic_sequence_1027;

import java.util.HashMap;
import java.util.Map;

/**
 1027. Longest Arithmetic Sequence
 https://leetcode.com/problems/longest-arithmetic-sequence/

 Given an array A of integers, return the length of the longest arithmetic subsequence in A.

 Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

 Example 1:
 Input: [3,6,9,12]
 Output: 4
 Explanation:
 The whole array is an arithmetic sequence with steps of length = 3.

 Example 2:
 Input: [9,4,7,2,10]
 Output: 3
 Explanation:
 The longest arithmetic subsequence is [4,7,10].

 Example 3:
 Input: [20,1,15,3,10,5,8]
 Output: 4
 Explanation:
 The longest arithmetic subsequence is [20,15,10,5].


 Note:
 2 <= A.length <= 2000
 0 <= A[i] <= 10000

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 The idea is to create a hash map of all differences for each number in the list and store it in another hash map
        with the number itself as a key. After computing new differences for the next number, we add diff to it and check
        if we already have pro-longed number in the hash map. If yes, add its diff counter to the current one.
 3 Implementation
     3.1 Check if input array is valid and it's length is greater than 1.
     3.2 Create a hash map dp to store other hash maps with diffs for every number.
     3.3 Iterate all elements:
         3.3.1 Create an empty hash map and iterate all elements after the current one:
            3.3.1.1 Get the diff between active and the current elements
            3.3.1.2 Check if current element + diff exists in the dp hash map. If it does - check the value for exact same
                diff in it and add it to the counter.
         3.3.2 Check if current counter is bigger than stored max.
         3.3.3 Store counter to the hash map for the further usage
     3.4 Return the max computed diff.
 */

class DPSolution {

    public int longestArithSeqLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();

        int max = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int val = arr[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < arr.length; j ++) {
                int diff = arr[i] - arr[j];
                int counter = 1;
                if (dp.containsKey(val + diff)) {
                    Map<Integer, Integer> past = dp.get(val + diff);
                    counter += past.getOrDefault(diff, 0);
                }
                max = Math.max(max, counter);
                map.put(diff, counter);
            }
            dp.put(val, map);
        }

        return max + 1;
    }
}
