package org.lights1eep.graph.adjacencylist;


/**
 * 边节点
 * @author lights1eep
 * @param <T>
 */
public class VertexNode<T> {
    private T vertex;
    private EdgeNode edge;

    public VertexNode(T vertex) {
        this.vertex = vertex;
    }

    public void setEdge(EdgeNode edge) {
        this.edge = edge;
    }

    public EdgeNode getEdge() {
        return edge;
    }

    public T getVertex() {
        return vertex;
    }
}
