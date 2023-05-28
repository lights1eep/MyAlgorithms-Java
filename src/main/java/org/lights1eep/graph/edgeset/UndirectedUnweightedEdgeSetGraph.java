package org.lights1eep.graph.edgeset;


import org.lights1eep.graph.UndirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

import java.util.List;

/**
 * 无向无权边表数组图
 * @author lights1eep
 * @param <T>
 */
public class UndirectedUnweightedEdgeSetGraph<T> extends AbstractEdgeSetGraph<T> implements UndirectedGraph<T>, UnweightedGraph<T> {
    @Override
    public int getDegree(T vertex) {
        int degree = 0;
        List<EdgeNode> edges = getEdges();
        for (EdgeNode edge : edges) {
            if (edge.containsVertex(getVertexIndex(vertex))) {
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
