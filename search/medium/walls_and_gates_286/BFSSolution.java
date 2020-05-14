package search.medium.walls_and_gates_286;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 286. Walls and Gates
 https://leetcode.com/problems/walls-and-gates/

 You are given a m x n 2D grid initialized with these three possible values.

 -1  - A wall or an obstacle.
  0  - A gate.
 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 Example:
 Given the 2D grid:
 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF
 After running your function, the 2D grid should be:

 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n*m) for queue
 2. Approach
    2.1 The idea is to do a level by level BFS for every gate and put new candidates to the queue again
 */

class BFSSolution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }

        Deque<int[]> q = new ArrayDeque();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    q.addLast(new int[]{i, j});
                }
            }
        }

        int level = 1;
        int[][] moves = new int[][]{
                {1,0}, {-1,0}, {0,1}, {0,-1}
        };
        while (q.size() > 0) {
            int bucket = q.size(); // 3
            for (int b = 0; b < bucket; b++) {
                int[] c = q.removeFirst();
                for (int[] m : moves) {
                    int row = m[0] + c[0];
                    int col = m[1] + c[1];
                    if (row >= 0 && row < rooms.length
                            && col >= 0 && col < rooms[row].length
                            && rooms[row][col] > level) {
                        rooms[row][col] = level;
                        q.addLast(new int[]{row, col});
                    }
                }
            }
            level++;
        }
    }


    /**
     1 0  0
     2 -1 1
     1 0  1

     q = 0,2  2,1 , 0,0, 1,2
     */
}


/**

 1. If n is a height and m is a width, we can say that n != m
 2. If there are two paths for the gate, we should put the smallest number of steps
 3. The distance is the number of steps that we should do?
 4. -1 is the wall, how do we encode our room?
 5. What should be the end result? Can I modify the given matrix?
 6. Can we move by diagonal? Can we move from one room to another?


     1  0  1  -1
     1  1  -1  1
     0  1  -1  0
     1  2  2  1



     1. In naive approach - take all gates and do a BFS traverse
        which is O(n*m*k) where K is the number of gates

     2. We can make it better by traversing level by level moving from all gates at one iteration
        O(n*m) time complexity and O(n) for the queue

     1. Iterate over matrix, collect all gates as start points to the queue
     2. While queue is not empty, traverse by level with counter
     2.1 If value of left/top/right/bottom cell is bigger than level, set level and add to queue


 */
