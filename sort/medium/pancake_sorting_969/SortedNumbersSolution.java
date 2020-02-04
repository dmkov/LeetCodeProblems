package sort.medium.pancake_sorting_969;

import java.util.ArrayList;
import java.util.List;

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
    2.1 It will work if all number is in increasing order and max = arr.length, so we do not need a max heap.
        Check additionally heap solution
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Get the largest number as a length of the array.
    3.3 For every number from 1 to n:
        3.3.1 Get the position of the largest number.
        3.3.2 Do two flips to the found index and the max position (you can additionally check if numbers are already on their places).
        3.3.3 Add both flips to the result and decrease the max position/number
 */

class SortedNumbersSolution {

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int n = A.length, largest = n;
        for (int i = 0; i < n; i++) {
            int index = find(A, largest);
            flip(A, index);
            flip(A, largest - 1);
            result.add(index + 1);
            result.add(largest--);
        }
        return result;
    }

    private int find(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private void flip(int[] A, int index) {
        int i = 0, j = index;
        while (i < j) {
            int temp = A[i];
            A[i++] = A[j];
            A[j--] = temp;
        }
    }

}
