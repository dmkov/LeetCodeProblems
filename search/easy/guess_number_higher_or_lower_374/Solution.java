package search.easy.guess_number_higher_or_lower_374;

/**
 374. Guess Number Higher or Lower (easy)
 https://leetcode.com/problems/guess-number-higher-or-lower/

 We are playing the Guess Game. The game is as follows:
 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number is higher or lower.

 You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 -1 : My number is lower
  1 : My number is higher
  0 : Congrats! You got it!

 Example :
 Input: n = 10, pick = 6
 Output: 6

---

 1. Complexity
    1.1 Time Complexity is O(log n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on binary search
    2.2 Define two pointer min and max where range is in 1 and n
    2.3 Get the middle min + (max - min) / 2
    2.4 Call the guess(mid) method, depending on response:
        2.4.1 -1: max = mid - 1
        2.4.2  1: min = mid + 1
        2.4.3  0: return result
    2.5 Repeat from 2.3 (while min <= max loop)

 */
public class Solution {
    public int guessNumber(int n) {
        int result = 0;
        int min = 1;
        int max = n;

        while (min <= max) {
            int mid = min + (max - min) / 2;
            int guess = guess(mid);
            if (guess == 0) {
                result = mid;
                break;
            } else if (guess == -1) {
                max = mid - 1;
            } else if (guess == 1) {
                min = mid + 1;
            }
        }

        return result;
    }

    private int guess(int n) {
        return -1;
    }
}
