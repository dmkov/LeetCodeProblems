package bit_operations.easy.sum_of_two_integers_371;

/**
 371. Sum of Two Integers
 https://leetcode.com/problems/sum-of-two-integers/

 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example 1:
 Input: a = 1, b = 2
 Output: 3

 Example 2:
 Input: a = -2, b = 3
 Output: 1

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to use binary '&' and '^' to get sum and check the carry.
    2.2 My bad here is that I do manipulations bite by bite (check another version for the improvement).
 2.2 Implementation
    2.2.1 Iterate from first byte to 32. For both numbers do "& 1" to clear other bytes, then check aBit ^ bBit for the
        sum and sumBit ^= carry for the total result with carry.
    2.2.2 If both bits are "1" or any of them "1" and carry exists then transmit carry to the next round.
    2.2.3 Shift sumBit for required number of bits and apply it to total sum. Do not forget to shift
        a" and "b" for one byte
 */

public class OwnLongSolution {
    public int getSum(int a, int b) {
        int mem = 0;
        int sum = 0;

        for (int i = 0; i < 32; i++) {
            int aBit = a & 1;
            int bBit = b & 1;
            int sumBit = aBit ^ bBit;
            sumBit ^= mem;

            if ((aBit & bBit) == 1 || ((aBit | bBit) & mem) == 1) {
                mem = 1;
            } else {
                mem = 0;
            }

            sumBit = sumBit << i;
            sum = sum | sumBit;

            a = a >> 1;
            b = b >> 1;
        }

        return sum;
    }
}
