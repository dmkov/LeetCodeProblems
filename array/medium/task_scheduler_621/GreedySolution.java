package array.medium.task_scheduler_621;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    2.1 The idea is to sort processes by their occurrences
    2.2 Then, we need to get the max possible idle amount (first occurrence - 1) * n
    2.3 And for every remaining occurrence - subtract it from max possible idle (or subtract
        first occurrence - 1 if it is smaller (in case of similar occurrences))
    2.4 At the end, it could be negative or positive idle remaining. Validate it and return together
        with tasks length
 */

public class GreedySolution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            Integer counter = map.getOrDefault(ch, 0);
            map.put(ch, counter + 1);
        }

        List<Character> chars = new ArrayList<>(map.keySet());
        Collections.sort(chars, (a, b) -> {
            return map.get(b).compareTo(map.get(a));
        });

        int idles = (map.get(chars.get(0)) - 1) * n;
        for (int i = 1; i < chars.size(); i++) {
            idles -= Math.min(map.get(chars.get(0)) - 1, map.get(chars.get(i)));
        }
        idles = Math.max(idles, 0);


        return tasks.length + idles;
    }


    /**
         In other words, I need to find the way to organize processes to have the min distance
         between same processes N and minimize the possible length of the total list

         1. what should be the output?
         2. and I have array as input with [A-Z] possible values?


         [A,A,A] , 2 -> A, , ,A, , ,A (length + N*(length-1)) - the max,
         [A,B,C] , 2 -> A,B,C - the min is length
         [A,B,A,B], 2 -> A,B, ,A,B
         [A,A,B,B,C,C,D,D] , 1 -> A, ,A, ,B, ,B


         1. Count occurrences of every task, sort it
         2. Starting from the biggest - fill list with the values with interval


         a,b, ,a,b

         A,B,G,A,C, ,A,D, ,A,E, ,A,F, ,A
     */
}
