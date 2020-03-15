package design.graph;

import design.union_find.QuickFind;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MSTKruskals {

    private List<EdgeWeighted> mst(EdgeWeightedGraph graph) {
        List<EdgeWeighted> res = new LinkedList<>();

        PriorityQueue<EdgeWeighted> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (EdgeWeighted edge : graph.edges()) {
            pq.add(edge);
        }

        QuickFind uf = new QuickFind(graph.V());
        while (pq.size() > 0) {
            EdgeWeighted edge = pq.poll();
            if (!uf.find(edge.u, edge.v)) {
                uf.union(edge.u, edge.v);
                res.add(edge);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(5);
        graph.addEdge(new EdgeWeighted(0, 2, 6));
        graph.addEdge(new EdgeWeighted(2, 3, 3));
        graph.addEdge(new EdgeWeighted(3, 4, 1));
        graph.addEdge(new EdgeWeighted(4, 0, 2));
        graph.addEdge(new EdgeWeighted(1, 0, 5));
        graph.addEdge(new EdgeWeighted(1, 2, 3));
        graph.addEdge(new EdgeWeighted(1, 4, 8));
        graph.addEdge(new EdgeWeighted(1, 3, 7));

        MSTKruskals kruskals = new MSTKruskals();
        kruskals.mst(graph);
    }

}
