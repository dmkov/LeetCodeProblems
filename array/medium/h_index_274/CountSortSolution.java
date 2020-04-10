package array.medium.h_index_274;

/**
 274. H-Index
 https://leetcode.com/problems/h-index/

 Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

 According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

 Example:
 Input: citations = [3,0,6,1,5]
 Output: 3

 Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 received 3, 0, 6, 1, 5 citations respectively.
 Since the researcher has 3 papers with at least 3 citations each and the remaining
 two with no more than 3 citations each, her h-index is 3.
 Note: If there are several possible values for h, the maximum one is taken as the h-index.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to do a count sort and then get the last element where counter is greater than length-index. It will
        be our cut.
 3 Implementation
    3.1 Check if given array is valid.
    3.2 Count element appearance. Since actual values greater than N do not make real difference - set them to N (length)
    3.3 Iterate over all numbers from right to left.
    3.4 Compare value with (length-indx), if it is greater - return (length-indx) as the result
 */

public class CountSortSolution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int length = citations.length;
        int[] cnt = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (citations[i] >= length) {
                cnt[length]++;
            } else {
                cnt[ citations[i] ]++;
            }
        }

        int c = 0;
        for (int i = length; i >= 0; i--) {
            c += cnt[i];
            if (c >= i) {
                return i;
            }
        }

        return 0;
    }
}
