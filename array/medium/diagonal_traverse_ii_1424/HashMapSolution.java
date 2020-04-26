package array.medium.diagonal_traverse_ii_1424;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 1424. Diagonal Traverse II
 https://leetcode.com/problems/diagonal-traverse-ii/

 Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.

 Example 1:
 Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 Output: [1,4,2,7,5,3,8,6,9]

 Example 2:
 Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

 Example 3:
 Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 Output: [1,4,2,5,3,8,6,9,7,10,11]

 Example 4:
 Input: nums = [[1,2,3,4,5,6]]
 Output: [1,2,3,4,5,6]

 --------

 1. Complexity
    1.1 Time Complexity is O(n) where n - is number of elements in the grid
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Element with the same sum of indexes are part of one diagonal. So we can traverse to collect groups and
        then we just need to get the right order
 */

public class HashMapSolution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null) {
            return new int[]{};
        }

        int size = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                List<Integer> res = map.getOrDefault(i+j, new ArrayList<>());
                res.add(row.get(j));
                map.put(i+j, res);
                size = Math.max(size, i+j);
            }
        }

        /**
         0:1
         1:2 4
         2:3 5 7
         3:6 8
         4:9
         */

        for (int i = 0; i <= size; i++) {
            List<Integer> row = map.get(i);
            if (row != null) {
                for (int j = row.size() - 1; j >= 0; j--) {
                    list.add(row.get(j));
                }
            }
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}
