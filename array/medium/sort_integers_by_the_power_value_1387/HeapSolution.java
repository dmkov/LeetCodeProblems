package array.medium.sort_integers_by_the_power_value_1387;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 1387. Sort Integers by The Power Value
 https://leetcode.com/problems/sort-integers-by-the-power-value/

 The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:

 if x is even then x = x / 2
 if x is odd then x = 3 * x + 1
 For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).

 Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.

 Return the k-th integer in the range [lo, hi] sorted by the power value.

 Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and that the power of x is will fit in 32 bit signed integer.

 Example 1:
 Input: lo = 12, hi = 15, k = 2
 Output: 13
 Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)
 The power of 13 is 9
 The power of 14 is 17
 The power of 15 is 17
 The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
 Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.

 Example 2:
 Input: lo = 1, hi = 1, k = 1
 Output: 1

 Example 3:
 Input: lo = 7, hi = 11, k = 4
 Output: 7
 Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
 The interval sorted by power is [8, 10, 11, 7, 9].
 The fourth number in the sorted array is 7.

 Example 4:
 Input: lo = 10, hi = 20, k = 5
 Output: 13

 Example 5:
 Input: lo = 1, hi = 1000, k = 777
 Output: 570

 Constraints:
 1 <= lo <= hi <= 1000
 1 <= k <= hi - lo + 1

 --------

 1. Complexity
    1.1 Time Complexity is O(n logk) for the heap
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to write a comparator with memoization and calculate power of each number in a separate function.
 3 Implementation
    3.1 Create a PriorityQueue with the comparator to get two Integers and calculate a power for them in a separate method.
    3.2 Compare powers and if they are the same - return the differences between numbers.
    3.3 To get the power, use a hash map with already calculated values. If number is not in the hash map - recursively
        call the method after number transformation and store result to the hash map.
    3.4 At the end poll() the required number of elements to get the k-th number.
 */

class HeapSolution {

    Map<Integer, Integer> cache = new HashMap<>();

    public int getKth(int lo, int hi, int k) {
        if (hi - lo + 1 < k) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> {
            Integer aRes = power(a);
            Integer bRes = power(b);

            if (aRes.equals(bRes)) {
                return a - b;
            }
            return aRes - bRes;
        });
        for (int i = lo; i <= hi; i++) {
            pq.add(Integer.valueOf(i));
        }

        while (k > 1) {
            pq.poll();
            k--;
        }

        return pq.poll();
    }

    private Integer power(Integer a) {
        if (cache.containsKey(a)) {
            return cache.get(a) - 1;
        }

        Integer res = 0;
        if (a % 2 == 0) {
            a = a / 2;
        } else {
            a = 3* a + 1;
        }

        if (a == 1) {
            res = 1;
        } else {
            res = power(a) + 1;
        }

        cache.put(a, res);
        return res;
    }

}
