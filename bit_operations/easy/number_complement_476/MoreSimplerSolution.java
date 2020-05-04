package bit_operations.easy.number_complement_476;

/**
 476. Number Complement
 https://leetcode.com/problems/number-complement/

 Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

 Example 1:
 Input: 5
 Output: 2
 Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

 Example 2:
 Input: 1
 Output: 0
 Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

 Note:
 The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 You could assume no leading zero bit in the integer’s binary representation.

 --------

 1. Complexity
    1.1 Time Complexity is O(1)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Shift copy of number until it reaches 0. Meantime, reverse original number bit by bit.
 */

public class MoreSimplerSolution {

    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }

        int mask = 1;
        int todo = num;
        while (todo > 0) {
            num = num ^ mask;
            mask = mask << 1;
            todo = todo >> 1;
        }
        return num;
    }

}
