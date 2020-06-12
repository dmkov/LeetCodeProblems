package dynamic.hard.number_of_ways_to_paint_n_3_grid_1411;

/**
 1411. Number of Ways to Paint N × 3 Grid
 https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/

 You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).

 You are given n the number of rows of the grid.

 Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.

 --------

 1. Complexity
    1.1 Time Complexity is O(n * 27^2)
    1.2 Space Complexity is O(n * 4^3)
 2. Approach
    https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574912/JavaC%2B%2B-DFS-Memoization-with-Picture-Clean-code
    2.1 It is a backtracking approach line by line with memoization in memo[n][a][b][c], where a, b and c - colors
    2.2 Also for every next row we pass colors selected colors on the previous level so we do not have two same colors
        in vertical neighborhs
 */

class BacktrackingSolution {

    public int numOfWays(int r) {
        if (r < 0) {
            return 0;
        }
        int[][][][] memo = new int[r][4][4][4];
        // use 1,2,3 for colors

        return backtracking(memo, 0, 0, 0, 0);
    }

    private int backtracking(int[][][][] memo, int n, int a0, int b0, int c0) {
        if (n == memo.length) {
            return 1;
        }
        if (memo[n][a0][b0][c0] != 0) {
            return memo[n][a0][b0][c0];
        }

        int sum = 0;
        int[] colors = new int[]{1, 2, 3};
        for (int a : colors) {
            if (a == a0) {
                continue;
            }
            for (int b : colors) {
                if (b == b0 || b == a) {
                    continue;
                }
                for (int c : colors) {
                    if (c == c0 || c == b) {
                        continue;
                    }
                    sum += backtracking(memo, n + 1, a, b, c);
                    sum %= 1_000_000_007;
                }
            }
        }

        memo[n][a0][b0][c0] = sum;
        return sum;
    }
}
