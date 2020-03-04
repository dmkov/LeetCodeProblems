package graph.medium.reconstruct_itinerary_332;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 332. Reconstruct Itinerary
 https://leetcode.com/problems/reconstruct-itinerary/

 Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:
 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.

 Example 1:
 Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

 Example 2:
 Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]

 Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 But it is larger in lexical order.

 --------

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 The idea is to sort all tickets by source and then destination cities and do a DFS traverse then. The first
        valid path will be the answer since we sorted list in needed order before.
 3 Implementation
    3.1 Check if input list is valid.
    3.2 Sort list using custom comparator checking the src city first and destination strings if src names are equal.
    3.3 Create a stack to store results, visited array to mark visited vertexes and start a DFS traverse with remaining counter:
        3.3.1 Return true and store to stack if counter is 0.
        3.3.2 Check all nodes and do a recursive traverse if it was not visited and start name equals to the current destination
        3.3.3 If recursive traverse returns true, add src city to the stack and also return true, breaking the further traverse
    3.4 Iterate stack and pop results to the list to return.
 */

class DFSSolution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Stack<String> path = new Stack<>();
        if (tickets == null || tickets.size() == 0) {
            return path;
        }

        Collections.sort(tickets, (a, b) -> {
            if (a.get(0).equals(b.get(0))) {
                return a.get(1).compareTo(b.get(1));
            }
            return a.get(0).compareTo(b.get(0));
        });
        boolean[] visited = new boolean[tickets.size()];

        Stack<String> stack = new Stack<>();
        trace(tickets, visited, "JFK", tickets.size(), stack);

        List<String> res = new LinkedList<>();
        while (stack.size() > 0) {
            res.add(stack.pop());
        }

        return res;
    }

    private boolean trace(List<List<String>> list, boolean[] visited, String start, int remaining, Stack<String> stack) {
        if (remaining == 0) {
            stack.push(start);
            return true;
        }

        int i = 0;
        for (List<String> ticket : list) {
            if (ticket.get(0).equals(start) && !visited[i]) {
                visited[i] = true;
                if (trace(list, visited, ticket.get(1), remaining - 1, stack)) {
                    stack.push(start);
                    return true;
                }
                visited[i] = false;
            }
            i++;
        }
        return false;
    }
}
