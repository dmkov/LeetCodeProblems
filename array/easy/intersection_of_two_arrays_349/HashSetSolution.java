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
    2.1 We iterate first array and put all items into the hash, then we iterate second array and compare items with the hashset.
 3. Implementation
    3.1 Check if the input arrays are valid
    3.2 Put all items from the first array into the hash
    3.3 Create result array and iterate the second input array. Compare items with the set and push them to the result.
        To avoid duplications, remove item from the set after the first attempt. The second hashset can also be used for this.
 */

class HashSetSolution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        int length1 = nums1.length;
        int length2 = nums2.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length1; i++) {
            set.add(nums1[i]);
        }

        int[] result = new int[Math.min(length1, length2)];
        int p = 0;
        for (int i = 0; i < length2; i++) {
            if (set.contains(nums2[i])) {
                result[p] = nums2[i];
                set.remove(nums2[i]);
                p++;
            }
        }

        return Arrays.copyOfRange(result, 0, p);
    }
}
