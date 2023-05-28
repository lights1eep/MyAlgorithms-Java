package org.lights1eep.graph.adjacencymultilist;

/**
 * 边节点
 * @author lights1eep
 */
class EdgeNode {
    /**
     * 起点索引
     */
    private int iVertex;
    /**
     * 终点索引
     */
    private int jVertex;
    /**
     * iVertex的下一条边
     */
    private EdgeNode iLink;
    /**
     * jVertex的下一条边
     */
    private EdgeNode jLink;
    private int weight;

    public EdgeNode(int iVertex, int jVertex) {
        this(iVertex, jVertex, 0);
    }

    public EdgeNode(int iVertex, int jVertex, int weight) {
        this.iVertex = iVertex;
        this.jVertex = jVertex;
        this.weight = weight;
        this.iLink = null;
        this.jLink = null;
    }

    public int getIVertex() {
        return iVertex;
    }

    public int getJVertex() {
        return jVertex;
    }

    public EdgeNode getILink() {
        return iLink;
    }

    public EdgeNode getJLink() {
        return jLink;
    }

    public int getWeight() {
        return weight;
    }

    public void setILink(EdgeNode iLink) {
        this.iLink = iLink;
    }

    public void setJLink(EdgeNode jLink) {
        this.jLink = jLink;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeNode{" +
                "iVertex=" + iVertex +
                ", jVertex=" + jVertex +
                ", weight=" + weight +
                '}';
    }
}
