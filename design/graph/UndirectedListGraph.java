package design.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UndirectedListGraph {

    LinkedList<Integer>[] graph;

    public UndirectedListGraph(int n) {
        graph = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    public int V() {
        return graph.length - 1;
    }

    public int E() {
        int count = 0;
        for (int i = 1; i < graph.length; i++) {
            count += graph[i].size();
        }
        return count / 2;
    }

    public void addEdge(int v, int w) {
        graph[v].add(w);
        graph[w].add(v);
    }

    public List<Integer> adj(int v) {
        return graph[v];
    }

    public static void main(String[] args) {
        UndirectedListGraph graph = new UndirectedListGraph(4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);

        System.out.println("Vertexes (4):" + graph.V());
        System.out.println("Edges (2):" + graph.E());
        System.out.println("Edges for 1 (3):" + Arrays.toString(graph.adj(1).toArray()));
        System.out.println("Edges for 3 (1,2):" + Arrays.toString(graph.adj(3).toArray()));
        System.out.println("Edges for 4 ():" + Arrays.toString(graph.adj(4).toArray()));
    }

}
