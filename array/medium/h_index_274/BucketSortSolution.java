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
    2.1 The idea is to sort elements and then get the first element where value is greater than length-index. It will
        be our cut.
 3 Implementation
    3.1 Check if given array is valid.
    3.2 Since actual values greater than N do not make real difference - set them to N (length)
    3.3 Do a bucket sort for fixed range
    3.4 Iterate over all numbers. Compare value with (length-indx), if it is greater - return (length-indx) as the result
 */

public class BucketSortSolution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int length = citations.length;
        for (int i = 0; i < length; i++) {
            if (citations[i] >= length) {
                citations[i] = length;
            }
        }

        bucketSort(citations);

        for (int i = 0; i < length; i++) {
            if (citations[i] >= length - i) {
                return length - i;
            }
        }
        return 0;
    }

    private void bucketSort(int[] arr) {
        int[] cnt = new int[arr.length + 2];
        for (int num : arr) {
            cnt[num + 1]++;
        }
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        int[] aux = new int[arr.length];
        for (int num : arr) {
            aux[ cnt[num]++ ] = num;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = aux[i];
        }

    }
}
