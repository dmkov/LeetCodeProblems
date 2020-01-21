package array.easy.best_time_to_buy_and_sell_stock_121;

/**
 121. Best Time to Buy and Sell Stock
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

 Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 Note that you cannot sell a stock before you buy one.

 Example 1:
 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.

 Example 2:
 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.


 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
     2.2 Implementation
         2.2.1 Check if given array is valid.
         2.2.2 Iterate given array. The idea is to check only cases when the price is going up so skip cycles when it goes down.
         2.2.3 If the current price is bigger than lowestPrice then continue keeping the lowestPrice and compute
            the max diff from the current change or previous one.
         2.2.4 If the current price is less than lowestPrice, then update lowestPrice and compute the max diff from
            the previous value and diff between lowestPrice and the next element.
 */

public class IntuitiveSolution {
    public int maxProfit(int[] prices) {
        int max = 0; // 5
        if (prices == null || prices.length == 0) {
            return max;
        }

        int length = prices.length;
        int lowestPrice = Integer.MAX_VALUE; // 1
        for (int i = 0; i < length - 1; i++) {
            if (prices[i] > prices[i+1]) {
                continue;
            }
            if (lowestPrice < prices[i]) {
                int diff = prices[i+1] - lowestPrice;
                max = Math.max(max, diff);
            } else {
                lowestPrice = prices[i];
                int diff = prices[i+1] - prices[i]; // 2
                max = Math.max(max, diff);
            }
        }

        return max;
    }
}
