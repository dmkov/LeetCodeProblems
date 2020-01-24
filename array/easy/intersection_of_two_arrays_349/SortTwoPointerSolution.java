package array.easy.intersection_of_two_arrays_349;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 349. Intersection of Two Arrays
 https://leetcode.com/problems/intersection-of-two-arrays/

 Given two arrays, write a function to compute their intersection.

 Example 1:
 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]

 Example 2:
 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]

 Note:
 Each element in the result must be unique.
 The result can be in any order.

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is size of bigger array
    1.2 Space Complexity is O(m) where m is the size of smaller array
 2. Approach
    2.1 Another idea to make O(1) space complexity but O(logn) time complexity. Sort arrays and use two pointers to compare numbers
 3. Implementation
    3.1 Check if the input arrays are valid
    3.2 Sort arrays and create i and j pointers for the first elements in the arrays.
    3.3 While i and j are less than array lengths, compare items in both arrays. Move smaller pointer and to avoid duplicates
        skip all equal items after adding them to the result array.
 */

class SortTwoPointerSolution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        int length1 = nums1.length;
        int length2 = nums2.length;
        Arrays.sort(nums1); // 1,1,2,2
        Arrays.sort(nums2); // 2,2

        int i = 0;
        int j = 0;
        int p = 0;
        int[] result = new int[Math.min(length1, length2)];
        while (i < length1 && j < length2) {
            if (nums1[i] == nums2[j]) {
                result[p] = nums1[i];
                while (i < length1 && nums1[i] == result[p])
                    i++;
                while (j < length2 && nums2[j] == result[p])
                    j++;
                p++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            }

        }

        return Arrays.copyOfRange(result, 0, p);
    }
}
