package array.medium.task_scheduler_621;

import java.util.HashMap;
import java.util.Map;

/**
 621. Task Scheduler
 https://leetcode.com/problems/task-scheduler/

 You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task. Tasks could be done without the original order of the array. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

 However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

 You need to return the least number of units of times that the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation:
 A -> B -> idle -> A -> B -> idle -> A -> B
 There is at least 2 units of time between any two same tasks.

 Example 2:
 Input: tasks = ["A","A","A","B","B","B"], n = 0
 Output: 6
 Explanation: On this case any permutation of size 6 would work since n = 0.
 ["A","A","A","B","B","B"]
 ["A","B","A","B","A","B"]
 ["B","B","B","A","A","A"]
 ...
 And so on.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 https://leetcode.com/articles/task-scheduler/
    2.2 The idea is that the answer is the max of ((n+1)*(max-1) + maxNum) or tasks.length
    2.3 The first case is when there are too many unique elements and n is big,
        the second one, if we can fill everything with numbers.
 */

public class MathSolution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            Integer counter = map.getOrDefault(ch, 0);
            map.put(ch, counter + 1);
        }

        int max = 0;
        int maxNum = 0;
        for (int num : map.values()) {
            if (num > max) {
                max = num;
                maxNum = 1;
            } else if (num == max) {
                maxNum++;
            }
        }

        return Math.max((n+1)*(max-1) + maxNum, tasks.length);
    }
}
