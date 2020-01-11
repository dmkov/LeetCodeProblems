package hash_table.medium.prison_cells_after_n_days_957;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 957. Prison Cells After N Days
 https://leetcode.com/problems/prison-cells-after-n-days/

 There are 8 prison cells in a row, and each cell is either occupied or vacant.

 Each day, whether the cell is occupied or vacant changes according to the following rules:
 If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 Otherwise, it becomes vacant.
 (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

 We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 Example 1:
 Input: cells = [0,1,0,1,1,0,0,1], N = 7
 Output: [0,0,1,1,0,0,0,0]
 Explanation:
 The following table summarizes the state of the prison on each day:
 Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

 Example 2:
 Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 Output: [0,0,1,1,1,1,1,0]

 Note:
 cells.length == 8
 cells[i] is in {0, 1}
 1 <= N <= 10^9

 --------

 1. Complexity
    1.1 Time Complexity is O(2^N) where N is the number of cells in the prison.
    1.2 Space Complexity is O(2^N * N)
 2. Approach
    2.1 We iterate all combinations but store them into a hash map and when it starts to repeat, we skip all full cycles.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty.
    2.2.2 Save the current state to hashmap with days index (do Arrays.toString() method).
    2.2.3 Start iterating while days counter is greater than 0. For every step - count the prison state, decrement days counter
        and check if prison state was stored previously.
    2.2.4 If it is, take modulo of days and map index - days (it gives the size of the cycle) and assign it to days
        (so we have only remaining part to iterate after full cycles).
    2.2.4 Use boolean flag to do not enter cycle condition again. Return prison state after the end of the iteration.
 */

class IteratingWithPatternSolution {
    public int[] prisonAfterNDays(int[] cells, int days) {
        if (cells == null | cells.length == 0) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();
        map.put(Arrays.toString(cells), days);
        boolean stop = false;

        while (days > 0) {
            cells = prisonNextDay(cells);
            days--;

            Integer cycleStart = map.get(Arrays.toString(cells));
            if (cycleStart != null && !stop) {
                days = days % (cycleStart - days);
                stop = true;
            }
            map.put(Arrays.toString(cells), days);
        }

        return cells;
    }

    private int[] prisonNextDay(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            if (cells[i - 1] == cells[i + 1]) {
                next[i] = 1;
            }
        }
        return next;
    }
}
