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
    1.1 Time Complexity is O(n^3). Not the most efficient solution, check DFS version.
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to union all items using union-find algorithm and then find how many elements are grouped together.
 */

class UnionFindSolution {

    public int findCircleNum(int[][] arr) {
        int res = 0;
        if (arr == null || arr.length == 0 || arr[0].length == 0 || arr.length != arr[0].length) {
            return res;
        }

        int[] parent = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                res++;
                for (int j = i; j < arr.length; j++) {
                    if (find(parent, i, j)) {
                        visited[j] = true;
                    }
                }
            }
        }

        return res;
    }

    private void union(int[] parent, int i, int j) {
        parent[i] = parent(parent, j);
    }

    private int parent(int[] parent, int n) {
        while (parent[n] != n) {
            n = parent[n];
        }
        return n;
    }

    private boolean find(int[] parent, int i, int j) {
        return parent(parent, j) == parent(parent, i);
    }

}
