package org.lights1eep.graph.adjacencylist;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.WeightedGraph;

/**
 * 有向有权邻接表图
 * @author lights1eep
 * @param <T>
 */
public class DirectedWeightedAdjacencyListGraph<T> extends AbstractAdjacencyListGraph<T> implements DirectedGraph<T>, WeightedGraph<T> {
    @Override
    public int getInDegree(T vertex) {
        int degree = 0;
        int vertexIndex = getVertexIndex(vertex);
        for (int i = 0; i < getVertexSize(); i++) {
            VertexNode vertexNode = getVertexes().get(i);
            EdgeNode edge = vertexNode.getEdge();
            while (edge != null) {
                if (edge.getVertex() == vertexIndex) {
                    degree++;
                    break;
                }
            }
        }
        return degree;
    }

    @Override
    public int getOutDegree(T vertex) {
        int degree = 0;
        EdgeNode edge = getVertexes().get(getVertexIndex(vertex)).getEdge();
        while (edge != null) {
            degree++;
            edge = edge.getNext();
        }
        return degree;
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            addEdgeWithWeight(startIndex, endIndex, weight);
            return true;
        }
        return false;
    }

    /**
     * 添加边权重
     * @param startIndex 起点索引
     * @param endIndex 终点索引
     * @param weight 权重
     */
    private void addEdgeWithWeight(int startIndex, int endIndex, int weight) {
        VertexNode vertexNode = getVertexes().get(startIndex);
        EdgeNode edge = vertexNode.getEdge();
        if (edge == null) {
            vertexNode.setEdge(new EdgeNode(endIndex, weight));
        } else {
            while (edge.getNext() != null) {
                edge = edge.getNext();
            }
            edge.setNext(new EdgeNode(endIndex, weight));
        }
    }

    /**
     * 通过点索引设置权重
     * @param startIndex 起点索引
     * @param endIndex 终点索引
     * @param weight 权重
     */
    private void setEdgeWeightWithIndex(int startIndex, int endIndex, int weight) {
        EdgeNode edge = getVertexes().get(startIndex).getEdge();
        while (edge != null) {
            if (edge.getVertex() == endIndex) {
                edge.setWeight(weight);
                return;
            }
            edge = edge.getNext();
        }
    }

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if (containsEdge(startVertex, endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            setEdgeWeightWithIndex(startIndex, endIndex, weight);
            return true;
        }
        return false;
    }

    @Override
    public int getEdgeWeight(T startVertex, T endVertex) {
        if (containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            EdgeNode edge = getVertexes().get(startIndex).getEdge();
            while (edge != null) {
                if (edge.getVertex() == endIndex) {
                    return edge.getWeight();
                }
                edge = edge.getNext();
            }
        }
        return -1;
    }
}
