package design.medium.exam_room_855;

import java.util.TreeSet;

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
     1.1 Time Complexity is O(n) for the seat and O(logn) the leave methods where is n - number of sitting students
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea st to keep TreeSet to have all occupied sits sorted. For every new seat() we check all stored
        elements and find the max distance to the next element.
     2.2 In the max interval we find the position point and return it as the result, adding to the tree set before.
     2.3 For deletion we just remove element from the tree set.
 */

class LinearSolution {

    TreeSet<Integer> set;
    int size;

    public LinearSolution(int N) {
        set = new TreeSet<>();
        size = N;
    }

    public int seat() {
        Integer seat = 0;

        if (set.size() == 0) {
            set.add(seat);
            return seat;
        }

        int max = 0;
        Integer prev = null;
        for (Integer student : set) {
            int dist;
            if (prev == null) {
                dist = student * 2;
                prev = 0 - student;
            } else {
                dist = student - prev;
            }

            if (dist/2 > max) {
                max = dist/2;
                seat = prev + dist/2;
            }
            prev = student;
        }

        if (!prev.equals(size - 1)) {
            int dist = size - 1 - prev;
            if (dist > max) {
                seat = size - 1;
            }
        }

        set.add(seat);

        return seat;
    }

    public void leave(int p) {
        set.remove(p);
    }

    public static void main(String[] args) {
        LinearSolution solution = new LinearSolution(10);
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

