package org.lights1eep.graph.adjacencymatrix;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

/**
 * 有向无权邻接矩阵图
 * @author lights1eep
 * @param <T>
 */
public class DirectedUnweightedAdjacencyMatrixGraph<T> extends AbstractAdjacencyMatrixGraph<T> implements DirectedGraph<T>, UnweightedGraph<T> {

    public DirectedUnweightedAdjacencyMatrixGraph() {
        super(0);
    }


    @Override
    public boolean addEdge(T startVertex, T endVertex) {
        if(containsVertex(startVertex) && containsVertex(endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, 1);
            return true;
        }
        return false;
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
                degree += getEdgesMatrix().get(i).get(index) != 0 ? 1 : 0;
            }
            return degree;
        }
        return -1;
    }

    @Override
    public int getOutDegree(T vertex) {
        return containsVertex(vertex) ? (int)getEdgesMatrix().get(getVertexIndex(vertex)).stream().filter(num -> num != 0).count() : -1;
    }
}
