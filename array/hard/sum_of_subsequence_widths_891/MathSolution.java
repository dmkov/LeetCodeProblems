package array.hard.sum_of_subsequence_widths_891;

import java.util.Arrays;

/**
 891. Sum of Subsequence Widths
 https://leetcode.com/problems/sum-of-subsequence-widths/

 Given an array of integers A, consider all non-empty subsequences of A.
 For any sequence S, let the width of S be the difference between the maximum and minimum element of S.

 Return the sum of the widths of all subsequences of A.
 As the answer may be very large, return the answer modulo 10^9 + 7.

 Example 1:
 Input: [2,1,3]
 Output: 6
 Explanation:
 Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 The sum of these widths is 6.

 Note:
 1 <= A.length <= 20000
 1 <= A[i] <= 20000

 --------

 https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/C%2B%2BJava1-line-Python-Sort-and-One-Pass

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 First, we need to sort array.
     2.2 For each number A[i]:
        2.2.1 There are i smaller numbers, so there are 2 ^ i sequences in which A[i] is maximum.
            we should do res += A[i] * (2 ^ i)
        2.2.2 There are n - i - 1 bigger numbers, so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.
            we should do res -= A[i] * (2 ^ (n - i - 1))
 3. Implementation
     3.1 Check if the given array is valid. Sort it.
     3.2 Populate long values with powers of 2 for every element in the index
     3.3 For every element:
        3.3.1 Add previous sum
        3.3.2 Add arr[i] * pow[i]
        3.3.3 Subtract arr[i] * pow[arr.length - i - 1]
        3.3.4 Get modulo of the result
     3.4 At the end add modulo value to the sum and get modulo from the number (to make it positive).
 */

class MathSolution {

    private final static int MOD = 1_000_000_007;

    public int sumSubseqWidths(int[] arr) {
        int sum = 0;
        if (arr == null || arr.length == 0) {
            return sum;
        }

        Arrays.sort(arr);

        long[] pow = new long[arr.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = pow[i - 1] * 2 % MOD;
        }

        for (int i = 0; i < arr.length; i++) {
            sum = (int)((sum + arr[i] * pow[i] - arr[i] * pow[arr.length - i - 1]) % MOD);
        }

        return (int)((sum + MOD) % MOD);
    }
}
