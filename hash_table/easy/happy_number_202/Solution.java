package hash_table.easy.happy_number_202;

import java.util.HashSet;
import java.util.Set;

/**
 202. Happy Number
 https://leetcode.com/problems/happy-number/

 Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example:
 Input: 19
 Output: true

 Explanation:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 --------

 1. Complexity
    1.1 Time Complexity is O(logn) because we are processing each digit in the number, and the number of digits in a number is given by logn.
    1.2 Space Complexity is O(logn). Closely related to the time complexity, and is a measure of what numbers we're putting in the HashSet,
        and how big they are. For a large enough n, the most space will be taken by n itself.
 2. Approach
    2.1 To define squares for every digit, we use a separate helper. Continue the process until n equals 1 or cycle is detected.
 2.2 Implementation
    2.2.1 Check if number is valid (not negative). Create an empty set to detect cycles.
    2.2.2 Until n does not equal 1, get squares of all digits and store new number to the set. If number is repeated. it means that the cycle
        is detected and we should return false.
    2.2.3 To get every digit in the number, get modulo of 10 for the current number. To shift the number for the next operation,
        divide it by 10 at the end of the iteration.
 */

class Solution {
    public boolean isHappy(int n) {
        if (n < 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();

        while (n != 1) {
            n = getSquares(n);
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
        return true;
    }

    private int getSquares(int n) {
        int result = 0;
        while (n != 0) {
            int num = n % 10;
            result += Math.pow(num, 2);
            n = n / 10;
        }
        return result;
    }
}
