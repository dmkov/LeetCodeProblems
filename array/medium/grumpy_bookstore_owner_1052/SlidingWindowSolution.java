package array.medium.grumpy_bookstore_owner_1052;

/**
 1052. Grumpy Bookstore Owner
 https://leetcode.com/problems/grumpy-bookstore-owner/

 Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

 On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

 The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.

 Return the maximum number of customers that can be satisfied throughout the day.

 Example 1:
 Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 Output: 16
 Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

 Note:
 1 <= X <= customers.length == grumpy.length <= 20000
 0 <= customers[i] <= 1000
 0 <= grumpy[i] <= 1

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Use sliding window to get the max possible "grumpy" customers. Then add it to the sum of "happy" customers
 3 Implementation
    3.1 Check if given array is valid.
    3.2 Iterate over all customers:
        3.2.1 If owner was not grumpy - add value to the total sum
        3.2.2 If owner is grumpy - add value to the sliding subSum, also subtract the first element
            if owner was grumpy there
        3.2.3 Get the max subSum of received values
 */

public class SlidingWindowSolution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        if (customers == null || grumpy == null || customers.length != grumpy.length || X > grumpy.length) {
            return sum;
        }

        int subSum = 0;
        int max = 0;
        for (int i = 0; i < customers.length; i++) {
            // Sum up non-grumpy customers separately since they are always included to the answer
            if (grumpy[i] == 0) {
                sum += customers[i]; // 3
            }

            // Use sliding window to get the max possible grumpy customers
            if (i >= X && grumpy[i - X] == 1) {
                subSum -= customers[i - X];
            }
            if (grumpy[i] == 1) {
                subSum += customers[i];
            }
            max = Math.max(max, subSum);
        }

        // result is the sum of non-grumpy customers and max window of grumpied customers
        return sum + max;
    }
}
