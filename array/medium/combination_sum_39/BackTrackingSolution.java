package array.medium.combination_sum_39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 39. Combination Sum
 https://leetcode.com/problems/combination-sum/

 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
     [7],
     [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
     [2,2,2,2],
     [2,3,3],
     [3,5]
 ]

---

 1. Complexity
    1.1 Time Complexity is O(n*n!)
    1.2 Space Complexity is O(n!)
 2. Approach
    2.1 Idea is to get all possible sums for every element in the list. To do not count duplicated combinations
        we do not move back in checking candidates.
 3. Implementation
    3.1 Check if the input array is valid. Sort it to speed up next method a little and reduce number of checked options.
    3.2 Create empty list and call traverse method for the first index and 0 sum.
        3.2.1 Starting from the passed index to the end of the array, try to sum up all elements in the remaining array
            with the given sum.
        3.2.2 If new sum is less than target (possible candidate), then add candidate to the list and call traverse method
            recursively. After the method - remove candidate from the list (this prevents creating a new copy of the array every time)
        3.2.3 If new sum is larger than target - just break the loop. Since array is sorted, it means that all next elements
            are greater than the current one.
 */

class BackTrackingSolution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);

        List<Integer> list = new ArrayList<>();
        traverse(list, 0, target, candidates, 0, result);

        return result;
    }

    private void traverse(List<Integer> list, int sum, int target, int[] nums, int limit, List<List<Integer>> result) {
        if (sum == target) {
            result.add(new LinkedList<>(list));
            return;
        }
        for (int i = limit; i < nums.length; i++) {
            int newSum = (sum + nums[i]) % Integer.MAX_VALUE;
            if (newSum <= target) {
                list.add(nums[i]);
                traverse(list, newSum, target, nums, i, result);
                list.remove(list.size() - 1);
            } else {
                break;
            }
        }
    }
}
