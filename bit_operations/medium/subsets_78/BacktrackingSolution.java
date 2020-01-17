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
    2.1 This is recursive backtracking solution to find every possible combination.
 2.2 Implementation
    2.2.1 Check if the input array is valid. Add empty subset for the result.
    2.2.2 Iterate all numbers in the array, create a list with the active number, add it to the result
        and then recursively call method to add more items to the list starting the current position.
    2.2.3 In the recursive method copy passed list, add element from the next index and repeat action for the new list
        and rest of elements in the array..
 */

public class BacktrackingSolution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> empty = new ArrayList<>();
        result.add(empty);

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            List<Integer> list = new ArrayList<>(empty);
            list.add(nums[i]);
            result.add(list);

            addSubset(nums, i + 1, list, result);
        }

        return result;
    }

    private void addSubset(int[] nums, int pos, List<Integer> prev, List<List<Integer>> result) {
        int length = nums.length;
        for (int i = pos; i < length; i++) {
            List<Integer> list = new ArrayList<>(prev);
            list.add(nums[i]);
            result.add(list);

            addSubset(nums, i + 1, list, result);
        }
    }
}
