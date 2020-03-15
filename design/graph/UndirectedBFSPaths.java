package design.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndirectedBFSPaths {

    UndirectedGraph graph;
    int src;

    public UndirectedBFSPaths(UndirectedGraph graph, int src) {
        this.graph = graph;
        this.src = src;
    }

    public boolean hasPath(int dst) {
        int[] visited = new int[graph.V() + 1];
        visited[src] = src;
        traceGraph(src, dst, visited);

        return visited[dst] != 0;
    }

    public List<Integer> pathTo(int dst) {
        int[] visited = new int[graph.V() + 1];
        visited[src] = -1;
        traceGraph(src, dst, visited);

        if (visited[dst] == 0) {
            return new ArrayList<>();
        }
        int num = dst;
        Stack<Integer> stack = new Stack<>();
        while (num != -1) {
            stack.push(num);
            num = visited[num];
        }
        List<Integer> res = new ArrayList<>();
        while (stack.size() > 0) {
            res.add(stack.pop());
        }

        return res;
    }

    private void traceGraph(int src, int dst, int[] visited) {
        if (src == dst) {
            return;
        }
        for (int v : graph.adj(src)) {
            if (visited[v] == 0) {
                visited[v] = src;
                traceGraph(v, dst, visited);
            }
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);

        UndirectedBFSPaths path = new UndirectedBFSPaths(graph, 1);

        System.out.println("Path from 1 to 4 (false) - " + path.hasPath(4));
        System.out.println("Path from 1 to 4 ([]) - " + path.pathTo(4));
        System.out.println("Path from 1 to 2 (true) - " + path.hasPath(2));
        System.out.println("Path from 1 to 2 ([1,3,2]) - " + path.pathTo(2));

    }

}
