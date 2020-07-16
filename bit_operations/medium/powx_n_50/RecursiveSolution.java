package bit_operations.medium.powx_n_50;

/**
 50. Pow(x, n)
 https://leetcode.com/problems/powx-n/

 Implement pow(x, n), which calculates x raised to the power n (xn).

 Example 1:
 Input: 2.00000, 10
 Output: 1024.00000

 Example 2:
 Input: 2.10000, 3
 Output: 9.26100

 Example 3:
 Input: 2.00000, -2
 Output: 0.25000
 Explanation: 2-2 = 1/22 = 1/4 = 0.25

 --------

 1. Complexity
    1.1 Time Complexity is O(logn)
    1.2 Space Complexity is O(logn) because of recursion
 2. Approach
    2.1 Calculate power of n/2 and then multiple it on itself to get the result
 */

public class RecursiveSolution {
    public double myPow(double x, int n) {
        double res = divideAndMultiple(x, Math.abs(n));
        res = (n > 0) ? res : (1/res);

        return res;
    }

    private double divideAndMultiple(double num, int power) {
        if (power == 0) {
            return 1;
        } else if (power == 1) {
            return num;
        }

        int half = power / 2;
        double res = divideAndMultiple(num, half);
        res *= res;

        if (power % 2 != 0) {
            res *= num;
        }
        return res;
    }
}
