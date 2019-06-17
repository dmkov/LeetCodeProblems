package sort.easy.sort_array_by_parity_ii_922;

import java.util.Arrays;

/**
 922. Sort Array By Parity II
 https://leetcode.com/problems/sort-array-by-parity-ii/

 Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 You may return any answer array that satisfies this condition.

 Example 1:
 Input: [4,2,5,7]
 Output: [4,5,2,7]
 Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

 Note:
 2 <= A.length <= 20000
 A.length % 2 == 0
 0 <= A[i] <= 1000

---

 1. Complexity
    1.1 Time Complexity is O(n) because of double loop
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 We check only even numbers and pointer for latest odd number
    2.2 Iterate through even indexes and if value is odd, then replace it with the last even number on odd position

 */
public class Solution {

    public int[] sortArrayByParityII(int[] A) {
        int k = -1;
        for (int i = 0; i < A.length; i+=2) {
            if (A[i] % 2 != 0) {
                while (k < A.length) {
                    k += 2;
                    if (A[k] % 2 == 0) {
                        int temp = A[k];
                        A[k] = A[i];
                        A[i] = temp;
                        break;
                    }
                }
            }
        }
        return A;
    }


//    public int[] sortArrayByParityII(int[] A) {
//        for (int i = 0; i < A.length; i++) {
//            if (i % 2 != A[i] % 2) {
//                for (int k = i + 1; k < A.length; k++) {
//                    if (i % 2 == A[k] % 2) {
//                        int temp = A[k];
//                        A[k] = A[i];
//                        A[i] = temp;
//                        break;
//                    }
//                }
//            }
//        }
//        return A;
//    }


}
