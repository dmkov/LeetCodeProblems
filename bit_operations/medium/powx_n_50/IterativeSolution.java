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
    1.1 Time Complexity is O(n) - where n is the number of bits of n in a system of 2
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Convert n to system of 2 (6 -> 0110). x^6 means x^(2^2)*x^(2^1)
    2.2 Start count product for every bit -> x*x*x... every time you hit 1, multiple it on result
 */

public class IterativeSolution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }

        double res = 1, product = x;
        long k = Math.abs((long)n);
        while (k > 0) {
            if ((k & 1) == 1) {
                res *= product;
            }
            product *= product;
            k = k >>> 1;
        }

        return (n > 0) ? res : (1/res);
    }
}
