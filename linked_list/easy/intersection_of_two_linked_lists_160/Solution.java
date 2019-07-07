package linked_list.easy.intersection_of_two_linked_lists_160;

import java.util.HashSet;
import java.util.Set;

/**
 160. Intersection of Two Linked Lists
 https://leetcode.com/problems/intersection-of-two-linked-lists/

 Write a program to find the node at which the intersection of two singly linked lists begins.

 Example 1:
 Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 Output: Reference of the node with value = 8
 Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

 Example 2:
 Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 Output: Reference of the node with value = 2
 Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

 Example 3:
 Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 Output: null
 Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 Explanation: The two lists do not intersect, so return null.

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n+m) - where n and m is the length of lists
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The first solution is based on iterating both lists with two pointers. Every pointer iterates its list and
        then switches to the opposite. So it total number of steps will be the same and last common elements will be
        checked at the same time.
    2.2 The second solution checks lengths of both lists, then shifts the start point of longest one and compare
        remaining parts of both lists. The idea is that if they have similar part it will be at end end of the list.
    2.3 The last solution is most simple and based on hashset but it uses O(n) space.
    2.4 Implementation
        2.4.1 Check if any of lists are empty, then return null
        2.4.2 Assign pointerA to the head of A list and pointerB to the head of B
        2.4.3 Iterate both lists using pointers and switch pointerA to B and pointerB to A at the end
        2.4.4 If elements are equal, return one of the pointer
 */

class Solution {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;
        boolean switchA = false;
        boolean switchB = false;

        while (pointerA != null && pointerB != null) {
            if (pointerA == pointerB) {
                return pointerA;
            }
            if (pointerA.next != null) {
                pointerA = pointerA.next;
            } else if (!switchA) {
                pointerA = headB;
                switchA = true;
            } else {
                pointerA = null;
            }
            if (pointerB.next != null) {
                pointerB = pointerB.next;
            } else if (!switchB) {
                pointerB = headA;
                switchB = true;
            } else {
                pointerB = null;
            }
        }

        return null;
    }

//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null || headB == null) {
//            return null;
//        }
//
//        ListNode lastA = headA;
//        ListNode lastB = headB;
//        int lengthA = 1;
//        int lengthB = 1;
//        while (lastA.next != null) {
//            lastA = lastA.next;
//            lengthA++;
//        }
//        while (lastB.next != null) {
//            lastB = lastB.next;
//            lengthB++;
//        }
//        if (lastA != lastB) {
//            // lists do not intersect
//            return null;
//        }
//
//        while (lengthA > lengthB) {
//            headA = headA.next;
//            lengthA--;
//        }
//        while (lengthB > lengthA) {
//            headB = headB.next;
//            lengthB--;
//        }
//        while (headA != null && headB != null) {
//            if (headA == headB) {
//                return headA;
//            }
//            headA = headA.next;
//            headB = headB.next;
//        }
//        return null;
//    }

//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null || headB == null) {
//            return null;
//        }
//
//        Set<ListNode> set = new HashSet<>();
//        while (headA != null) {
//            set.add(headA);
//            headA = headA.next;
//        }
//
//        while (headB != null) {
//            if (set.contains(headB)) {
//                return headB;
//            }
//            headB = headB.next;
//        }
//        return null;
//    }
}
