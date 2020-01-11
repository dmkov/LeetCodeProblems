package sort.easy.reorder_data_in_log_files_937;

import java.util.Arrays;
import java.util.Comparator;

/**
 937. Reorder Data in Log Files
 https://leetcode.com/problems/reorder-data-in-log-files/

 You have an array of logs.  Each log is a space delimited string of words.
 For each log, the first word in each log is an alphanumeric identifier.  Then, either:

 Each word after the identifier will consist only of lowercase letters, or;
 Each word after the identifier will consist only of digits.
 We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

 Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

 Return the final order of the logs.

 Example 1:
 Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

 Constraints:
 0 <= logs.length <= 100
 3 <= logs[i].length <= 100
 logs[i] is guaranteed to have an identifier, and a word after the identifier.

---

 1. Complexity
    1.1 Time Complexity is O(nlog n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on binary search
    2.2 Define two pointer left and right where range is in 0 and nums.length-1
    2.3 Get the middle left + (right - left) / 2
    2.4 Check the mid value and shift bounds depending on response:
        2.4.1 nums[mid] > target: right = mid - 1
        2.4.2 nums[mid] < target: left = mid + 1, result = mid + 1
    2.5 Repeat from 2.3 (while left <= right && nums[result] != target loop)

 */

class Solution {
    private enum LOG_TYPE {STRING, DIGIT};

    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return logs;
        }

        // Sort by type
        int d = logs.length - 1;
        int p = logs.length - 1;
        while (p >= 0) {
            if (getType(logs[p]) == LOG_TYPE.STRING) {
                p--;
            } else {
                String temp = logs[p];
                logs[p] = logs[d];
                logs[d] = temp;
                p--;
                d--;
            }
        }

        // Sort strings from 0..d
        Arrays.sort(logs, 0, d + 1, new Comparator<String>() {
            public int compare(String a, String b) {
                int result = getMessage(a).compareTo(getMessage(b));
                if (result != 0) {
                    return result;
                } else {
                    return a.compareTo(b);
                }
            }
        });
        return logs;
    }

    private LOG_TYPE getType(String log) {
        String message = getMessage(log);
        if (message.charAt(0) <= 'z' && message.charAt(0) >= 'a') {
            return LOG_TYPE.STRING;
        } else {
            return LOG_TYPE.DIGIT;
        }
    }

    private String getMessage(String log) {
        int space = 0;
        while (log.charAt(space) != ' ') {
            space++;
        }

        return log.substring(space + 1, log.length());
    }
}
