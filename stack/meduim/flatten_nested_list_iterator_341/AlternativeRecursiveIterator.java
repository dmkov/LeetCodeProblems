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
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n) - it creates new AlternativeRecursiveIterator for every level
 2. Approach
    2.1 This is alternative solution with recursive NestedIterator. It is more elegant but it does not check what
        value is in the list. So for [[1],[]] it returns [1,null] since the second element exists.
        But by definition it should be [1] and empty list should be just skipped from processing.
    2.2 The idea of the approach is to create another NestedIterator for every inner level. So we work with the current
        list and always check innerList before.
    2.3 For the hasNext() checks list.hasNext() if innerList is null or both list.hasNext() and innerList.hasNext()
        in other case.
    2.4 For next() method checks that innerList exists and if innerList.hasNext(), return innerList.next().
        Otherwise, take the item from the list. If it an integer - return the value.
        If it is the list - create NestedIterator and return the next element from it.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AlternativeRecursiveIterator implements Iterator<Integer> {

    private Iterator<NestedInteger> list;
    private AlternativeRecursiveIterator innerList;

    public AlternativeRecursiveIterator(List<NestedInteger> nestedList) {
        this.list = nestedList.iterator();
    }

    @Override
    public Integer next() {
        if (innerList != null && innerList.hasNext()) {
            return innerList.next();
        }
        innerList = null;

        if (list.hasNext()) {
            NestedInteger item = list.next();
            if (item.isInteger()) {
                return item.getInteger();
            } else {
                innerList = new AlternativeRecursiveIterator(item.getList());
                return innerList.next();
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        boolean result = list.hasNext();
        if (innerList != null) {
            result = result || innerList.hasNext();
        }

        return result;
    }
}

/**
 * Your MainStackIterator object will be instantiated and called as such:
 * MainStackIterator i = new MainStackIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
