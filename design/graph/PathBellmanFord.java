package design.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathBellmanFord {

    private List<Integer> minWeight(DirectedEdgeWeightedGraph graph, int target) {
        int[] from = new int[graph.V()];
        int[] weight = new int[graph.V()];
        Arrays.fill(weight, Integer.MAX_VALUE);
        from[0] = 0;
        weight[0] = 0;

        for (int i = 0; i < graph.V(); i++) {
            for (int j = 0; j < graph.V(); j++) {
                List<EdgeWeighted> list = graph.adj(j);
                for (EdgeWeighted edge : list) {
                    if (weight[edge.v] != Integer.MAX_VALUE && weight[edge.v] + edge.weight < weight[edge.u]) {
                        weight[edge.u] = weight[edge.v] + edge.weight;
                        from[edge.u] = edge.v;
                    }
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (target != 0) {
            stack.add(target);
            target = from[target];
        }
        List<Integer> res = new LinkedList<>();
        while (stack.size() > 0) {
            res.add(stack.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        DirectedEdgeWeightedGraph graph = new DirectedEdgeWeightedGraph(5);
        graph.addEdge(new EdgeWeighted(0, 1, 1));
        graph.addEdge(new EdgeWeighted(0, 3, 3));
        graph.addEdge(new EdgeWeighted(1, 2, 1));
        graph.addEdge(new EdgeWeighted(2, 4, 1));
        graph.addEdge(new EdgeWeighted(0, 4, 5));
        graph.addEdge(new EdgeWeighted(3, 4, 1));

        PathBellmanFord bellmanFord = new PathBellmanFord();
        bellmanFord.minWeight(graph, 4);
    }
}
