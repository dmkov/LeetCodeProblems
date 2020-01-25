package graph.medium.flower_planting_with_no_adjacent_1042;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 1042. Flower Planting With No Adjacent
 https://leetcode.com/problems/flower-planting-with-no-adjacent/

 You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

 paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

 Also, there is no garden that has more than 3 paths coming into or leaving it.
 Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

 Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 Example 1:
 Input: N = 3, paths = [[1,2],[2,3],[3,1]]
 Output: [1,2,3]

 Example 2:
 Input: N = 4, paths = [[1,2],[3,4]]
 Output: [1,2,1,2]

 Example 3:
 Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 Output: [1,2,3,4]

 Note:
 1 <= N <= 10000
 0 <= paths.size <= 20000
 No garden has 4 or more paths coming into or leaving it.
 It is guaranteed an answer exists.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Since each garden is connected to at most 3 gardens, there's always an available color for each garden.
        For example, if one garden is next to gardens with colors 1, 3, 4, then color #2 is available.
        So we just need to select a color not used for neighbor gardens.
 3. Implementation
     3.1 Check if given number of gardens is bigger than 0.
     3.1 Create a matrix with links, where i is index of node and value is a LinkedList with neighbors.
     3.2 Create a result array with colors and for every garden check what colors were already chosen. Use a 5-length
        array for this. At the end put into result the first vacant color for the current garden.
 */

class GreedySolution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        if (N < 1) {
            return new int[0];
        }

        List<Integer>[] matrix = new LinkedList[N];
        for (int[] path : paths) {
            if (matrix[path[0] - 1] == null) {
                matrix[path[0] - 1] = new LinkedList<>();
            }
            matrix[path[0] - 1].add(path[1] - 1);

            if (matrix[path[1] - 1] == null) {
                matrix[path[1] - 1] = new LinkedList<>();
            }
            matrix[path[1] - 1].add(path[0] - 1);
        }

        int[] available = new int[]{1, 2, 3, 4};
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int[] colors = new int[5];
            if (matrix[i] != null) {
                for (Integer garden : matrix[i]) {
                    colors[result[garden]] = 1;
                }
            }

            for (int color : available) {
                if (colors[color] != 1) {
                    result[i] = color;
                    break;
                }
            }
        }

        return result;
    }
}
