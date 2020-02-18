package stack.meduim.asteroid_collision_735;

import java.util.Stack;

/**
 735. Asteroid Collision
 https://leetcode.com/problems/asteroid-collision/

 We are given an array asteroids of integers representing asteroids in a row.

 For each asteroid, the absolute value represents its size, and the sign represents its direction
 (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

 Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 Example 1:
 Input:
 asteroids = [5, 10, -5]
 Output: [5, 10]
 Explanation:
 The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

 Example 2:
 Input:
 asteroids = [8, -8]
 Output: []
 Explanation:
 The 8 and -8 collide exploding each other.

 Example 3:
 Input:
 asteroids = [10, 2, -5]
 Output: [10]
 Explanation:
 The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

 Example 4:
 Input:
 asteroids = [-2, -1, 1, 2]
 Output: [-2, -1, 1, 2]
 Explanation:
 The -2 and -1 are moving left, while the 1 and 2 are moving right.
 Asteroids moving the same direction never meet, so no asteroids will meet each other.

 Note:
 The length of asteroids will be at most 10000.
 Each asteroid will be a non-zero integer in the range [-1000, 1000]..

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use stack to store every next element and compare it with previous ones. If the current element is less than 0
        and previous one is greater than 0, consider a collision case.
 3. Implementation
    3.1 Check if the input list is valid and can be processed
    3.2 Create a stack and start iterating the given list of asteroids
    3.3 For every number check if stack is not empty and if peek() element is positive and current item is negative
        (only in this case we might have a collision)
        3.3.1 If collision exists, pop the element and compare it to the current one.
        3.3.2 If both asteroids have same absolute value - assign 0 to the current element and break the loop
        3.3.3 If previous element is bugger than the current one in absolute - assign previous value to the current
            element and break the loop.
        3.3.4 Otherwise, continue checking.
    3.4 Reverse the remaining items in the stack and return them as an array.
 */

class StackSolution {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack<>();
        for (int num : asteroids) {
            while (stack.size() > 0 && (stack.peek() > 0 && num < 0)) {
                int prev = stack.pop();
                if (Math.abs(prev) == Math.abs(num)) {
                    num = 0;
                    break;
                } else if (Math.abs(prev) > Math.abs(num)) {
                    num = prev;
                    break;
                }
            }
            if (num != 0) {
                stack.push(num);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
