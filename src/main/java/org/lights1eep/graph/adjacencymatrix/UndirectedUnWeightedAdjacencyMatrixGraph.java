package org.lights1eep.graph.adjacencymatrix;


import org.lights1eep.graph.UndirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

/**
 * 无向无权邻接矩阵图
 * @author lights1eep
 * @param <T>
 */
public class UndirectedUnWeightedAdjacencyMatrixGraph<T> extends AbstractAdjacencyMatrixGraph<T> implements UndirectedGraph<T>, UnweightedGraph<T> {


    public UndirectedUnWeightedAdjacencyMatrixGraph() {
        super(0);
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
    public boolean addEdge(T startVertex, T endVertex) {
        if(containsVertex(startVertex) && containsVertex(endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, 1);
            getEdgesMatrix().get(endIndex).set(startIndex, 1);
            return true;
        }
        return false;
    }

    @Override
    public int getEdgeSize() {
        return super.getEdgeSize() / 2;
    }
}
