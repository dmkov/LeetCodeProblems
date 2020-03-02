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
     2.1 For every book in the list check two possible options: include it to the new shelf or leave on the current if
        it is possible. Check recursively remaining options after the selection and choose the min height at the end.
 3 Implementation
     3.1 Check if input array is valid and total width is bigger than 0.
     3.2 Add first book to the shelf and start recursion check for the every next one:
        3.2.1 Generate string key with the position and remaining width for the cache. Check if memo has stored
            value for this key. If it does - return it as an answer.
        3.2.2 If it is possible to add the current book to the remaining width on the shelf - execute recursive method
            to get height with the active book on the current shelf.
        3.2.3 Execute another recursive method to get height with the active book on the new shelf (reset remaining width
            and add the current height to the final result).
        3.2.4 Get the min of both methods, store it to the cache and return as a result.
 */

class DPUpToBottomSolution {
    public int minHeightShelves(int[][] books, int totalWidth) {
        if (books == null || books.length == 0 || totalWidth < 0) {
            return -1;
        }
        Map<String, Integer> memo = new HashMap<>();

        return check(books, 1, totalWidth - books[0][0], totalWidth, books[0][1], memo);
    }

    private int check(int[][] books, int pos, int remainingWidth, int totalWidth, int curHeight, Map<String, Integer> memo) {
        if (pos == books.length) {
            return curHeight;
        }
        String key = pos + "_" + remainingWidth;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int height = Integer.MAX_VALUE;
        if (remainingWidth - books[pos][0] >= 0) {
            height = check(books, pos + 1, remainingWidth - books[pos][0], totalWidth, Math.max(books[pos][1], curHeight), memo);
        }
        height = Math.min(check(books, pos + 1, totalWidth - books[pos][0], totalWidth, books[pos][1], memo) + curHeight, height);

        memo.put(key, height);

        return height;
    }
}
