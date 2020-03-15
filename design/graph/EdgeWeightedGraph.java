package design.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EdgeWeightedGraph {

    List[] edges;

    public EdgeWeightedGraph (int V) {
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
        edges[e.u].add(e);
        edges[e.v].add(e);
    }

}
