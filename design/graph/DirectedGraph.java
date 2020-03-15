package design.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectedGraph {

    List<DirectedEdge>[] edges;

    public DirectedGraph(int V) {
        this.edges = new List[V];
        for (int v = 0; v < V; v++) {
            this.edges[v] = new ArrayList<>();
        }
    }

    public void add(DirectedEdge e) {
        this.edges[e.from].add(e);
    }

    public List<DirectedEdge> adj(int v) {
        return edges[v];
    }

    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[edges.length];

        for (int i = 0; i < edges.length; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        while (stack.size() > 0) {
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(int from, boolean[] visited, Stack<Integer> stack) {
        visited[from] = true;
        for (DirectedEdge edge : edges[from]) {
            if (!visited[edge.to]) {
                dfs(edge.to, visited, stack);
            }
        }
        stack.add(from);
    }
}
