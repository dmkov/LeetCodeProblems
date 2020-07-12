package array.medium.sparse_matrix_multiplication_311;

import java.util.ArrayList;
import java.util.List;

/**
 311. Sparse Matrix Multiplication
 https://leetcode.com/problems/sparse-matrix-multiplication/

 Given two sparse matrices A and B, return the result of AB.
 You may assume that A's column number is equal to B's row number.

 Example:
 Input:
 A = [
   [ 1, 0, 0],
   [-1, 0, 3]
 ]

 B = [
   [ 7, 0, 0 ],
   [ 0, 0, 0 ],
   [ 0, 0, 1 ]
 ]

 Output:

      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
      |  0 0 1 |

 --------

 1. Complexity
     1.1 Time Complexity is O(i * j * h)
     1.2 Space Complexity is O(n), n is the total elements
 2. Approach
     2.1 Compress the matrixes (one or both) to have
 */

class Solution {

  public int[][] multiply(int[][] a, int[][] b) {
    if (a == null || b == null || a.length == 0 || b.length == 0 || a[0].length != b.length) {
      throw new IllegalArgumentException();
    }
    int[][] res = new int[a.length][b[0].length];

    List<int[]>[] aRows = new List[a.length];
    for (int i = 0; i < a.length; i++) {
      List<int[]> list = new ArrayList<>();
      for (int j = 0; j < a[i].length; j++) {
        if (a[i][j] != 0) {
          list.add(new int[]{j, a[i][j]});
        }
      }
      aRows[i] = list;
    }

    List<int[]>[] bCols = new List[b[0].length];
    for (int i = 0; i < b[0].length; i++) {
      List<int[]> list = new ArrayList<>();
      for (int j = 0; j < b.length; j++) {
        if (b[j][i] != 0) {
          list.add(new int[]{j, b[j][i]});
        }
      }
      bCols[i] = list;
    }

    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[i].length; j++) {
        res[i][j] = computeProduct(aRows[i], bCols[j]);
      }
    }
    return res;
  }

  private int computeProduct(List<int[]> aRows, List<int[]> bCols) {
    int res = 0;
    int i = 0, j = 0;
    while (i < aRows.size() && j < bCols.size()) {
      if (aRows.get(i)[0] < bCols.get(j)[0]) {
        i++;
      } else if (bCols.get(j)[0] < aRows.get(i)[0]) {
        j++;
      } else {
        res += bCols.get(j)[1] * aRows.get(i)[1];
        i++;
        j++;
      }
    }
    return res;
  }


  /**
   - We can multiple matrices only if A[0].length == B.length
   - The size of result matrix is [A.length][B[0].length]
   - for every result[i][j] we need to multiple all numbers from A[i] row with B[0..n][j] column
   in other words result[i][j] = sum of A[i][0..n] * B[0..n][j]


   */
}
