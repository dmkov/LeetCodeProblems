package stack.easy.min_stack_155;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.w3c.dom.Node;

/**
 155. Min Stack
 https://leetcode.com/problems/min-stack/

 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.

 ---


 1. Complexity
    1.1 Time Complexity is O(1) for all operations
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on designing new class with value and min value for every node.
    2.2 Implementation
        2.2.1 For push() check if current value is greater or not with the previous minimum and create a new node
        2.2.2 For pop(), peek() and min() work as with usual stack
 */

class MinStack {
    class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    private Node head = null;

    public MinStack() {}

    public void push(int x) {
        int min = x;
        if (head != null) {
            min = Math.min(head.min, x);
        }

        Node oldHead = head;
        head = new Node(x, min);
        head.next = oldHead;
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        if (head != null) {
            return head.val;
        }
        return -1;
    }

    public int getMin() {
        if (head != null) {
            return head.min;
        }
        return -1;
    }
}

// Another good solution:

//class MinStack {
//    int min = Integer.MAX_VALUE;
//    Stack<Integer> stack = new Stack<Integer>();
//    public void push(int x) {
//        // only push the old minimum value when the current
//        // minimum value changes after pushing the new value x
//        if(x <= min){
//            stack.push(min);
//            min=x;
//        }
//        stack.push(x);
//    }
//
//    public void pop() {
//        // if pop operation could result in the changing of the current minimum value,
//        // pop twice and change the current minimum value to the last minimum value.
//        if(stack.pop() == min) min=stack.pop();
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return min;
//    }
//}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
