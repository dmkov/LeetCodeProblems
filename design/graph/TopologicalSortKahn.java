package design.graph;

import java.util.LinkedList;
import java.util.List;

public class TopologicalSortKahn {

    private List<Integer> minWeight(DirectedEdgeWeightedGraph graph, int target) {
        int[] income = new int[graph.V()];
        for (EdgeWeighted edge : graph.edges()) {
            income[edge.u]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.V(); i++) {
            if (income[i] == 0) {
                queue.add(i);
            }
        }

        int counter = 0;
        List<Integer> result = new LinkedList<>();
        while (queue.size() > 0) {
            Integer v = queue.remove();
            result.add(v);
            for (EdgeWeighted edge : graph.adj(v)) {
                income[edge.u]--;
                if (income[edge.u] == 0) {
                    queue.add(edge.u);
                }
            }
            counter++;
        }

        if (counter != graph.V()) {
            throw new RuntimeException("Cycle is detected!");
        }

        return result;
    }

    public static void main(String[] args) {
        DirectedEdgeWeightedGraph graph = new DirectedEdgeWeightedGraph(5);
        graph.addEdge(new EdgeWeighted(0, 1, 1));
        graph.addEdge(new EdgeWeighted(3, 0, 3));
        graph.addEdge(new EdgeWeighted(1, 2, 1));
        graph.addEdge(new EdgeWeighted(2, 4, 1));
        graph.addEdge(new EdgeWeighted(0, 4, 5));
        graph.addEdge(new EdgeWeighted(3, 4, 1));

        TopologicalSortKahn topologicalSort = new TopologicalSortKahn();
        topologicalSort.minWeight(graph, 4);
    }
}
