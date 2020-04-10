package design.graph.max_flow;

public class DirectedEdgeWeighted {

    public int from, to;
    public int capacity, flow;

    public DirectedEdgeWeighted(int from, int to, int capacity) {
        this.to = to;
        this.from = from;
        this.capacity = capacity;
    }

    public int other(int v) {
        if (v == from) {
            return to;
        } else {
            return from;
        }
    }

    public int augmentic(int v) {
        if (v == from) {
            return capacity - flow;
        } else {
            return flow;
        }
    }

    public void addFlow(int v, int flow) {
        if (v == from) {
            this.flow += flow;
        } else {
            this.flow -= flow;
        }
    }

}
