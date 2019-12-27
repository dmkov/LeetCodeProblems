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
    2.1 Get value of every bit by binary '&'. Update original value manually according to received result
 2.2 Implementation
    2.2.1 Set two pointers, one - at the beginning and second at the end of the number (32 bits). While they do not
        cross do the next iteration.
    2.2.2 Create a mask from 1 shifting it to the x and y pointer values.
    2.2.3 Do binary '&' to receive value of the bit. If it is equal to '0' set original number at the opposite pointer
        to '0', otherwise - to '1'.
 */

public class InPlaceReverseLongSolution {
    // you need to treat n as an unsigned value
    public int reverseBits(int n) {
        int xPointer = 31;
        int yPointer = 0;

        while (xPointer > yPointer) {
            int xMask = 1 << xPointer;
            int yMask = 1 << yPointer;

            int x = n & xMask;
            int y = n & yMask;

            if (x == 0) {
                n = setZero(n, yPointer);
            } else {
                n = setOne(n, yPointer);
            }
            if (y == 0) {
                n = setZero(n, xPointer);
            } else {
                n = setOne(n, xPointer);
            }

            xPointer--;
            yPointer++;
        }
        return n;
    }

    private int setOne(int n, int bit) {
        int mask = 1 << bit;
        return n | mask;
    }

    private int setZero(int n, int bit) {
        int mask = 1 << bit;
        return n & (~mask);
    }
}
