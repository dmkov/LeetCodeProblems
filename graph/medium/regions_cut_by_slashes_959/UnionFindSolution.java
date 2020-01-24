package graph.medium.regions_cut_by_slashes_959;

import java.util.HashSet;
import java.util.Set;

/**
 959. Regions Cut By Slashes
 https://leetcode.com/problems/regions-cut-by-slashes/

 In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
 (Note that backslash characters are escaped, so a \ is represented as "\\".)

 Return the number of regions.

 Example 1:
 Input:
 [
    " /",
    "/ "
 ]
 Output: 2

 Example 2:
 Input:
 [
     " /",
     "  "
 ]
 Output: 1

 Example 3:
 Input:
 [
     "\\/",
     "/\\"
 ]
 Output: 4

 Example 4:
 Input:
 [
     "/\\",
     "\\/"
 ]
 Output: 5

 Example 5:
 Input:
 [
     "//",
     "/ "
 ]
 Output: 3

 Note:
 1 <= grid.length == grid[0].length <= 30
 grid[i][j] is either '/', '\', or ' '.

 --------

 https://www.youtube.com/watch?v=tIZkh7mpIDo

 1. Complexity
     1.1 Time Complexity is O(n^2)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 Idea is to split every character (cell) into 4 cells-triangles and numerate them.
     2.2 After that use Union Find approach to link top, bottom, left and right triangles between each other
        and neighbour cells. At the end, count unique parents and it will be the number of areas.
 3. Implementation
     3.1 Check if given array is valid
     3.1 Create UnionFind instance with the 2*n*n capacity. Iterate all strings and characters in them.
     3.2 Calculate the root triangle as sum of all previous rows and left squares. Depending on the character,
        link two or four triangles in the square
     3.3 Then check and link four neighbours.
     3.4 At the end count how many unique parents in UnionFind instance and return it as the result
 */

class UnionFindSolution {
    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int n = grid.length;
        UF uf = new UF(4 * n * n);

        for (int i = 0; i < n; i++) {
            String s = grid[i];
            for (int j = 0; j < n; j++) {
                int root = 4 * n * i + 4 * j;
                if (s.charAt(j) != '/') {
                    uf.union(root, root + 2);
                    uf.union(root + 1, root + 3);
                }
                if (s.charAt(j) != '\\') {
                    uf.union(root, root + 1);
                    uf.union(root + 2, root + 3);
                }
                if (j > 0) {
                    uf.union(root + 1, root - 2);
                }
                if (j < n - 1) {
                    uf.union(root + 2, root + 5);
                }
                if (i > 0) {
                    uf.union(root, root - 4 * n + 3);
                }
                if (i < n - 1) {
                    uf.union(root + 3, root + 4 * n);
                }
            }
        }

        Set<Integer> parents = new HashSet<>();
        for (int i = 0; i < 4 * n * n; i++) {
            parents.add(uf.find(i));
        }

        return parents.size();
    }

    class UF {
        private int[] parent;

        public UF(int capacity) {
            parent = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                parent[i] = i;
            }
        }

        public void union(int i, int j) {
            parent[find(i)] = find(j);
        }

        public int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }
    }
}
