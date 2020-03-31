package heap.hard.find_median_from_data_stream_295;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 295. Find Median from Data Stream
 https://leetcode.com/problems/find-median-from-data-stream/

 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3
 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:
 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.

 Example:
 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2

 Follow up:
 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

 --------

 1. Complexity
     1.1 Time Complexity is O(logn) for insert and O(1) for getting median
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to have two heaps in addition to two current median numbers. With every median request, we use saved
        numbers but when we need to insert we shift our median left or right and push extra numbers to the left or right heaps.
 3 Implementation
     3.1 In the constructor, create two heaps, one for descending order (left) and another one with the default sorting (right).
     3.2 For every insert consider three scenarios:
        3.2.1 If left number is empty (first insert) - just update it with the new number.
        3.2.2 The right number is empty (every even insert):
            3.2.2.1 If new number is less than left, we need to push new number to the left heap, reassign left number to the right
                and pop new amount from left heap into the left variable.
            3.2.2.2 Otherwise, add new element to the right heap and pop new value from heap to the right number.
        3.2.3 If both numbers are not empty (every odd insert):
            3.2.3.1 If new num is less than right, push right num to its heap, also push left number and new num to the left heap.
                Pop new value from the left heap to the left number (shift median to the left)
            3.2.3.2 Otherwise, add left number to the left heap, add new number to the the right heap. Than reassign right
                number to the left number (shift median to the right)
 */

class TwoHeapsSolution {

    Integer left;
    Integer right;

    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;

    /** initialize your data structure here. */
    public TwoHeapsSolution() {
        leftHeap = new PriorityQueue<>((a, b) -> b-a);
        rightHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left == null) {
            left = num;
        } else if (right == null) {
            if (num < left) {
                leftHeap.add(num);
                right = left;
                left = leftHeap.poll();
            } else {
                rightHeap.add(num);
                right = rightHeap.poll();
            }
        } else {

            if (num < right) {
                leftHeap.add(num);
                leftHeap.add(left);
                rightHeap.add(right);
                right = null;
                left = leftHeap.poll();
            } else {
                leftHeap.add(left);
                rightHeap.add(num);
                left = right;
                right = null;
            }

        }
    }

    public double findMedian() {
        int num = 0;
        double sum = 0;
        if (left != null) {
            sum += (double)left;
            num++;
        }
        if (right != null) {
            sum += (double)right;
            num++;
        }
        return sum / num;
    }

}
