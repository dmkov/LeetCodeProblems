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
     2.1 With 3-level list, first numbers should be added 3 times (multiplied by 3), second - 2 times,
        and the last ones - only once. We can replace multiplication with just summing these numbers on every level
        one more time. So instead of computing every level separately, we will sum up existing numbers to the total number
        as many times as many levels we have.
 3 Implementation
     3.1 Check if input list is not null.
     3.2 Create two integer variables - for the level and total sums.
     3.3 Iterate while nested list is not empty:
         3.3.1 If it is integer, sum up the numbers on the level sum.
         3.3.2 If it is a list, add nested items to the next level list.
         3.3.3 At the end of the level, add to the total sum the current level sum. Assign next level list
            to the nested list for the next iteration.
     3.4 Return total sum as the result of the method.
 */

class MathSolution {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return 0;
        }

        int level = 0;
        int weighted = 0;
        while (nestedList.size() > 0) {
            List<NestedInteger> nextLevel = new ArrayList<>();

            for (NestedInteger num : nestedList) {
                if (num.isInteger()) {
                    level += num.getInteger();
                } else {
                    nextLevel.addAll(num.getList());
                }
            }

            nestedList = nextLevel;
            weighted += level;
        }

        return weighted;
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
