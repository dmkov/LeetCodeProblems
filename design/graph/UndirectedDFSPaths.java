package design.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UndirectedDFSPaths {

    UndirectedListGraph graph;
    int src;

    public UndirectedDFSPaths(UndirectedListGraph graph, int src) {
        this.graph = graph;
        this.src = src;
    }

    public boolean hasPath(int dst) {
        return pathTo(dst) != null;
    }

    public List<Integer> pathTo(int dst) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        return getPath(src, dst, path, visited);
    }

    private List<Integer> getPath(int src, int dst, List<Integer> path, Set<Integer> visited) {
        visited.add(src);
        path.add(src);
        if (src == dst) {
            return path;
        }
        for (int v : graph.adj(src)) {
            if (!visited.contains(v) && getPath(v, dst, path, visited) != null) {
                return path;
            }
        }
        path.remove(path.size()-1);

        return null;
    }

    public static void main(String[] args) {
        UndirectedListGraph graph = new UndirectedListGraph(4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);

        UndirectedDFSPaths path = new UndirectedDFSPaths(graph, 1);

        System.out.println("Path from 1 to 4 (false) - " + path.hasPath(4));
        System.out.println("Path from 1 to 4 ([]) - " + path.pathTo(4));
        System.out.println("Path from 1 to 2 (true) - " + path.hasPath(2));
        System.out.println("Path from 1 to 2 ([1,3,2]) - " + path.pathTo(2));

    }

}
