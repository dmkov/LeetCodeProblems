package search.medium.kth_smallest_element_in_a_sorted_matrix_378;

import java.util.PriorityQueue;

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
        Heap doesn't have more than r elements inside, which means every poll/offer operations takes O(log r) time,
        which we repeat k times.
    1.2 Space Complexity is O(r)
 2. Approach
    2.1 Build a minHeap of elements from the first row. Pop the root and replace it with the next element
        from the same column
 3. Implementation
    3.1 Check if input array is valid
    3.2 Build the min heap. Populate it with arrays containing numbers and their row/col positions
    3.3 Pop elements until K is reached. If it is not the last element in the column - push the next one back to the heap
 */

class HeapSolution {

    public int kthSmallest(int[][] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length != arr[0].length) {
            return -1;
        }

        int n = arr.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            heap.add(new int[]{arr[0][i], 0, i});
        }

        int result = arr[0][0];
        while (k > 0) {
            int[] num = heap.poll();
            result = num[0];
            if (num[1] < n - 1) {
                heap.add(new int[]{arr[ num[1]+1 ][ num[2] ], num[1] + 1, num[2]});
            }
            k--;
        }

        return result;
    }

}
