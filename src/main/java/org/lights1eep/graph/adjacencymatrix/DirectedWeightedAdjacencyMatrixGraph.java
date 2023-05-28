package org.lights1eep.graph.adjacencymatrix;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.WeightedGraph;

/**
 * 有向有权邻接矩阵图
 * @author lights1eep
 * @param <T>
 */
public class DirectedWeightedAdjacencyMatrixGraph<T> extends AbstractAdjacencyMatrixGraph<T> implements DirectedGraph<T>, WeightedGraph<T> {

    public DirectedWeightedAdjacencyMatrixGraph() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, weight);
            return true;
        }
        return false;
    }

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if (containsEdge(startVertex, endVertex) && weight > 0) {
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
    public int getInDegree(T vertex) {
        if(containsVertex(vertex)) {
            int index = getVertexIndex(vertex);
            int degree = 0;
            for(int i = 0; i < getVertexSize(); i++) {
                if(i == index) {
                    continue;
                }
                degree += getEdgesMatrix().get(i).get(index) != Integer.MAX_VALUE ? 1 : 0;
            }
            return degree;
        }
        return -1;
    }

    @Override
    public int getOutDegree(T vertex) {
        return containsVertex(vertex) ? (int)getEdgesMatrix().get(getVertexIndex(vertex)).stream().filter(num -> num != Integer.MAX_VALUE).count() : -1;
    }
}
