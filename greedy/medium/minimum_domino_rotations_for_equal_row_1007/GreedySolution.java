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
     2.1 There are 2 possible cases: 1) selected number equals top element or 2) selected number equals bottom element.
        For the 1) case we just move to the next step. For 2) - we also increase counter.
     2.2 We can choose first top or bottom elements for the selected number. In the second case we rotate first domino and
        should increment counter from the start.
     2.3 Repeat same checking for the bottom list of numbers.
 3. Implementation
     3.1 Check if given arrays are valid
     3.2 Get the min of 4 possible cases:
        3.2.1 When A is the primary array, and selected element is A[0]
        3.2.2 When A is the primary array, and selected element is B[0] (means we swapped the first domino)
        3.2.3 When B is the primary array, and selected element is B[0]
        3.2.4 When B is the primary array, and selected element is A[0] (means we swapped the first domino)
     3.3 For every case in a separate method start iteration over the primary array:
        3.3.1 If selected == a[i] -> continue to the next step
        3.3.2 If selected == b[i] -> increment counter, continue to the next step
        3.3.2 If selected does not equal neither a[i] nor b[i] -> return Integer.MAX_VALUE
     3.4 Check for Integer.MAX_VALUE in the result and return -1 in this case.
 */

class GreedySolution {
    public int minDominoRotations(int[] a, int[] b) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || a.length != b.length) {
            return -1;
        }

        int res = Math.min(
                Math.min(check(a, b, a[0]), check(a, b, b[0])),
                Math.min(check(b, a, a[0]), check(b, a, b[0]))
        );

        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    private int check(int[] a, int[] b, int num) {
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            if (num == a[i]) {
                continue;
            } else if (num == b[i]) {
                counter++;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return counter;
    }
}
