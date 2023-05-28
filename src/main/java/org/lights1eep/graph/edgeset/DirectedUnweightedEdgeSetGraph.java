package org.lights1eep.graph.edgeset;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

import java.util.List;

/**
 * 有向无权边表数组图
 * @author lights1eep
 * @param <T>
 */
public class DirectedUnweightedEdgeSetGraph<T> extends AbstractEdgeSetGraph<T> implements DirectedGraph<T>, UnweightedGraph<T> {
    @Override
    public int getInDegree(T vertex) {
        int degree = 0;
        int vertexIndex = getVertexIndex(vertex);
        List<EdgeNode> edges = getEdges();
        for (EdgeNode edge : edges) {
            if (edge.isEndVertex(vertexIndex)) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public int getOutDegree(T vertex) {
        int degree = 0;
        int vertexIndex = getVertexIndex(vertex);
        List<EdgeNode> edges = getEdges();
        for (EdgeNode edge : edges) {
            if (edge.isStartVertex(vertexIndex)) {
                degree++;
            }
        }
        return degree;
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex) {
        if (containsVertex(startVertex) && containsVertex(endVertex)) {
            getEdges().add(new EdgeNode(getVertexIndex(startVertex), getVertexIndex(endVertex)));
            return true;
        }
        return false;
    }
}
