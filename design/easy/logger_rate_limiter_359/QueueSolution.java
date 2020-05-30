package design.easy.logger_rate_limiter_359;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 359. Logger Rate Limiter
 https://leetcode.com/problems/logger-rate-limiter/

 Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

 Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

 It is possible that several messages arrive roughly at the same time.

 Example:
 Logger logger = new Logger();
 // logging string "foo" at timestamp 1
 logger.shouldPrintMessage(1, "foo"); returns true;
 // logging string "bar" at timestamp 2
 logger.shouldPrintMessage(2,"bar"); returns true;
 // logging string "foo" at timestamp 3
 logger.shouldPrintMessage(3,"foo"); returns false;
 // logging string "bar" at timestamp 8
 logger.shouldPrintMessage(8,"bar"); returns false;
 // logging string "foo" at timestamp 10
 logger.shouldPrintMessage(10,"foo"); returns false;
 // logging string "foo" at timestamp 11
 logger.shouldPrintMessage(11,"foo"); returns true;

 --------

 1. Complexity
    1.1 Time Complexity is O(1)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to use queue for keeping list of elements and cleanup old records (from the beginning)
        with the every call
    2.2. Additionally we need a set to do a quick lookup of not-removed entries
 */

class QueueSolution {

    class Log {
        String str;
        int time;

        public Log(String str, int time) {
            this.str = str;
            this.time = time;
        }
    }

    Deque<Log> queue;
    Set<String> set;

    /** Initialize your data structure here. */
    public QueueSolution() {
        queue = new ArrayDeque<>();
        set = new HashSet<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (queue.size() > 0 && timestamp - queue.getFirst().time > 9) {
            Log log = queue.removeFirst();
            set.remove(log.str);
        }

        if (set.contains(message)) {
            return false;
        }

        set.add(message);
        queue.addLast(new Log(message, timestamp));

        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */


/**
     1. Could we have two messages reported at the same second?
     2. And comparisson of the strings should be made by content?
     3. So I need to find the last timestamp for this message if it was presented earlier and check with the current one?
     4. If it is in the range of 10 seconds, should I just return false and still consider the first timestamp?
     5. Are we limited in the space?

     1. Naive approach is to use hashmap to track message and timestamp.
     O(1) but requires O(n) extra space

 */
