package stack.meduim.exclusive_time_of_functions_636;

import java.util.List;
import java.util.Stack;

/**
 636. Exclusive Time of Functions
 https://leetcode.com/problems/exclusive-time-of-functions/

 On a single threaded CPU, we execute some functions. Each function has a unique id between 0 and N-1.
 We store logs in timestamp order that describe when a function is entered or exited.

 Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".
 For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3. "1:end:2" means the
 function with id 1 ended at the end of timestamp 2.

 A function's exclusive time is the number of units of time spent in this function.
 Note that this does not include any recursive calls to child functions.

 The CPU is single threaded which means that only one function is being executed at a given time unit.

 Return the exclusive time of each function, sorted by their function id.

 Example 1:
 Input:
 n = 2
 logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 Output: [3, 4]

 Explanation:
 Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
 Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
 Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
 So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.


 Note:
 1 <= n <= 100
 Two functions won't start or end at the same time.
 Functions will always log when they exit.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is based on using stack and putting a new element with every start and popping it on the end of the interval.
 2.2 Implementation
     2.2.1 Check if input list and number of elements are valid
     2.2.2 For every element check its type.
        2.2.2.3 For start point, check if stack is not empty (it means that another interval interrupts the current one).
            In such case update the element in the array with the stack.peek() index and push a new element to the stack.
            Start time also should be updated to the log time.
        2.2.2.4 For end point, update element in the list, popping its index from the stack. The trick is that the next interval
            starts from the next index, so we update start time to log time + 1. Also we add +1 to the element interval time since
            the previous start time belongs to the current interval.
 */

class Solution {
    class Log {
        int el;
        int time;
        String type;

        public Log(String str) {
            String[] s = str.split(":");

            el = Integer.parseInt(s[0]);
            type = s[1];
            time = Integer.parseInt(s[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        if (n < 1) {
            return null;
        }
        int[] result = new int[n];
        if (logs == null || logs.size() == 0) {
            return result;
        }

        int start = 0;
        Stack<Integer> stack = new Stack<>();
        for (String str: logs) {
            Log log = new Log(str);

            if (log.type.equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += log.time - start;
                }
                stack.push(log.el);
                start = log.time;
            } else {
                result[stack.pop()] += log.time - start + 1;
                start = log.time + 1;
            }
        }

        return result;
    }
}
