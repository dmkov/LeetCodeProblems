package stack.meduim.pour_water_755;

import java.util.Stack;

/**
 755. Pour Water
 https://leetcode.com/problems/pour-water/

 We are given an elevation map, heights[i] representing the height of the terrain at that index.
 The width at each index is 1. After V units of water fall at index K, how much water is at each index?

 Water first drops at index K and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:
    - If the droplet would eventually fall by moving left, then move left.
    - Otherwise, if the droplet would eventually fall by moving right, then move right.
    - Otherwise, rise at it's current position.
 Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction. Also, "level" means the height of the terrain plus any water in that column.
 We can assume there's infinitely high terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly on more than 1 grid block - each unit of water has to be in exactly one block.

 Example 1:
 Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
 Output: [2,2,2,3,2,2,2]
 Explanation:
 #       #
 #       #
 ##  # ###
 #########
 0123456    <- index

 The first drop of water lands at index K = 3:

 #       #
 #   w   #
 ##  # ###
 #########
 0123456

 When moving left or right, the water can only move to the same level or a lower level.
 (By level, we mean the total height of the terrain plus any water in that column.)
 Since moving left will eventually make it fall, it moves left.
 (A droplet "made to fall" means go to a lower height than it was at previously.)

 #       #
 #       #
 ## w# ###
 #########
 0123456

 Since moving left will not make it fall, it stays in place.  The next droplet falls:

 #       #
 #   w   #
 ## w# ###
 #########
 0123456

 Since the new droplet moving left will eventually make it fall, it moves left.
 Notice that the droplet still preferred to move left,
 even though it could move right (and moving right makes it fall quicker.)

 #       #
 #  w    #
 ## w# ###
 #########
 0123456

 #       #
 #       #
 ##ww# ###
 #########
 0123456

 After those steps, the third droplet falls.
 Since moving left would not eventually make it fall, it tries to move right.
 Since moving right would eventually make it fall, it moves right.

 #       #
 #   w   #
 ##ww# ###
 #########
 0123456

 #       #
 #       #
 ##ww#w###
 #########
 0123456

 Finally, the fourth droplet falls.
 Since moving left would not eventually make it fall, it tries to move right.
 Since moving right would not eventually make it fall, it stays in place:

 #       #
 #   w   #
 ##ww#w###
 #########
 0123456

 The final answer is [2,2,2,3,2,2,2]:

 #
 #######
 #######
 0123456

 Example 2:
 Input: heights = [1,2,3,4], V = 2, K = 2
 Output: [2,3,3,4]
 Explanation:
 The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.

 Example 3:
 Input: heights = [3,1,3], V = 5, K = 1
 Output: [4,4,4]

---

 1. Complexity
    1.1 Time Complexity is O(V*n) with amortization because of using stacks. The best scenario is O(V+n).
        However, there is O(V+n) guaranteed solution exists - https://leetcode.com/problems/pour-water/discuss/113003/C%2B%2BJavaPython-O(V%2BN)-time-solution-using-2-pointers-and-2-stacks
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 We keep in left and right stack points smaller than K (decreasing stacks).
    2.2 With every push we check if we can extend our stack further.
    2.3 The idea is to keep tracking of points for the next inserts. However, we just reduce the number of checking -
        the overall time complexity remains O(V*n)
 3. Implementation
    3.1 Check if the input array and integers are valid
    3.2 Create left and right stacks. Put there items smaller than K on the left and right sides of the array.
        Be sure that you check all <= elements, but put into stack only with smaller condition.
    3.3 For every drop of water (while loop):
        3.3.1 Check if left stack is not empty, poll the item, update element in the array and check
            if stack can be extended
        3.3.2 If left stack is empty, check the same with the right stack.
        3.3.3 Otherwise, update K-th element and re-check left and right stacks then.
 */

class StackSolution {
    public int[] pourWater(int[] arr, int V, int K) {
        if (arr == null || arr.length == 0 || K > arr.length - 1) {
            return new int[]{};
        }

        // 0 - pos
        // 1 - val
        Stack<Integer> left = new Stack<>();
        checkLeft(K - 1, arr, left);
        Stack<Integer> right = new Stack<>();
        checkRight(K + 1, arr, right);

        while (V > 0) {
            if (left.size() > 0) {
                Integer indx = left.pop();
                arr[indx]++;
                checkLeft(indx, arr, left);
            } else if (right.size() > 0) {
                Integer indx = right.pop();
                arr[indx]++;
                checkRight(indx, arr, right);
            } else {
                arr[K]++;
                checkLeft(K - 1, arr, left);
                checkRight(K + 1, arr, right);
            }
            V--;
        }

        // [1,2,1,1], V = 5, K = 2

        return arr;
    }

    private void checkLeft(int l, int[] arr, Stack<Integer> left) {
        while (l >= 0 && arr[l] <= arr[l + 1]) {
            if (arr[l] < arr[l + 1]) {
                left.push(l);
            }
            l--;
        }
    }

    private void checkRight(int r, int[] arr, Stack<Integer> right) {
        while (r <= arr.length - 1 && arr[r] <= arr[r - 1]) {
            if (arr[r] < arr[r - 1]) {
                right.push(r);
            }
            r++;
        }
    }
}
