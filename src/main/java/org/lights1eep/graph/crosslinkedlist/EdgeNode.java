package org.lights1eep.graph.crosslinkedlist;

/**
 * 十字链表图边表
 * @author lights1eep
 */
class EdgeNode {
    /**
     * 起点索引
     */
    private int startVertex;
    /**
     * 终点索引
     */
    private int endVertex;
    private int weight;
    /**
     * 连接下一个以startVertex为起点的边
     */
    private EdgeNode startLink;
    /**
     * 连接下一个以endVertex为终点的边
     */
    private EdgeNode endLink;

    public EdgeNode(int startVertex, int endVertex) {
        this(startVertex, endVertex, 0);
    }

    public EdgeNode(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        this.startLink = null;
        this.endLink = null;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public EdgeNode getStartLink() {
        return startLink;
    }

    public EdgeNode getEndLink() {
        return endLink;
    }

    public void setStartLink(EdgeNode startLink) {
        this.startLink = startLink;
    }

    public void setEndLink(EdgeNode endLink) {
        this.endLink = endLink;
    }

    @Override
    public String toString() {
        return "EdgeNode{" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                ", weight=" + weight +
                '}';
    }
}
