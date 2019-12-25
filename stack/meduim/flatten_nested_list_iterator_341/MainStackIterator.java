package stack.meduim.flatten_nested_list_iterator_341;

/**
 341. Flatten Nested List Iterator
 https://leetcode.com/problems/flatten-nested-list-iterator/

 Given a nested list of integers, implement an iterator to flatten it.
 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Input: [[1,1],2,[1,1]]
 Output: [1,1,2,1,1]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Input: [1,[4,[6]]]
 Output: [1,4,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,4,6].

---

 1. Complexity
    1.1 Time Complexity is O(n^2). For every pushToStack we call size() and get (which is 2N in LinkedList) and have
        another loop that gives us O(n^2) (or O(n) is there is ArrayList)
    1.2 Space Complexity is O(n) because of additional stack
 2. Approach
    2.1 This solution is based on using additional stack. Every level is pushed to the stack at the beginning and
        if we have another inner list, it is also populated to the stack from end to start element. So to get next
        element we always pop() item from the stack.
    2.2 For the hasNext() just check that !stack.isEmpty()
    2.3 For next() get last item from the stack to return. Check also the next element and if it is a list, then
        explode it to the stack (for future step).
    2.4 The trick here is that we always populate stack for the next step. When we create an iterator - we push
        the first level to the stack. With every pop() action we check the next element and populate it (if there is
        a list) to the stack as well.
    2.5 Another trick is to check first item after populating stack. So, checking and exploding in advance helps
        to exclude empty lists and returns [1] for [[1],[]] instead of [1,null]. And we paid extra O(n) for this.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MainStackIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack = new Stack<>();

    public MainStackIterator(List<NestedInteger> list) {
        pushToStack(list);
    }

    @Override
    public Integer next() {
        Integer item = stack.pop().getInteger();
        if (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushToStack(stack.pop().getList());
        }
        return item;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushToStack(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }

        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushToStack(stack.pop().getList());
        }
    }
}

/**
 * Your MainStackIterator object will be instantiated and called as such:
 * MainStackIterator i = new MainStackIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
