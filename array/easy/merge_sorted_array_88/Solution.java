package array.easy.merge_sorted_array_88;

/**
 88. Merge Sorted Array
 https://leetcode.com/problems/merge-sorted-array/

 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 The number of elements initialized in nums1 and nums2 are m and n respectively.
 You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.

 Example:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 Output: [1,2,2,3,5,6]
 */

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null || nums1.length < (m + n)) {
            return;
        }

        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[n + m - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[n + m - 1] = nums2[n - 1];
                n--;
            }
        }
        while (n > 0) {
            nums1[n + m - 1] = nums2[n - 1];
            n--;
        }
    }
}
