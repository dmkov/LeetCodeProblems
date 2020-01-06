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
    1.1 Time Complexity is O(n!) (which is huge but since the max n=10, it is not so dramatic)
    1.2 Space Complexity is O(n!)
 2. Approach
    2.1 This is recursive backtracking solution to find for every combination of hour(s) possible variations of minutes.
 2.2 Implementation
    2.2.1 Define arrays with possible integer values of hours and minutes if only one bit is set in the number.
    2.2.2 The max number of both arrays combinations is 5 (the first is "0" for hours and rest for minutes and
        "1-4" for hours and "minutes - 1...4" for minutes). Iterate these possible combinations and for every step get
        the list of possible hours and minutes.
    2.2.3 To get possible numbers do a recursive backtracking - for every index in integer values check which remaining
        numbers can get the required set of numbers. Exclude the current one and recursively execute function for
        remaining list decreasing the total count.
    2.2.4 If total count is "0" - add number to the result, it means that possible combination was found.
    2.2.5 At the end compute string values from possible hours and minutes numbers in the current combination.
 */

public class BacktrackingSolution {
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        int[] hourBits = {1, 2, 4, 8};
        int[] minBits = {1, 2, 4, 8, 16, 32};

        for (int i = 0; i <= num && i <= 4; i++) {
            List<Integer> hours = getDigits(hourBits, i);
            List<Integer> mins = getDigits(minBits, num - i);
            result.addAll(computeStrings(hours, mins));
        }

        return result;
    }

    private List<Integer> getDigits(int[] list, int i) {
        List<Integer> res = new ArrayList<>();
        getDigits(res, list, 0, 0, i);

        return res;
    }

    private void getDigits(List<Integer> res, int[] list, int start, int sum, int num) {
        if (num == 0) {
            res.add(sum);
            return;
        }

        for (int i = start; i < list.length; i++) {
            getDigits(res, list, i + 1, sum + list[i], num - 1);
        }
    }

    private List<String> computeStrings(List<Integer> hours, List<Integer> mins) {
        List<String> result = new ArrayList<>();
        for (Integer hour: hours) {
            if (hour >= 12) continue;
            for (Integer min: mins) {
                if (min >= 60) continue;
                result.add(String.format(
                        (min > 9) ? "%s:%s" : "%s:0%s", hour, min
                ));
            }
        }

        return result;
    }
}
