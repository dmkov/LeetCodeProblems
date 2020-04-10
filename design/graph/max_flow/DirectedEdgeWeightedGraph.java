package design.graph.max_flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectedEdgeWeightedGraph {

    private int V;
    private List<DirectedEdgeWeighted>[] edges;

    public DirectedEdgeWeightedGraph(int V) {
        this.V = V;
        this.edges = new List[V];
        for (int i = 0; i < V; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void add(DirectedEdgeWeighted edge) {
        this.edges[ edge.from ].add(edge);
        this.edges[ edge.to ].add(edge);
    }

    public int maxFlow(int start, int end) {
        int maxFlow = 0;
        Stack<DirectedEdgeWeighted> path = new Stack<>();
        getAugmenticPath(start, end, path, new boolean[V]);
        while (path.size() > 0) {
            int flow = Integer.MAX_VALUE;
            int s = start;
            for (DirectedEdgeWeighted edge : path) {
                flow = Math.min(flow, edge.augmentic(s));
                s = edge.other(s);
            }

            maxFlow += flow;
            s = start;
            for (DirectedEdgeWeighted edge : path) {
                edge.addFlow(s, flow);
                s = edge.to;
            }
            path.clear();
            getAugmenticPath(start, end, path, new boolean[V]);
        }

        return maxFlow;
    }

    private boolean getAugmenticPath(int start, int end, Stack<DirectedEdgeWeighted> stack, boolean[] visited) {
        if (start == end) {
            return true;
        }

        visited[start] = true;
        for (DirectedEdgeWeighted edge : edges[start]) {
            if (!visited[ edge.other(start) ] && edge.augmentic(start) > 0) {
                stack.add(edge);
                if (getAugmenticPath(edge.other(start), end, stack, visited)) {
                    return true;
                }
                stack.pop();
            }
        }
        visited[start] = false;

        return false;
    }

    public static void main(String[] args) {
        DirectedEdgeWeightedGraph graph = new DirectedEdgeWeightedGraph(4);
        graph.add(new DirectedEdgeWeighted(0, 1, 2));
        graph.add(new DirectedEdgeWeighted(0, 2, 1));
        graph.add(new DirectedEdgeWeighted(1, 2, 3));
        graph.add(new DirectedEdgeWeighted(1, 3, 1));
        graph.add(new DirectedEdgeWeighted(2, 3, 2));

        System.out.println(graph.maxFlow(0, 3));
    }

}
