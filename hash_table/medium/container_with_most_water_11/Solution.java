package hash_table.medium.container_with_most_water_11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 11. Container With Most Water
 https://leetcode.com/problems/container-with-most-water/

 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.

 Example:
 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49
 */

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int local = Math.min(height[left], height[right]);
            int area = local * (right - left);
            if (maxArea < area) {
                maxArea = area;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxArea;
    }


//    public int maxArea(int[] height) {
//        int maxArea = 0;
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int local = Math.min(height[i], height[j]);
//                int area = local * (j - i);
//                if (area > maxArea) {
//                    maxArea = area;
//                }
//            }
//        }
//        return maxArea;
//    }
}
