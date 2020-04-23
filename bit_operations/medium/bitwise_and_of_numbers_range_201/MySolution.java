package bit_operations.medium.bitwise_and_of_numbers_range_201;

/**
 505. The Maze II
 https://leetcode.com/problems/the-maze-ii/

 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1:
 Input 1: a maze represented by a 2D array
 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0
 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: 12

 Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

 Example 2:
 Input 1: a maze represented by a 2D array
 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0
 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: -1

 Explanation: There is no way for the ball to stop at the destination.

 Note:
 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 --------

 1. Complexity
    1.1 Time Complexity is O(1)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to find the largest bit in both numbers. It positions are different - we had shift on all bits
        and can return 0 as the result.
    2.2 If positions are the same, iterate to the right until you find a difference. Then, the rest of number - should be 0.
 */

public class MySolution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m > n) {
            return 0;
        }

        int first = largestBitPosition(m);
        int second = largestBitPosition(n);
        if (first == 0 || second == 0 || first != second) {
            return 0;
        }

        boolean found = false;
        int res = 0;
        for (int i = first - 1; i >= 0; i--) {
            int mask = 1 << i;
            if (!found && (m & mask) == (n & mask)) {
                res += m & mask;
            } else {
                found = true;
            }
        }

        return res;
    }

    private int largestBitPosition(int num) {
        int pos = 0;
        while (num > 0) {
            num = num >>> 1;
            pos++;
        }
        return pos;
    }

    /**
     1. What is the possible range of numbers? min/max?
     2. Negatives and zero are not allowed?
     3. What is the output format? int or string?


     0101 - 5
     0110 - 6
     0111 - 7
     --------
     0100 - 4

     1. Everything before larger bit in smaller number will give us 0.

     1000 - 8
     --------
     0000 - 0

     2. If larger num has more bits - result is 0.


     ----
     1. Get the largeset bit in both number. If they are different - return 0
     2. From found position to 0:
     if bids equal -> add them to result
     otherwise -> break and finish with 0s.

     */
}
