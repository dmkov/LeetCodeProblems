package stack.easy.implement_queue_using_stacks_232;

import java.util.Stack;

/**
 232. Implement Queue using Stacks
 https://leetcode.com/problems/implement-queue-using-stacks/

 Implement the following operations of a queue using stacks.
 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.

 Example:
 MyQueue queue = new MyQueue();
 queue.push(1);
 queue.push(2);
 queue.peek();  // returns 1
 queue.pop();   // returns 1
 queue.empty(); // returns false

 Notes:
 You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size,
 and is empty operations are valid.
 Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or
 deque (double-ended queue), as long as you use only standard operations of a stack.
 You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

 ---


 1. Complexity
    1.1 Time Complexity is O(1) for push and O(n) for pop when pop stack is empty and O(1) if not (amortized complexity)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on two stacks.
    2.2 Implementation
        2.2.1 For push() just add elements to the first stack
        2.2.2 For pop() and peak() check if the second stack is not empty
        2.2.3 If there are items - pop() or peak() the last one
        2.2.4 Otherwise, if the second stack is empty - move all items from the first stack to it and then
            do pop()/peak() operation. So the last element will be the first and vice versa after the moving.
 */
class MyQueue {
    Stack<Integer> pop = new Stack<>();
    Stack<Integer> push = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {}

    /** Push element x to the back of queue. */
    public void push(int x) {
        push.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (pop.isEmpty()) {
            moveItemsFromStack(push, pop);
        }
        return pop.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (pop.isEmpty()) {
            moveItemsFromStack(push, pop);
        }
        return pop.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return !(push.size() > 0 || pop.size() > 0);
    }

    private void moveItemsFromStack(Stack<Integer> from, Stack<Integer> to) {
        if (from.size() > 0) {
            while (from.size() > 0) {
                to.push(from.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
