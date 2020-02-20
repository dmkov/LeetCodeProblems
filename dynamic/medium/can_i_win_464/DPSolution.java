package dynamic.medium.can_i_win_464;

import java.util.HashMap;
import java.util.Map;

/**
 464. Can I Win
 https://leetcode.com/problems/can-i-win/

 In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

 What if we change the game so that players cannot re-use integers?
 For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

 You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

 Example
 Input:
 maxChoosableInteger = 10
 desiredTotal = 11
 Output:
 false

 Explanation:
 No matter which integer the first player choose, the first player will lose.
 The first player can choose an integer from 1 up to 10.
 If the first player choose 1, the second player can only choose integers from 2 up to 10.
 The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 Same with other integers chosen by the first player, the second player will always win.

 --------

 1. Complexity
     1.1 Time Complexity is O(n!) without memo and O(2^n) with memoization
     1.2 Space Complexity is O(2^n)
 2. Approach
     2.1 The most tricky part is define when the player wins :) The answer - when opposite player could not win with remaining numbers.
        So we need to reduce possible options and execute functions recursively. The answer for the current step will be
        the inverted result of the next iteration. Player also wins if he can put the number bigger or equal to target.
     2.2 To encode steps and possible options use bits in an integer number. We can also use this number as index in the
        hash map for memoization.
 3 Implementation
     3.1 Check if given numbers are valid.
        3.1.1 If total number is already 0 - return true.
        3.1.2 Count possible sum for all available numbers. If the sum is less than given total - return false.
     3.2 Encode all possible numbers with bits in `bank` integer. From 1 to the max number shift 1 and do binary 'or'
        to set the bit in the number.
     3.3 Create empty map for memoization and call the recursive check method with all created parameters.
     3.4 Inside the check method:
        3.4.1 If result for given bank is already in the map - return it without future computation.
        3.4.2 Start loop from 1 to the max value and check if active bit is still available (set) in the bank variable.
        3.4.3 If number is available, check if it is equal or bigger than total - if yes, return true, since the player wins
        3.4.4 Otherwise reduce number from total, set bit to 0 and call method recursively one more time.
            If result is false (means opposite player could not win) - set the current result to true.
        3.4.5 Store result of the current bank to the memo variable and return it as the result of the method.
 */

class DPSolution {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        int sum = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            sum += i;
        }
        if (sum < desiredTotal) {
            return false;
        }

        int bank = 0;
        for (int i = 0; i < maxChoosableInteger; i++) {
            bank = (bank | (1 << i));
        }

        Map<Integer, Boolean> map = new HashMap<>();
        return check(maxChoosableInteger, desiredTotal, bank, map);
    }

    private boolean check(int max, int total, int bank, Map<Integer, Boolean> map) {
        if (map.containsKey(bank)) {
            return map.get(bank);
        }

        boolean result = false;
        for (int i = 0; i < max; i++) {
            int fl = (1 << i);
            if ((bank & fl) > 0) {
                if (total - i - 1 <= 0 || !check(max, total - i - 1, (bank & ~fl), map)) {
                    result = true;
                    break;
                }
            }
        }
        map.put(bank, result);

        return result;
    }
}
