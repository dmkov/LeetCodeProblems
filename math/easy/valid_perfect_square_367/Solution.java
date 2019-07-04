package math.easy.valid_perfect_square_367;

/**
 367. Valid Perfect Square
 https://leetcode.com/problems/valid-perfect-square/

 Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:
 Input: 16
 Output: true

 Example 2:
 Input: 14
 Output: false

---

 1. Complexity
    1.1 Time Complexity is O(logn) where n is given number for binary search
        Time complexity is O(sqrt(n)) for a 1+3+5+7... math solution
 1.2 Space Complexity is O(1)
 2. Approach
    2.1 The first approach is based on checking required number using a binary search.
    2.2 The second approach is based on the math rule that the sum of sqr numbers should be equal from arithmetical
        progression from 1 plus 2...
         1 = 1
         4 = 1 + 3
         9 = 1 + 3 + 5
         16 = 1 + 3 + 5 + 7
         25 = 1 + 3 + 5 + 7 + 9
         36 = 1 + 3 + 5 + 7 + 9 + 11
         ....
         so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 0;
        int end = num / 2 + 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            long sqr = (long) mid * mid;
            if (sqr == num) {
                return true;
            } else if (sqr > num) {
                end = mid - 1;
            } else if (sqr < num) {
                start = mid + 1;
            }
        }
        return false;
    }

//    public boolean isPerfectSquare(int num) {
//        int i = 1;
//        while (num > 0) {
//            num -= i;
//            i += 2;
//        }
//        return num == 0;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPerfectSquare(808201));
    }
}
