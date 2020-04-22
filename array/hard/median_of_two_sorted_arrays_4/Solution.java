package array.hard.median_of_two_sorted_arrays_4;

/**
 4. Median of Two Sorted Arrays
 https://leetcode.com/problems/median-of-two-sorted-arrays/

 There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 You may assume nums1 and nums2 cannot be both empty.

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]
 The median is 2.0

 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 --------

 1. Complexity
    1.1 Time Complexity is O(log(min(m,n)))
    1.2 Space Complexity is O(1)
 2. Approach
    https://www.youtube.com/watch?v=LPFhl65R7ww
    2.1 In smallest array with binary search select the the mid element
    2.2 Calculate the difference in the second array. Now we have the cut.
    2.3 Check that all elements of both cuts are smaller than their next items in the second half
    2.4 If not - shift mid to the right or to the left, otherwise - here is the answer
 */

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res = 0.0;
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return res;
        }

        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int left = 0;
        int right = nums1.length;
        int size = nums1.length + nums2.length;
        while (left <= right) {
            int X = left + (right - left)/2;
            int Y = size/2 - X;

            if (X > 0 && Y < nums2.length && nums1[X-1] > nums2[Y]) {
                right = X - 1;
            } else if (X < nums1.length && Y > 0 && nums2[Y-1] > nums1[X]) {
                left = X + 1;
            } else {
                // answer
                if (size % 2 == 0) {
                    int f = Math.max(
                            (X == 0) ? Integer.MIN_VALUE : nums1[X-1],
                            (Y == 0) ? Integer.MIN_VALUE : nums2[Y-1]
                    );
                    int s = Math.min(
                            (X == nums1.length) ? Integer.MAX_VALUE : nums1[X],
                            (Y == nums2.length) ? Integer.MAX_VALUE : nums2[Y]
                    );
                    res = (double)(f + s) / 2;
                } else {
                    res = Math.min(
                            (X == nums1.length) ? Integer.MAX_VALUE : nums1[X],
                            (Y == nums2.length) ? Integer.MAX_VALUE : nums2[Y]
                    );
                }
                break;
            }
        }

        return res;
    }
}


/**
 1. What is the median?
 2. Is there possible to have duplicates?
 3. And the size of arrays is different? What is the max size? Empty arrays?
 4. What possible range of values? Are negatives and zero allowed?

     1 2 3 ---> 3
     4 5

     1 3 6 ---> 2.5
     2

     1 1 1 1
     1 3 5 6 7

     X and Y - cut
     X + Y = size/2
     y = size/2 - x


 1. Get the middle of the smallest array. Count how many items should be on the left for another array
 2. Since we have cut in two arrays:
     while left <= right
     - Xn should be smaller or equal to Yn+1 else right = mid - 1
     - Yn should be smaller or equal to Xn+1 else left = mid + 1
 3. If size % 2 == 0 -> get max of Y and X and min of two next items, then divide by 2
                == 1 -> get min of next two items
 */
