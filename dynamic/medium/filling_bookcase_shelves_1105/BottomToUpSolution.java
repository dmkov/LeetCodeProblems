package dynamic.medium.filling_bookcase_shelves_1105;

import java.util.HashMap;
import java.util.Map;

/**
 1105. Filling Bookcase Shelves
 https://leetcode.com/problems/filling-bookcase-shelves/

 We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 We want to place these books in order onto bookcase shelves that have total width shelf_width.
 We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

 Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

 Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.

 Example 1:
 Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 Output: 6

 Explanation:
 The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 Notice that book number 2 does not have to be on the first shelf.

 Constraints:
 1 <= books.length <= 1000
 1 <= books[i][0] <= shelf_width <= 1000
 1 <= books[i][1] <= 1000

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2) ?
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 Put every book to the new shelf. Calculate dp[i] = dp[i - 1] + height.
     2.2 Check all previous books that first to total width - current width.
     2.3 After taking previous book, compare all heights in the hand + dp[j] with dp[i] and store min value to dp[i]
     2.4 In such way we get the min possible height rolling all previous books that fit on the current shelf.
 3 Implementation
     3.1 Check if input array is valid and total width is bigger than 0.
     3.2 Create dp array with the length + 1 of the given books list.
     3.3 For every book in the loop:
        3.3.1 Get the dp[i] value as dp[i - 1] + height of the current book.
        3.3.2 While sum of the current and all previous books is less or equal to shelf width, iterate through these
            books back and:
            3.3.2.1 Get the height of possible shelf as max height of previous value and the next book
            3.3.2.2 Get dp[i] as the max value of previous dp[i] and dp[j] (previous book - 1) + height of the current shelf
            3.3.2.3 Increase the width for the shelf.
        3.2.2 In such way we always roll as much books as possible back and check if every combination gives us a better set
            in the global height.
 */

class BottomToUpSolution {
    public int minHeightShelves(int[][] books, int totalWidth) {
        if (books == null || books.length == 0 || totalWidth < 0) {
            return -1;
        }

        int[] dp = new int[books.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= books.length; i++) {
            int width = books[i - 1][0];
            int height = books[i - 1][1];
            dp[i] = dp[i - 1] + height;
            for (int j = i - 2; j >= 0 && width + books[j][0] <= totalWidth; j--) {
                height = Math.max(height, books[j][1]);
                dp[i] = Math.min(dp[i], dp[j] + height);
                width += books[j][0];
            }
        }

        return dp[books.length];
    }
}
