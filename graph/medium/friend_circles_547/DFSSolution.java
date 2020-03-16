package graph.medium.friend_circles_547;

/**
 547. Friend Circles
 https://leetcode.com/problems/friend-circles/

 There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

 Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

 Example 1:
 Input:
     [[1,1,0],
      [1,1,0],
      [0,0,1]]
 Output: 2
 Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 The 2nd student himself is in a friend circle. So return 2.

 Example 2:
 Input:
     [[1,1,0],
      [1,1,1],
      [0,1,1]]
 Output: 1
 Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

 Note:
 N is in range [1,200].
 M[i][i] = 1 for all students.
 If M[i][j] = 1, then M[j][i] = 1.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Similar to Island Count. Do a DFS for every student and then check unvisited yet.
 3 Implementation
    3.1 Check if given array is not null or empty. Create empty visited array.
    3.2 Iterate over all students. If the current one was not visited, increment result counter and do a DFS
        marking all friends as visited.
 */

class DFSSolution {

    public int findCircleNum(int[][] arr) {
        int res = 0;
        if (arr == null || arr.length == 0 || arr[0].length == 0 || arr.length != arr[0].length) {
            return res;
        }

        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                dfs(arr, i, visited);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] arr, int p, boolean[] visited) {
        visited[p] = true;
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i] && arr[p][i] == 1) {
                dfs(arr, i, visited);
            }
        }
    }

}
