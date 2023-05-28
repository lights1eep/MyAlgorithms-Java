package org.lights1eep.graph.adjacencylist;


import org.lights1eep.graph.UndirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

/**
 * 无向无权邻接表图
 * @author lights1eep
 * @param <T>
 */
public class UndirectedUnweightedAdjacencyListGraph<T> extends AbstractAdjacencyListGraph<T> implements UndirectedGraph<T>, UnweightedGraph<T> {

    public UndirectedUnweightedAdjacencyListGraph() {
        super();
    }

    @Override
    public int getDegree(T vertex) {
        int degree = 0;
        EdgeNode edge = getVertexes().get(getVertexIndex(vertex)).getEdge();
        while (edge != null) {
            degree++;
            edge = edge.getNext();
        }
        return degree;
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && !containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            addEdgeWithoutWeight(startIndex, endIndex);
            addEdgeWithoutWeight(endIndex, startIndex);
            return true;
        }
        return false;
    }

    /**
     * 添加边
     * @param startIndex 起点索引
     * @param endIndex 重点索引
     */
    private void addEdgeWithoutWeight(int startIndex, int endIndex) {
        VertexNode vertexNode = getVertexes().get(startIndex);
        EdgeNode edge = vertexNode.getEdge();
        if (edge == null) {
            vertexNode.setEdge(new EdgeNode(endIndex));
        } else {
            while (edge.getNext() != null) {
                edge = edge.getNext();
            }
            edge.setNext(new EdgeNode(endIndex));
        }
    }

    @Override
    public int getEdgeSize() {
        return super.getEdgeSize() / 2;
    }
}
