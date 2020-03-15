package design.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DirectedEdgeWeightedGraph {

    List[] edges;

    public DirectedEdgeWeightedGraph(int V) {
        this.edges = new List[V];
        for (int i = 0; i < V; i++) {
            this.edges[i] = new LinkedList<EdgeWeighted>();
        }
    }

    public int V() {
        return edges.length;
    }

    public List<EdgeWeighted> adj(int v) {
        return edges[v];
    }

    public Set<EdgeWeighted> edges() {
        Set<EdgeWeighted> set = new HashSet<>();
        for (int i = 0; i < V(); i++) {
            set.addAll(edges[i]);
        }
        return set;
    }

    public void addEdge(EdgeWeighted e) {
        edges[e.v].add(e);
    }

    public List<Integer> topologicalSort() {
        List<Integer> res = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[V()];
        for (int i = 0; i < V(); i++) {
            if (!visited[i]) {
                dfs(i, stack, visited);
            }
        }

        while (stack.size() > 0) {
            res.add(stack.pop());
        }
        return res;
    }

    private void dfs(int v, Stack<Integer> stack, boolean[] visited) {
        List<EdgeWeighted> list = edges[v];
        visited[v] = true;
        for (EdgeWeighted edge : list) {
            if (!visited[edge.u]) {
                dfs(edge.u, stack, visited);
            }
        }
        stack.add(v);
    }

}
