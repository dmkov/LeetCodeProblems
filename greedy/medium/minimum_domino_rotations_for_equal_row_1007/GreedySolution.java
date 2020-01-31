package greedy.medium.minimum_domino_rotations_for_equal_row_1007;

/**
 1007. Minimum Domino Rotations For Equal Row
 https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/

 In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

 We may rotate the i-th domino, so that A[i] and B[i] swap values.
 Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

 If it cannot be done, return -1.

 Example 1:
 Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 Output: 2
 Explanation:
 The first figure represents the dominoes as given by A and B: before we do any rotations.
 If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.

 Example 2:
 Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 Output: -1
 Explanation:
 In this case, it is not possible to rotate the dominoes to make one row of values equal.

 Note:
 1 <= A[i], B[i] <= 6
 2 <= A.length == B.length <= 20000

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 For every a[i] there are two options: move it down if it is on the top or move to the top if it is on the bottom line.
     2.2 We do not need to move items if a[i] == b[i], otherwise count both possibilities
 3. Implementation
     3.1 Check if given arrays are valid
     3.1 Start two counter for the first element: 1) to move item down or 2) leave it on the top. Iterate through the array.
     3.2 If a[i] == prev (and b[i] is different) count it as a possibility to move down
     3.3 If b[i] == prev (and a[i] is different) count it as a possibility to move up
     3.3 Return min sum of movements and repeat it for the first element in b array.
 */

class GreedySolution {
    public int minDominoRotations(int[] a, int[] b) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length) {
            return -1;
        }

        int result = Math.min(rotations(a, b), rotations(b, a));
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private int rotations(int[] a, int[] b) {
        int down = 1, up = 0;
        int prev = a[0];
        for (int i = 1; i < a.length; i++) {
            if (prev == a[i] && prev != b[i]) {
                down++;
            } else if (prev != a[i] && prev == b[i]) {
                up++;
            } else if (prev != a[i] && prev != b[i]) {
                up = Integer.MAX_VALUE;
                down = Integer.MAX_VALUE;
                break;
            }
        }

        return Math.min(up, down);
    }
}
