package search.medium.kth_smallest_element_in_a_sorted_matrix_378;

/**
 378. Kth Smallest Element in a Sorted Matrix
 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:
 matrix = [
     [ 1,  5,  9],
     [10, 11, 13],
     [12, 13, 15]
 ],
 k = 8,
 return 13.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.

 --------

 1. Complexity
    1.1 Time Complexity is O(k log r), where r is a number of rows.
    1.2 Space Complexity is O(r)
 2. Approach
    2.1 Most usually, when the array is sorted in one direction, we can use index as "search space",
        when the array is unsorted and we are going to find a specific number, we can use "range".
        Search element in values range.
 3. Implementation
    3.1 Check if input array is valid
    3.2 Assign left var to the smallest value (left top), right to the largest (right bottom)
    3.3 While left is less or equal right do a binary search. Counter number of elements less or equal mid in the array.
    3.4 Depending on result - move right or left values.
    3.5 Return left variable at the end. It is a bit confusing but here is the explanation:

 // 1) Q: Why we return lo at the end:
 //    A: Here lo=hi+1, for hi, we found <k elems, for lo, we found >=k elem, lo must have duplicates in matrix, return lo
 // 2) Q: Why lo exist in the matrix
 //    A: for lo which is only 1 more than hi, we could find some extra nums in matrix so that there r >=k elems, so lo it self must exist in the matrix to meet the requirement

 */

class BinarySearchSolution {

    public int kthSmallest(int[][] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length != arr[0].length) {
            return -1;
        }

        int left = arr[0][0];
        int right = arr[arr.length - 1][arr.length - 1];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int counter = 0;
            int n = arr[0][0];

            for (int i = 0; i < arr.length; i++) {
                for (int num : arr[i]) {
                    if (num <= mid) {
                        counter++;
                        n = Math.max(n, num);
                    }
                }
            }

            if (counter == k) {
                return n;
            } else if (counter > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

}
