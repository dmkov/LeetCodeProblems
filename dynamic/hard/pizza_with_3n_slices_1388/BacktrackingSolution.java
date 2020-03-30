package dynamic.hard.pizza_with_3n_slices_1388;

import java.util.HashMap;
import java.util.Map;

/**
 1388. Pizza With 3n Slices
 https://leetcode.com/problems/pizza-with-3n-slices/

 There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:
 - You will pick any pizza slice.
 - Your friend Alice will pick next slice in anti clockwise direction of your pick.
 - Your friend Bob will pick next slice in clockwise direction of your pick.
 Repeat until there are no more slices of pizzas.

 Sizes of Pizza slices is represented by circular array slices in clockwise direction.

 Return the maximum possible sum of slice sizes which you can have.

 Example 1:
 Input: slices = [1,2,3,4,5,6]
 Output: 10
 Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.

 Example 2:
 Input: slices = [8,9,8,6,1,1]
 Output: 16
 Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.

 Example 3:
 Input: slices = [4,1,2,5,8,3,1,9,7]
 Output: 21

 Example 4:
 Input: slices = [3,1,2]
 Output: 3

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2) if we use memoization and O(2^n) if we do not
     1.2 Space Complexity is O(n) because of memo and recursive memory stack
 2. Approach
     2.1 We simplify all task to the following problem - we can select or skip the element. In case if the current element
        is selected - the next one should be definitely skipped. So we consider both backtracking approaches and check which
        gives us the max value at the end.
 3 Implementation
     3.1 Check if input array is valid. Create empty map for the memo object.
     3.2 Start a recursive function to get the max sum of elements from the very first and last positions:
        3.2.1 Check if end is less than start, return 0, if it is the case.
        3.2.2 If it is the last possible pick (define it is as numbers/3 and decrease with every cycle) then check the max
                from remaining number.
        3.2.3 Otherwise, return the max of backtracking the next element or next-1 + the current value.
        3.2.4 There is a small trick to do not count first element in the first pick - pass a boolean or int flag for this.
 */

class BacktrackingSolution {

    public int maxSizeSlices(int[] slices) {
        int result = 0;
        if (slices == null || slices.length == 0) {
            return result;
        }

        Map<String, Integer> memo = new HashMap<>();
        return backtracking(slices, 0, slices.length - 1, slices.length/3, 1, memo);
    }

    private int backtracking(int[] slices, int start, int end, int times, int cycle, Map<String, Integer> memo) {
        if (end < start) {
            return 0;
        }

        String key = start + "_" + end + "_" + times;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result = 0;
        if (times == 1) {
            for (int i = start; i <= end; i++) {
                result = Math.max(slices[i], result);
            }
            return result;
        }

        result = Math.max(backtracking(slices, start + cycle, end - 2, times - 1, 0, memo) + slices[end],
                backtracking(slices, start, end - 1, times, 0, memo));

        memo.put(key, result);

        return result;
    }
}
