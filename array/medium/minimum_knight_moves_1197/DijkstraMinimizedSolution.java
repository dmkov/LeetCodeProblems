package array.medium.minimum_knight_moves_1197;

import java.util.PriorityQueue;

/**
 1197. Minimum Knight Moves
 https://leetcode.com/problems/minimum-knight-moves/

 In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

 A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

 Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

 Example 1:
 Input: x = 2, y = 1
 Output: 1
 Explanation: [0, 0] → [2, 1]

 Example 2:
 Input: x = 5, y = 5
 Output: 4
 Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Since we have restrictions on max possible X and Y we can do a BFS or Dijkstra search to find the min path to the point.
    2.2 Another great thing is that all movements are symmetric. So we can ignore negative coordinates and track only positive ones.
 */

public class DijkstraMinimizedSolution {
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);
        int[][] visited = new int[310][310];

        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> visited[a[0]][a[1]] - visited[b[0]][b[1]]);
        heap.add(new int[]{0, 0});

        int[][] moves = {
                {1, 2}, {1, -2}, {2, 1}, {2, -1},
                {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}
        };
        while (heap.size() > 0) {
            int[] point = heap.poll();
            for (int[] move : moves) {
                int[] next = { Math.abs(point[0] + move[0]), Math.abs(point[1] + move[1]) };
                if (visited[next[0]][next[1]] == 0
                        || visited[next[0]][next[1]] > visited[point[0]][point[1]] + 1) {
                    visited[next[0]][next[1]] = visited[point[0]][point[1]] + 1;
                    heap.add(next);
                }
                if (next[0] == x && next[1] == y) {
                    return visited[next[0]][next[1]];
                }
            }
        }
        return visited[x][y];
    }
}
