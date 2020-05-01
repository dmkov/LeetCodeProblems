package design.algorithms.traveling_salesman;

public class BacktrackingSolution {

    public int getCost(int[][] grid) {
        boolean[] visited = new boolean[grid.length];

        visited[0] = true;
        return dfs(grid, visited, 0, grid.length, 1);
    }

    private int dfs(int[][] grid, boolean[] visited, int pos, int steps, int step) {
        if (steps == step) {
            if (grid[pos][0] > 0) {
                return grid[pos][0];
            } else {
                return -1;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            if (!visited[i] && grid[pos][i] > 0) {
                visited[i] = true;

                int cost = dfs(grid, visited, i, steps, step + 1);
                if (cost != -1) {
                    res = Math.min(res, cost + grid[pos][i]);
                }

                visited[i] = false;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 15, 6},
                {2, 0, 7, 3},
                {9, 6, 0, 12},
                {10, 4, 8, 0}
        };

        BacktrackingSolution solution = new BacktrackingSolution();
        System.out.println(solution.getCost(grid));
    }

}
