package sort.medium.pancake_sorting_969;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 969. Pancake Sorting
 https://leetcode.com/problems/pancake-sorting/

 Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

 Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.


 Example 1:
 Input: [3,2,4,1]
 Output: [4,2,4,3]
 Explanation:
 We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 Starting state: A = [3, 2, 4, 1]
 After 1st flip (k=4): A = [1, 4, 2, 3]
 After 2nd flip (k=2): A = [4, 1, 2, 3]
 After 3rd flip (k=4): A = [3, 2, 1, 4]
 After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.

 Example 2:
 Input: [1,2,3]
 Output: []
 Explanation: The input is already sorted, so there is no need to flip anything.
 Note that other answers, such as [3, 3], would also be accepted.

 Note:
 1 <= A.length <= 100
 A[i] is a permutation of [1, 2, ..., A.length]

---

 1. Complexity
    1.1 Time Complexity is O(n^2)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to use max heap to get the max number every time, then get its index from the hashmap and
        sort it to the first position and then to the max index. So we pop the max element to the end with the every iteration.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Create max heap and hash map to store numbers vs indexes. Populate them from the given array. Track max index as length - 1.
    3.3 While heap is not empty, pop element and check its index from the map.
    3.4 If it equals max index so we do not shift anything. Reduce the max and continue to the next iteration.
    3.5 Otherwise, reverse indexes of all elements before the current element position including itself.
    3.5 Then, reverse all elements from 0 to max to move max number to the end. Save number of updated elements every time.
    3.6 For reversing elements, use the map updating indexes without actual array modification.
        (Another version provides idea to check and reverse array itself -
            https://leetcode.com/problems/pancake-sorting/discuss/214213/JavaC%2B%2BPython-Straight-Forward)
 */

class MySolution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new LinkedList<>();
        if (arr == null || arr.length == 0) {
            return result;
        }

        // r: 3, 4, 2, 3
        // [2,1,3,4]
        // map: 3->3, 2->0, 4->3, 1->1
        // heap: 1

        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a); // by default is min sorting! (default sorting)
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
            map.put(arr[i], i);
        }

        int max = arr.length; // 2
        while (heap.size() > 0) {
            Integer num = heap.poll(); // 2
            Integer pos = map.get(num); // 0
            if (max == pos + 1) {
                // already sorted
                max--;
                continue;
            }

            boolean check = false;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer cur = entry.getValue();
                Integer updated = pos - entry.getValue();
                if (cur <= pos && !cur.equals(updated)) {
                    map.put(entry.getKey(), updated);
                    check = true;
                }
            }
            if (check) {
                result.add(pos + 1);
            }

            check = false;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer cur = entry.getValue();
                Integer updated = max - entry.getValue() - 1;
                if (cur <= max && !cur.equals(updated)) {
                    map.put(entry.getKey(), updated);
                    check = true;
                }
            };
            if (check) {
                result.add(max);
            }

            max--;
        }

        return result;
    }
}
