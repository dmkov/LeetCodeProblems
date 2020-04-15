package array.medium.random_pick_with_weight_528;

/**
 528. Random Pick with Weight
 https://leetcode.com/problems/random-pick-with-weight/

 Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

 Note:

 1 <= w.length <= 10000
 1 <= w[i] <= 10^5
 pickIndex will be called at most 10000 times.
 Example 1:

 Input:
 ["Solution","pickIndex"]
 [[[1]],[]]
 Output: [null,0]
 Example 2:

 Input:
 ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 [[[1,3]],[],[],[],[],[]]
 Output: [null,0,1,1,1,0]

 --------

 1. Complexity
    1.1 Time Complexity is O(n) for preprocessing and O(logn) for picking the element
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to work with intervals (or cumulative sums in simplified version). If number has weight 2,
        we spread values in [0:2) interval, the next element is 3 and it is converted to [2:5) interval
        (the right bound is exclusive).
    2.2 After we create our intervals and get the total sum, we can pick a random number from 0 to the total sum
        with every pick. Then we just need to find an interval containing this value. Use binary search
        since it is sorted
 */

public class IntervalSolution {
    private int total;
    private int[] sums;

    public IntervalSolution(int[] w) {
        sums = new int[w.length];

        int i = 0;
        for (int j = 0; j < w.length; j++) {
            i += w[j];
            sums[j] = i;
        }
        total = i;
    }

    public int pickIndex() {
        int val = (int)(Math.random() * total);
        int left = 0;
        int right = sums.length - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            int num = sums[mid];
            if (val < num && (mid == 0 || sums[mid-1] <= val)) {
                return mid;
            } else if (val < num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }
}
