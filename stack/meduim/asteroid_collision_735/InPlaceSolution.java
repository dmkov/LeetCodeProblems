package stack.meduim.asteroid_collision_735;

import java.util.Arrays;
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
    2.1 Instead of using stack, use two pointers: 1) for the current element and 2) for the place in the array where current
        number can be shifted. Consider case of collision only if the current element is less than 0 and previous one is greater
        than 0. Otherwise, just copy elements and move the pointers.
 3. Implementation
    3.1 Check if the input list is valid and can be processed
    3.2 Point end variable to the last element and start iteration from the second number.
    3.3 Inside the loop if end is -1 (exceptional case when we remove all items) - just set end to 0 and
        copy the i-th element to it.
    3.4 If end element < 0 or both end and i-th elements are > 0 (no possible collision) -
        increment end and copy i-th element to it.
    3.5 If collision exists (end element > 0 and i-th < 0), consider possible options:
        3.5.1 Absolute value of both asteroids are the same - decrease end and do not copy anything.
        3.5.2 Absolute value of end element is less than i-th - we need to shift i-th to the end position. The
            tricky part here is that we need to start while loop and check all previous position for the new copied value.
    3.6 Return a copy of the array from 0 to the end+1 position exclusively.
 */

class InPlaceSolution {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }

        int end = 0;
        for (int i = 1; i < asteroids.length; i++) {
            if (end == -1) {
                end = 0;
                asteroids[0] = asteroids[i];
            } else {
                if (asteroids[end] < 0 || (asteroids[end] > 0 && asteroids[i] > 0)) {
                    end++;
                    asteroids[end] = asteroids[i];
                } else if (asteroids[end] > 0 && asteroids[i] < 0) {

                    if (Math.abs(asteroids[end]) == Math.abs(asteroids[i])) {
                        end--;
                    } else if (Math.abs(asteroids[end]) < Math.abs(asteroids[i])) {
                        asteroids[end] = asteroids[i];

                        while (end > 0 && asteroids[end - 1] > 0 && asteroids[end] < 0) {
                            if (Math.abs(asteroids[end - 1]) < Math.abs(asteroids[end])) {
                                asteroids[end - 1] = asteroids[end];
                            } else if (Math.abs(asteroids[end - 1]) == Math.abs(asteroids[end])) {
                                end--;
                            }
                            end--;
                        }

                    }
                }
            }
        }

        return Arrays.copyOfRange(asteroids, 0, end + 1);
    }
}
