package graph.medium.find_the_celebrity_277;

/**
 277. Find the Celebrity
 https://leetcode.com/problems/find-the-celebrity/

 Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

 Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

 You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 Example 1:
 Input: graph = [
     [1,1,0],
     [0,1,0],
     [1,1,1]
    ]
 Output: 1
 Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.

 Example 2:
 Input: graph = [
     [1,0,1],
     [1,1,0],
     [0,1,1]
    ]
 Output: -1
 Explanation: There is no celebrity.

 Note:
 The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
 Remember that you won't have direct access to the adjacency matrix.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to iterate all nodes and to check them with all available pairs
     2.2 The trick is if node A knows B, we can mark A as not celebrity and skip it in the next iteration. So all
        nodes will be checked once then.
 3. Implementation
     3.1 Validate input n and create an empty array of candidates that could not be celebrities.
     3.2 Iterate from 0 to n-1:
        3.2.1 If candidate was marked as not possible celebrity - skip iteration
        3.2.2 Otherwise, iterate from 0 to n - 1:
            3.2.2.1 Skip i==k (the element itself). If somebody does not know i -> we can break the loop, i is not celebrity
            3.2.2.2 Also if k knows i, we can put k to the not celebrity list, since there is out direction from this node.
        3.2.3 Check that all found K knows nobody else and return it then.
 */

class IterativeSolution {

    public int findCelebrity(int n) {
        if (n < 1) {
            return -1;
        }

        boolean[] candidate = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (candidate[i]) {
                continue;
            }

            boolean found = true;
            for (int k = 0; k < n; k++) {
                if (k == i) {
                    continue;
                }
                if (!knows(k, i)) {
                    found = false;
                    break;
                }
                candidate[k] = true;
            }

            if (found) {
                // check that it knows nobody
                boolean check = true;
                for (int k = 0; k < n; k++) {
                    if (k == i) {
                        continue;
                    }
                    if (knows(i, k)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return i;
                }
            }
        }

        return -1;
    }

    private boolean knows(int k, int i) {
        return true;
    }
}
