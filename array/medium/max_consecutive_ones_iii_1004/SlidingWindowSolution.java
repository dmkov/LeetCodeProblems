package array.medium.max_consecutive_ones_iii_1004;

/**
 1004. Max Consecutive Ones III
 https://leetcode.com/problems/max-consecutive-ones-iii/

 Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

 Return the length of the longest (contiguous) subarray that contains only 1s.

 Example 1:

 Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 Output: 6
 Explanation:
 [1,1,1,0,0,1,1,1,1,1,1]
 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 Example 2:

 Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 Output: 10
 Explanation:
 [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Idea is to use sliding window with left and right pointers. Shift right with every turn and
        restore left if k is less is zero before it
 */

public class SlidingWindowSolution {
    public int longestOnes(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        int left = 0, right = 0, max = 0;
        while (right < arr.length) {
            while (k < 0) {
                if (arr[left] == 0) {
                    k++;
                }
                left++;
            }
            max = Math.max(max, right - left);
            if (arr[right] == 0) {
                k--;
            }
            right++;
        }
        if (k >= 0) {
            max = Math.max(max, right - left);
        }

        return max;
    }

    /**
     1. What is the input format? Is it array of 1/0 and just a number?
     2. If k==0, I return the max existing range of 1s, otherwise, I can change k '0' elements?

         110010 , 2 -> 111110
         101011 , 1 -> 101111
         0010010011 , 2 -> 0010011111
         0010010011 , 0 -> 0010010011

         0010010011  , 2
         | |

         001000010011  , 0
         |    |

         1. Set left and right pointers to the first index
         2. while right is smaller than string length
             while k < 0
                move left and restore k, if left was pointed to 0
             get the max difference
             if right is pointed to 1 or k >= 0
                -> move right and decrease k, if right was 0
         3. get the last diff if k >= 0
     */
}
