package design.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PathTopologicalSort {

    private List<Integer> minWeight(DirectedEdgeWeightedGraph graph, int target) {
        int[] from = new int[graph.V()];
        int[] weight = new int[graph.V()];
        Arrays.fill(weight, Integer.MAX_VALUE);

        weight[0] = 0;
        from[0] = 0;
        for (int v : graph.topologicalSort()) {
            for (EdgeWeighted edge : graph.adj(v)) {
                if (weight[edge.u] > weight[edge.v] + edge.weight) {
                    weight[edge.u] = weight[edge.v] + edge.weight;
                    from[edge.u] = edge.v;
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (target != 0) {
            stack.add(target);
            target = from[target];
        }
        return stack;
    }

    public static void main(String[] args) {
        DirectedEdgeWeightedGraph graph = new DirectedEdgeWeightedGraph(5);
        graph.addEdge(new EdgeWeighted(0, 1, 1));
        graph.addEdge(new EdgeWeighted(0, 3, 3));
        graph.addEdge(new EdgeWeighted(1, 2, 1));
        graph.addEdge(new EdgeWeighted(2, 4, 1));
        graph.addEdge(new EdgeWeighted(0, 4, 5));
        graph.addEdge(new EdgeWeighted(3, 4, 1));

        PathTopologicalSort topologicalSort = new PathTopologicalSort();
        topologicalSort.minWeight(graph, 4);
    }
}
