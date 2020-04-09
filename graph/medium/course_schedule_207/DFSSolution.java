package graph.medium.course_schedule_207;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 207. Course Schedule
 https://leetcode.com/problems/course-schedule/

 There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:
 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.

 Example 2:
 Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.

 Constraints:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 1 <= numCourses <= 10^5

 --------

 1. Complexity
    1.1 Time Complexity is O(n) because of sorting
    1.2 Space Complexity is O(n) - sorting and separate list with ends
 2. Approach
    2.1 The idea is find if there are cycles in graph or no. With no cycles we can do a topological sort
        and reach the goal. There are two ways to check cycles: DFS and Kahn's algorithm. Lets do a DFS check here.
 3 Implementation
    3.1 Check if input args are valid, create lists for each node with linked nodes (directions).
    3.2 Create a boolean visited array. Iterate all nodes:
        3.2.1 Create an empty stack set to check visited nodes in this round
        3.2.2 Call a recursive function for the node:
            3.2.2.1 Mark it as visited and add to the stack set.
            3.2.2.2 Get all children of the node and check them
            3.2.2.3 If child was not visited, check that it is not in the stack set (otherwise return false).
            3.2.2.4 Call a recursive function for the child. At the end return true
                    if nested function responded with true.
 */

class DFSSolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        List<Integer>[] rel = new List[numCourses];
        for (int[] p : prerequisites) {
            if (rel[ p[0] ] == null) {
                rel[ p[0] ] = new LinkedList<>();
            }
            rel[ p[0] ].add(p[1]);
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            Set<Integer> cycle = new HashSet<>();

            if (!trace(i, rel, visited, cycle)) {
                return false;
            }
        }

        return true;
    }

    private boolean trace(int i, List<Integer>[] rel, boolean[] visited, Set<Integer> cycle) {
        if (!visited[i]) {
            visited[i] = true;
            cycle.add(i);

            if (rel[i] != null) {
                for (Integer child : rel[i]) {
                    if (cycle.contains(child)) {
                        return false;
                    }
                    if (visited[child]) {
                        continue;
                    }

                    if (!trace(child, rel, visited, cycle)) {
                        return false;
                    }
                }
            }

            cycle.remove(i);
        }

        return true;
    }

}
