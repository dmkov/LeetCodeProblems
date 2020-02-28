package design.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UndirectedArrayGraph {

    boolean[][] graph;

    public UndirectedArrayGraph(int n) {
        graph = new boolean[n+1][n+1];
    }

    public int V() {
        return graph.length - 1;
    }

    public int E() {
        int count = 0;
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph.length; j++) {
                if (graph[i][j]) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    public void addEdge(int v, int w) {
        graph[v][w] = true;
        graph[w][v] = true;
    }

    public List<Integer> adj(int v) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i < graph.length; i++) {
            if (graph[v][i]) {
                list.add(i);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        UndirectedArrayGraph graph = new UndirectedArrayGraph(4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);

        System.out.println("Vertexes (4):" + graph.V());
        System.out.println("Edges (2):" + graph.E());
        System.out.println("Edges for 1 (3):" + Arrays.toString(graph.adj(1).toArray()));
        System.out.println("Edges for 3 (1,2):" + Arrays.toString(graph.adj(3).toArray()));
        System.out.println("Edges for 4 ():" + Arrays.toString(graph.adj(4).toArray()));
    }

}
