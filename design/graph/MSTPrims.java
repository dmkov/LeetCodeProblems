package design.graph;

import design.union_find.QuickFind;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MSTPrims {

    private List<EdgeWeighted> mst(EdgeWeightedGraph graph) {
        List<EdgeWeighted> res = new LinkedList<>();

        PriorityQueue<EdgeWeighted> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        boolean[] visited = new boolean[graph.V()];

        visited[0] = true;
        pq.addAll(graph.adj(0));

        while (pq.size() > 0) {
            EdgeWeighted edge = pq.poll();
            if (!visited[edge.v] || !visited[edge.u]) {
                res.add(edge);
                pq.addAll(graph.adj(edge.v));
                visited[edge.v] = true;
                visited[edge.u] = true;
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

        MSTPrims kruskals = new MSTPrims();
        kruskals.mst(graph);
    }

}
