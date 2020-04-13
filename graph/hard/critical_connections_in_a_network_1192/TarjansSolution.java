package graph.hard.critical_connections_in_a_network_1192;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 1192. Critical Connections in a Network
 https://leetcode.com/problems/critical-connections-in-a-network/

 There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

 A critical connection is a connection that, if removed, will make some server unable to reach some other server.

 Return all critical connections in the network in any order.

 Example 1:
 Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 Output: [[1,3]]
 Explanation: [[3,1]] is also accepted.

 Constraints:
 1 <= n <= 10^5
 n-1 <= connections.length <= 10^5
 connections[i][0] != connections[i][1]
 There are no repeated connections.

 --------

 1. Complexity
    1.1 Time Complexity is O(V + E) where V is number of vertices and E is the number of edges
    1.2 Space Complexity is O(E + V)
 2. Approach
    2.1 For every node keep its depth and rank numbers. When edge from the current node points to the node with
        smaller rank, update the current rank as well (it means we found the cycle).
    2.2 At the end of backtracking (dfs) check if child node has larger rank than the current one (it means there
        is no cycle and this is a critical connection)
 3. Implementation
    3.1 Check is input array with connections is valid.
    3.2 Build a graph with lists of connected nodes for each node.
    3.3 Create rank and depth arrays with number of vertices. Since depth is also a visited flag, fill it with -1.
    3.4 Start DFS on the first node:
        3.4.1 Update rank and depth values for the current node with increased step counter
        3.4.2 Get the list of children. If child was not visited -> call recursion function
            and get the rank of this element. Update rank of the current node.
        3.4.3 If child rank is smaller than the current node - there is a cycle,
            otherwise, add link to the critical list
 */

public class TarjansSolution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new LinkedList<>();
        if (n < 2 || connections == null) {
            return res;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<Integer>();
        }
        for (List<Integer> c : connections) {
            graph[c.get(0)].add(c.get(1));
            graph[c.get(1)].add(c.get(0));
        }

        int[] rank = new int[n];
        int[] depth = new int[n];
        Arrays.fill(depth, -1);
        dfs(0, 0, depth, graph, rank, res);

        return res;
    }

    int step = 0; // time when discover each vertex

    private void dfs(Integer node, Integer parent, int[] depth, List[] graph, int[] rank, List<List<Integer>> res) {
        depth[node] = rank[node] = ++step; // discover u

        for (Integer child : (List<Integer>)graph[ node ]) {
            if (!child.equals(parent)) {
                if (depth[ child ] == -1) {
                    dfs(child, node, depth, graph, rank, res);

                    rank[node] = Math.min(rank[node], rank[child]);

                    if (rank[child] > depth[node]) {
                        res.add(Arrays.asList(node, child));
                    }
                } else {
                    rank[node] = Math.min(rank[node], depth[child]);
                }
            }
        }
    }
}
