package bit_operations.easy.binary_watch_401;

import java.util.ArrayList;
import java.util.List;

/**
 401. Binary Watch
 https://leetcode.com/problems/binary-watch/

 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 Each LED represents a zero or one, with the least significant bit on the right.

 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:
 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

 --------

 1. Complexity
    1.1 Time Complexity is O(12 * 60) - which is long but technically is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 We iterate all possible combinations for hours (0-11) and minutes (0-59) and check if sum of bits equals to required number.
 2.2 Implementation
    2.2.1 Iterate from 0 to 11 and from 0 to 59 in the second loop. For every combination check the sum of bits for hours and minutes.
    2.2.2 To count bits shift current number 31 times and do a binary '&' with the '1'.
    2.2.3 Compute string values from all possible combinations and return it as a result.
 */

public class BruteLoopSolution {
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();

        for (int hour = 0; hour < 12; hour++) {
            for (int min = 0; min < 60; min++) {
                if (countBits(hour) + countBits(min) == num) {
                    result.add(computeString(hour, min));
                }
            }
        }

        return result;
    }

    private int countBits(int num) {
        int total = 0;
        for (int i = 0; i < 32; i++) {
            if (((num >> i) & 1) == 1) {
                total++;
            }
        }
        return total;
    }

    private String computeString(int hour, int min) {
        return String.format(
                (min > 9) ? "%s:%s" : "%s:0%s", hour, min
        );
    }
}
