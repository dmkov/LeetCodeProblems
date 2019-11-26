package tree.medium.unique_binary_search_trees_96;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 96. Unique Binary Search Trees
 https://leetcode.com/problems/unique-binary-search-trees/

 Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

 Example:
 Input: 3
 Output: 5
 Explanation:
 Given n = 3, there are a total of 5 unique BST's:

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3

 --------------

 1. Complexity
     1.1 Time Complexity is ~O(2n) (not sure about this)
     1.2 Space Complexity is O(n) - for the hashmap
 2. Approach
     2.1 This solution is based on the BST definition + dynamic programming approach
        Idea is that if we select each element as a root, we will have (i - 1) elements on the left and (n - i)
        on the right to create a BST subtree. We can check every case recursively and then multiple (get cartesian
        product) of both numbers.
     2.2 Implementation
         2.2.2 Define base cases for 0, 1 and 2 elements
         2.2.3 Iterate from 1 to n and recursively calculate result += numTrees(i - 1) * numTrees(n - i)
         2.2.4 Use memoziation to reduce the time complexity.

*/

class Solution {
    private Map<Integer, Integer> map = new HashMap<>();

    public int numTrees(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;

        Integer result = map.get(n);
        if (result == null) {
            result = 0;
            for (int i = 1; i <= n; i++) {
                result += numTrees(i - 1) * numTrees(n - i);
            }
            map.put(n, result);
        }

        return result;
    }
}
