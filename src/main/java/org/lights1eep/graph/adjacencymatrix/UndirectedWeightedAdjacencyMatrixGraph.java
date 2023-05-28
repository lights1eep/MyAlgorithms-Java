package org.lights1eep.graph.adjacencymatrix;


import org.lights1eep.graph.UndirectedGraph;
import org.lights1eep.graph.WeightedGraph;

/**
 * 无向有权邻接矩阵图
 * @author lights1eep
 * @param <T>
 */
public class UndirectedWeightedAdjacencyMatrixGraph<T> extends AbstractAdjacencyMatrixGraph<T> implements UndirectedGraph<T>, WeightedGraph<T> {


    public UndirectedWeightedAdjacencyMatrixGraph() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public boolean removeEdge(T startVertex, T endVertex) {
        return super.removeEdge(startVertex, endVertex) && super.removeEdge(endVertex, startVertex);
    }

    @Override
    public int getDegree(T vertex) {
        return containsVertex(vertex) ? (int)getEdgesMatrix().get(getVertexIndex(vertex)).stream().filter(num -> num != 0).count() : -1;
    }


    @Override
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, weight);
            getEdgesMatrix().get(endIndex).set(startIndex, weight);
            return true;
        }
        return false;
    }

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if(containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, weight);
            return true;
        }
        return false;
    }

    @Override
    public int getEdgeWeight(T startVertex, T endVertex) {
        int startIndex = getVertexIndex(startVertex);
        int endIndex = getVertexIndex(endVertex);
        return containsEdge(startVertex, endVertex) ? getEdgesMatrix().get(startIndex).get(endIndex) : -1;
    }

    @Override
    public int getEdgeSize() {
        return super.getEdgeSize() / 2;
    }
}
