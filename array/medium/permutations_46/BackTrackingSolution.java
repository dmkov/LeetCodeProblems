package array.medium.permutations_46;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 46. Permutations
 https://leetcode.com/problems/permutations/

 Given a collection of distinct integers, return all possible permutations.

 Example:
 Input: [1,2,3]
 Output:
 [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
 ]

---

 1. Complexity
    1.1 Time Complexity is O(n*n!)
    1.2 Space Complexity is O(n!)
 2. Approach
    2.1 Idea is to place every number on its possible position and then recursively repeat method
        for the next number in the list for each case
 3. Implementation
    3.1 Check if the input arrays are valid
    3.2 Create and populate initial list with the size of input arr and null elements
    3.3 Execute recursive method for the first number in the given array.
    3.4 Iterate all empty positions in the initial list. Create a copy of initial list, put there a current number
        and recursively execute method for the next position and new copied initial list
 */

class BackTrackingSolution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(null);
        }

        traverse(nums, 0, list, result);

        return result;
    }

    private void traverse(int[] nums, int pos, List<Integer> list, List<List<Integer>> result) {
        int size = nums.length;
        if (pos == size) {
            result.add(list);
        } else {
            for (int i = 0; i < size; i++) {
                if (list.get(i) != null) {
                    continue;
                }
                List<Integer> n = new ArrayList<>(list);
                n.set(i, nums[pos]);
                traverse(nums, pos + 1, n, result);
            }
        }
    }
}
