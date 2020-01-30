package design.medium.online_stock_span_901;

import java.util.ArrayList;
import java.util.List;

/**
 901. Online Stock Span
 https://leetcode.com/problems/online-stock-span/

 Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

 The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

 For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

 Example 1:
 Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 Output: [null,1,1,1,2,1,4,6]
 Explanation:
 First, S = StockSpanner() is initialized.  Then:
 S.next(100) is called and returns 1,
 S.next(80) is called and returns 1,
 S.next(60) is called and returns 1,
 S.next(70) is called and returns 2,
 S.next(60) is called and returns 1,
 S.next(75) is called and returns 4,
 S.next(85) is called and returns 6.

 Note that (for example) S.next(75) returned 4, because the last 4 prices
 (including today's price of 75) were less than or equal to today's price.


 Note:
 Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 There will be at most 10000 calls to StockSpanner.next per test case.
 There will be at most 150000 calls to StockSpanner.next across all test cases.
 The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 If price going up - increase counter and check previous extremums. Sum up any values equal or less the current price.
     2.2 If price is going down, save previous element as extremum with the counter. Then update counter to 1.
     2.3 Idea is to reduce number of checking only to points when price went down.
 */

class ExtremumsSolution {

    private List<int[]> extremums;
    private int counter;
    private int prev;

    public ExtremumsSolution() {
        extremums = new ArrayList<>();
        counter = 0;
        prev = 0;
    }

    public int next(int price) {
        if (price < 0) {
            return -1;
        }

        int result = 0;
        if (price >= prev) {
            counter++;
            result = counter;
            // go back and check extremums
            int size = extremums.size();
            for (int i = size - 1; i >= 0; i--) {
                int[] pair = extremums.get(i);
                if (pair[0] <= price) {
                    result += pair[1];
                } else {
                    break;
                }
            }
        } else {
            extremums.add(new int[]{prev, counter});
            counter = 1;
            result = 1;
        }
        prev = price;

        return result;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
