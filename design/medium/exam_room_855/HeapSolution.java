package design.medium.exam_room_855;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 855. Exam Room
 https://leetcode.com/problems/exam-room/

 In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

 When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

 Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 Example 1:
 Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 Output: [null,0,9,4,2,null,5]
 Explanation:
 ExamRoom(10) -> null
 seat() -> 0, no one is in the room, then the student sits at seat number 0.
 seat() -> 9, the student sits at the last seat number 9.
 seat() -> 4, the student sits at the last seat number 4.
 seat() -> 2, the student sits at the last seat number 2.
 leave(4) -> null
 seat() -> 5, the student sits at the last seat number 5.
 ​​​​​​​
 Note:
 1 <= N <= 10^9
 ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

 --------

 1. Complexity
     1.1 Time Complexity is O(n logk) for the seat and O(1) for the leave methods
     1.2 Space Complexity is O(n) for the heap and additional hash maps
 2. Approach
     2.1 The idea behind the solution is keep all available intervals in the max heap. With every seat() call we will
        get the next best interval and define a point in it, split available space and store as two other possible intervals.
     2.2 The trick part is due to the fact how we handle first and last positions - we double interval lengths if
        it is the case.
     2.3 To do not search intervals in Queue with O(n) complexity when the seat is removed, we use additional hash maps to
        get intervals before and after the current position.
 */

class HeapSolution {

    class Interval {
        int start;
        int end;
        int totalSize;
        boolean removed;

        public Interval(int start, int end, int totalSize) {
            this.start = start;
            this.end = end;
            this.totalSize = totalSize;
        }

        public int getLength() {
            int length = (end - start) / 2;
            if (start == 0 || end == totalSize - 1) {
                length = (end - start);
            }
            return length;
        }

        public int getPoint(boolean f, boolean l) {
            if (!f && start == 0) {
                return 0;
            } else if (!l && end == totalSize - 1) {
                return totalSize - 1;
            } else {
                int pos = (end - start) / 2;
                return start + pos;
            }
        }
    }

    int size;
    boolean first, last;
    PriorityQueue<Interval> pq;
    Map<Integer, Interval> before;
    Map<Integer, Interval> after;

    public HeapSolution(int N) {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.getLength() == b.getLength()) {
                return a.start- b.start;
            } else {
                return b.getLength() - a.getLength();
            }
        });

        first = false;
        last = false;
        size = N;
        before = new HashMap<>();
        after = new HashMap<>();

        add(new Interval(0, size-1, size));
    }

    private void add(Interval i) {
        pq.add(i);
        before.put(i.end, i);
        after.put(i.start, i);
    }

    public int seat() {
        Interval i = pq.poll();
        while (i.removed) {
            i = pq.poll();
        }
        removeFromHashMaps(i);

        int pos = i.getPoint(first, last);
        if (pos > i.start) {
            add(new Interval(i.start, pos - 1, size));
        }
        if (pos < i.end) {
            add(new Interval(pos + 1, i.end, size));
        }

        if (pos == 0) {
            first = true;
        }
        if (pos == size - 1) {
            last = true;
        }

        return pos;
    }

    public void leave(int p) {
        Interval i = new Interval(p, p, size);
        if (p > 0 && before.containsKey(p - 1)) {
            Interval pre = before.get(p - 1);
            i.start = pre.start;
            removeFromHashMaps(pre);
        }
        if (p < size - 1 && after.containsKey(p + 1)) {
            Interval post = after.get(p + 1);
            i.end = post.end;
            removeFromHashMaps(post);
        }
        add(i);

        if (p == 0) {
            first = false;
        }
        if (p == size - 1) {
            last = false;
        }
    }

    private void removeFromHashMaps(Interval i) {
        i.removed = true;
        before.remove(i.end);
        after.remove(i.start);
    }

    public static void main(String[] args) {
        HeapSolution solution = new HeapSolution(10);
//        System.out.println("Seat (0): " + solution.seat());
//        System.out.println("Seat (9): " + solution.seat());
//        System.out.println("Seat (4): " + solution.seat());
//        System.out.println("Seat (2): " + solution.seat());
//        solution.leave(4);
//        System.out.println("Seat (5): " + solution.seat());


        System.out.println("Seat (0): " + solution.seat());
        System.out.println("Seat (9): " + solution.seat());
        System.out.println("Seat (4): " + solution.seat());
        solution.leave(0);
        solution.leave(4);
        System.out.println("Seat (0): " + solution.seat());
    }
}

