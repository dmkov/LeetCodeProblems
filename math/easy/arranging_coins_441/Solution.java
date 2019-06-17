package math.easy.arranging_coins_441;

/**
 441. Arranging Coins
 https://leetcode.com/problems/arranging-coins/

 You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 Given n, find the total number of full staircase rows that can be formed.
 n is a non-negative integer and fits within the range of a 32-bit signed integer.

 Example 1:
 n = 5

 The coins can form the following rows:
 ¤
 ¤ ¤
 ¤ ¤
 Because the 3rd row is incomplete, we return 2.

 Example 2:
 n = 8

 The coins can form the following rows:
 ¤
 ¤ ¤
 ¤ ¤ ¤
 ¤ ¤
 Because the 4th row is incomplete, we return 3.

---

 1. Complexity
    1.1 Time Complexity is O(logn) because of binary search approach
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is based on formula for S = (A1 + An)*n/2. Because of n = An and A1 = 1, it will be S = (1 + n)*n/2
    2.2 Using binary search, check S in the range of values and find the the answer.

 */
public class Solution {

    public int arrangeCoins(int n) {
        int left = 0;
        int right = n;
        int mid = 0;
        long sum = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;
            sum =  (long) mid * (1 + mid) / 2;
            if (sum == n) {
                return mid;
            } else if (sum > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return (sum > n)?(right):(mid);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.arrangeCoins(1804289383));
    }

}
