package stack.meduim.daily_temperatures_739;

import java.util.Stack;

/**
 739. Daily Temperatures
 https://leetcode.com/problems/daily-temperatures/

 Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

 For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

---

 1. Complexity
    1.1 Time Complexity is O(n), every element is pushed and popped from stack only once
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is based on using stack and putting a new element every day. We also check if there are smaller items in the stack
        and remove them to have always bigger items than current in increasing order.
 3 Implementation
     3.1 Check if input array is not empty. Create result array with the same length.
     3.2 For every element in the list starting from the last item to the first:
        3.2.1 Get the temperature. Check if stack is not empty and peek() element is smaller than the current value - pop() it.
        3.2.2 If stack is not empty, get the first element and calculate the distance in indexes with it
        3.2.3 Push the current element for the further calculation
 */

class StackSolution {

    public int[] dailyTemperatures(int[] temp) {
        if (temp == null || temp.length == 0) {
            return new int[0];
        }

        int length = temp.length;
        int[] result = new int[length];

        Stack<int[]> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            int t = temp[i];
            while (stack.size() > 0 && stack.peek()[1] <= t) {
                stack.pop();
            }
            if (stack.size() > 0) {
                result[i] = stack.peek()[0] - i;
            }
            stack.push(new int[]{i, t});
        }

        return result;
    }

}
