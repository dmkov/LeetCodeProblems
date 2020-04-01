package design.medium.design_hit_counter_362;

import java.util.ArrayList;
import java.util.List;

/**
 362. Design Hit Counter
 https://leetcode.com/problems/design-hit-counter/

 Design a hit counter which counts the number of hits received in the past 5 minutes.

 Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

 It is possible that several hits arrive roughly at the same time.

 Example:
 HitCounter counter = new HitCounter();
 // hit at timestamp 1.
 counter.hit(1);
 // hit at timestamp 2.
 counter.hit(2);
 // hit at timestamp 3.
 counter.hit(3);
 // get hits at timestamp 4, should return 3.
 counter.getHits(4);
 // hit at timestamp 300.
 counter.hit(300);
 // get hits at timestamp 300, should return 4.
 counter.getHits(300);
 // get hits at timestamp 301, should return 3.
 counter.getHits(301);

 Follow up:
 What if the number of hits per second could be very large? Does your design scale?

 --------

 1. Complexity
    1.1 Time Complexity is O(1) for storing and O(logn) for searching and O(n) for copying items
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to add timestamp to the ArrayList for the every hit
    2.2 For every request we do the binary search to find the start point according to the array and then copy the next
        half of the list as a new list. Return the size of the new list.
    2.3 The copying part will take O(n). As alternative we can use Queue to poll() items until we reach the required timestamp.
    2.4 We can change rate and do not call this erasing every time.
 */

class MyBinarySearchSolution {

    List<Integer> list;

    /** Initialize your data structure here. */
    public MyBinarySearchSolution() {
        list = new ArrayList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        list.add(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int pos = getFirstOccur(timestamp - 300);
        if (pos == -1) {
            list = new ArrayList<>();
        } else {
            list = list.subList(pos, list.size());
        }

        return list.size();
    }

    private int getFirstOccur(int t) {
        int left = 0;
        int right = list.size() - 1;
        int pos = -1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (list.get(mid) > t) {
                pos = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
