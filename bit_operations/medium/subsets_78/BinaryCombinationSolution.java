package bit_operations.medium.subsets_78;

import java.util.ArrayList;
import java.util.List;

/**
 78. Subsets
 https://leetcode.com/problems/subsets/

 Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:
 Input: nums = [1,2,3]
 Output:
 [
     [3],
     [1],
     [2],
     [1,2,3],
     [1,3],
     [2,3],
     [1,2],
     []
 ]

 --------

 1. Complexity
    1.1 Time Complexity is O(n*2^n) (which is huge but since the max n=10, it is not so dramatic)
    1.2 Space Complexity is O(2^n)
 2. Approach
    2.1 The idea is based on the fact that all possible combination of n elements is 2^n. We generate binary representation
        for every combination from 0 to 2^n and get elements from the array with indexes where digits are '1'.
 2.2 Implementation
    2.2.1 Check if the input array is valid. Add empty subset for the result.
    2.2.2 There is a trick to get binary numbers with leading zeros. We need to create mask with 1 at the index next to required
        and then do substring(1) for the final string.
    2.2.3 From 0 to 2^n generate all possible binary numbers in strings and iterate every string char by char. It char is '1' -
        get element from nums with the same index.
 */

public class BinaryCombinationSolution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int length = nums.length;
        int mask = 1 << length;
        for (int i = 0; i < (int) Math.pow(2, length); i++) {
            String binary = Integer.toBinaryString(i | mask).substring(1);

            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                if (binary.charAt(j) == '1') {
                    list.add(nums[j]);
                }
            }
            result.add(list);
        }

        return result;
    }
}
