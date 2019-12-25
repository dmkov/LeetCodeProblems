package bit_operations.easy.number_of_1_bits_191;

/**
 191. Number of 1 Bits
 https://leetcode.com/problems/number-of-1-bits/

 Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

 Example 1:
 Input: 00000000000000000000000000001011
 Output: 3
 Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.

 Example 2:
 Input: 00000000000000000000000010000000
 Output: 1
 Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.

 Example 3:
 Input: 11111111111111111111111111111101
 Output: 31
 Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 After binary 'AND' for 'n' with 'n-1' we flip least-significant 1-bit to 0. Repeating operation until 'n'
        is 0 will remove '1' once per step. So we need to count steps and it will be the answer.
 2.2 Implementation
    2.2.1 Iterate until 'n' does not equal '0'. Do binary 'AND' for the 'n' with 'n-1'.
    2.2.2 Increment sum counter with every step.
 */

public class MinusOneSolution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            n = n & (n - 1);
            sum++;
        }
        return sum;
    }
}
