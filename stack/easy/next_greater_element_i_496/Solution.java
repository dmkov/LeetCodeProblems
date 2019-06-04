package stack.easy.next_greater_element_i_496;

import java.util.HashMap;
import java.util.Map;

/**
 496. Next Greater Element I
 https://leetcode.com/problems/next-greater-element-i/

 You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

 The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

 Example 1:
 Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 Output: [-1,3,-1]
 Explanation:
 For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 For number 1 in the first array, the next greater number for it in the second array is 3.
 For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

 Example 2:
 Input: nums1 = [2,4], nums2 = [1,2,3,4].
 Output: [3,-1]
 Explanation:
 For number 2 in the first array, the next greater number for it in the second array is 3.
 For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

 Note:
 All elements in nums1 and nums2 are unique.
 The length of both nums1 and nums2 would not exceed 1000.

 ---


 1. Complexity
    1.1 Time Complexity is O(n * m) - where n and m is the length of arrays
            in fact, it will be mode efficient because of hashmap and using start index
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on recursively check of items next to the current element in the list.
        Alternative solution is to create stack and push/pop result but it will have same complexity
    2.2 Implementation
        2.2.1 Put values/indexes from the second array to the hashmap to get indexes faster
        2.2.2 Iterate through first array and execute method to get maximum element next to the item.
        2.2.3 In a separate method iterate second array starting the index of selected item (it can be retrieved
            from hashmap) and check if there are greater values than the current one.
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map2.put(nums2[i], i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = findMax(nums1[i], map2.get(nums1[i]), nums2);
        }
        return result;
    }

    private int findMax(int val, int startIndex, int[] arr) {
        int result = -1;
        for (int i = startIndex + 1; i < arr.length; i++) {
            if (arr[i] > val) {
                result = arr[i];
                break;
            }
        }
        return result;
    }
}
