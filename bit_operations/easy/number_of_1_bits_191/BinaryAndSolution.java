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
    2.1 Do binary 'AND' for every bit with '1' and if result is '1' then initial bit was '1' as well.
 2.2 Implementation
    2.2.1 Iterate from 0 to 31 (number of bits in integer). Do binary 'AND' for the last bit with '1'. If result
        is equal '1' then increment the sum.
    2.2.2 Shift number to the left (or mask to the right if you do not want to modify original value).
 */

public class BinaryAndSolution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                sum++;
            }
            n = n >> 1;
        }
        return sum;
    }
}
