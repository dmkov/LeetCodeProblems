package graph.medium.open_the_lock_752;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 752. Open the Lock
 https://leetcode.com/problems/open-the-lock/

 You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

 The lock initially starts at '0000', a string representing the state of the 4 wheels.

 You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

 Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

 Example 1:
 Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 Output: 6
 Explanation:
 A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 because the wheels of the lock become stuck after the display becomes the dead end "0102".

 Example 2:
 Input: deadends = ["8888"], target = "0009"
 Output: 1
 Explanation:
 We can turn the last wheel in reverse to move from "0000" -> "0009".

 Example 3:
 Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 Output: -1
 Explanation:
 We can't reach the target without getting stuck.

 Example 4:
 Input: deadends = ["0000"], target = "8888"
 Output: -1
 Note:
 The length of deadends will be in the range [1, 500].
 target will not be in the list deadends.
 Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.

 --------

 1. Complexity
    1.1 Time Complexity is  O(N^2 * A^N + D) where A is the number of digits in our alphabet,
        N is the number of digits in the lock, and DD is the size of deadends.
        We might visit every lock combination, plus we need to instantiate our set dead.
        When we visit every lock combination, we spend O(N^2) time enumerating through and constructing each node.
    1.2 Space Complexity is O(A^N + D), for the queue and the set dead..
 2. Approach
    2.1 Imagine all combinations as graph nodes with transmissions up and down in one digit. Now we need to find the shortest
        path. Since all steps has the same weight we can use BFS and stop when we reach the first result.
 */

public class BFSSolution {
    public int openLock(String[] deadends, String target) {
        if (target == null || target.length() != 4) {
            return -1;
        }

        Set<String> blocks = new HashSet<>(Arrays.asList(deadends));
        if (target.equals("0000")) {
            return 0;
        }
        if (blocks.contains("0000")) {
            return - 1;
        }

        Set<String> visited = new HashSet<>();
        visited.add("0000");
        int steps = 0;

        Deque<String> q = new ArrayDeque<>();
        q.addLast("0000");
        while (q.size() > 0) {
            int bucket = q.size();
            while (bucket > 0) {
                String cur = q.removeFirst();

                if (target.equals(cur)) {
                    return steps;
                }
                if (blocks.contains(cur)) {
                    bucket--;
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    String incremented = cur.substring(0, i)
                            + increment(Integer.valueOf(cur.substring(i, i+1)))
                            + cur.substring(i+1, 4);
                    if (!visited.contains(incremented)) {
                        q.addLast(incremented);
                        visited.add(incremented);
                    }

                    String decremented = cur.substring(0, i)
                            + decrement(Integer.valueOf(cur.substring(i, i+1)))
                            + cur.substring(i+1, 4);
                    if (!visited.contains(decremented)) {
                        q.addLast(decremented);
                        visited.add(decremented);
                    }
                }
                bucket--;
            }
            steps++;
        }

        return -1;
    }

    private int increment(int a) {
        if (a == 9) {
            return 0;
        }
        return a + 1;
    }

    private int decrement(int a) {
        if (a == 0) {
            return 9;
        }
        return a - 1;
    }

}

