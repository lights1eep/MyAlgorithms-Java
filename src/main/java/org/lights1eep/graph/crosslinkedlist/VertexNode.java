package org.lights1eep.graph.crosslinkedlist;


/**
 * 边节点
 * @author lights1eep
 * @param <T>
 */
class VertexNode<T> {
    private T vertex;
    /**
     * 第一个以该节点为终点的边
     */
    private EdgeNode firstIn;
    /**
     * 第一个以该节点为起点的边
     */
    private EdgeNode firstOut;

    public VertexNode(T vertex) {
        this.vertex = vertex;
        this.firstIn = null;
        this.firstOut = null;
    }

    public T getVertex() {
        return vertex;
    }

    public EdgeNode getFirstIn() {
        return firstIn;
    }

    public EdgeNode getFirstOut() {
        return firstOut;
    }

    public void setFirstIn(EdgeNode firstIn) {
        this.firstIn = firstIn;
    }

    public void setFirstOut(EdgeNode firstOut) {
        this.firstOut = firstOut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" vertex: " + vertex);
        sb.append(" edge: [");
        EdgeNode node = firstOut;
        while (node != null) {
            sb.append(node);
            node = node.getStartLink();
        }
        sb.append("] \n");
        return sb.toString();
    }
}