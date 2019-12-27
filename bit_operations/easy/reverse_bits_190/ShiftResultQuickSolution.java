package bit_operations.easy.reverse_bits_190;

/**
 190. Reverse Bits
 https://leetcode.com/problems/reverse-bits/

 Reverse bits of a given 32 bits unsigned integer.

 Example 1:
 Input: 00000010100101000001111010011100
 Output: 00111001011110000010100101000000
 Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
    so return 964176192 which its binary representation is 00111001011110000010100101000000.

 Example 2:
 Input: 11111111111111111111111111111101
 Output: 10111111111111111111111111111111
 Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
    so return 3221225471 which its binary representation is 10111111111111111111111111111111.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Get value of every bit by binary '&'. Set value to the result value and shift it to the left.
 2.2 Implementation
    2.2.1 Do iteration from 0 to 31 bit. Get the value of the last bit by shifting the number to the right and do
        binary '&'.
    2.2.2 Shift result with previous value to the left (the initial '0' is not affected but we would like to avoid
        shifting the 32rd bit so that is why we do it initially) and do binary '|' to set bit to the result.
 */

public class ShiftResultQuickSolution {
    // you need to treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 & (n >> i);

            result = result << 1;
            result = result | bit;
        }

        return result;
    }
}
