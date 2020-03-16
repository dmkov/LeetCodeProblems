package array.medium.nested_list_weight_sum_ii_364;

import java.util.ArrayList;
import java.util.List;
import stack.meduim.flatten_nested_list_iterator_341.NestedInteger;

/**
 364. Nested List Weight Sum II
 https://leetcode.com/problems/nested-list-weight-sum-ii/

 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Input: [[1,1],2,[1,1]]
 Output: 8
 Explanation: Four 1's at depth 1, one 2 at depth 2.

 Example 2:
 Input: [1,[4,[6]]]
 Output: 17
 Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Use additional list to store sums where each index corresponds to the depth level. Traverse array and sum up
        all numbers from this level. For the nested list, call method recursively and increase the depth level. At the end
        sum up all result values with the coefficient for the depth/index of the list.
 3 Implementation
     3.1 Check if input list is not null.
     3.2 Create a list of sums on each depth level and start method recursively for the current list and 0 level.
     3.3 Iterate number per number:
        3.3.1 If it is integer, sum up the numbers on this level and store result at the end to the given sum list.
        3.3.2 If it is a list, check if empty element should be added (sum.size() <= depth + 1) and call method
            recursively for the nested list with increased depth level.
     3.4 Iterate from 1 to sum.size() and sum up numbers weighted by their depth (multiplied on counter).
 */

class DFSSolution {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0;
        if (nestedList == null) {
            return res;
        }

        List<Integer> sum = new ArrayList<>();
        sum.add(0, 0);
        traverse(nestedList, 0, sum);

        for (int k = 1; k <= sum.size(); k++) {
            res += sum.get(sum.size() - k) * k;
        }
        return res;
    }

    private void traverse(List<NestedInteger> nestedList, int depth, List<Integer> sum) {
        int local = 0;
        for (NestedInteger num : nestedList) {
            if (num.isInteger()) {
                local += num.getInteger();
            } else {
                if (sum.size() <= depth + 1) {
                    sum.add(depth + 1, 0);
                }
                traverse(num.getList(), depth + 1, sum);
            }
        }
        sum.set(depth, sum.get(depth) + local);
    }

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *     // Constructor initializes an empty nested list.
     *     public NestedInteger();
     *
     *     // Constructor initializes a single integer.
     *     public NestedInteger(int value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer.
     *     public void setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *     public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
}
