package org.lights1eep.graph.adjacencylist;

/**
 * 边表
 * @author lights1eep
 */
class EdgeNode {
    /**
     * 点索引
     */
    private int vertex;

    private int weight;
    private EdgeNode next;

    public EdgeNode(int vertex) {
        this(vertex, 0);
    }

    public EdgeNode(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
        this.next = null;
    }

    public void setNext(EdgeNode next) {
        this.next = next;
    }

    public EdgeNode getNext() {
        return next;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
