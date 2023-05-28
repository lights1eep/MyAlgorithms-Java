package org.lights1eep.graph.edgeset;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.WeightedGraph;

import java.util.List;

/**
 * 有向有权边表数组图
 * @author lights1eep
 * @param <T>
 */
public class DirectedWeightedEdgeSetGraph<T> extends AbstractEdgeSetGraph<T> implements DirectedGraph<T>, WeightedGraph<T> {
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
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            getEdges().add(new EdgeNode(getVertexIndex(startVertex), getVertexIndex(endVertex), weight));
            getEdges().add(new EdgeNode(getVertexIndex(endVertex), getVertexIndex(startVertex),weight));
            return true;
        }
        return false;
    }

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if (containsEdge(startVertex, endVertex) && weight > 0) {
            List<EdgeNode> edges = getEdges();
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            for (EdgeNode edge : edges) {
                if (edge.containsVertex(startIndex) && edge.containsVertex(endIndex)) {
                    edge.setWeight(weight);
                }
            }
        }
        return false;
    }

    @Override
    public int getEdgeWeight(T startVertex, T endVertex) {
        if (containsEdge(startVertex, endVertex)) {
            List<EdgeNode> edges = getEdges();
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            for (EdgeNode edge : edges) {
                if (edge.containsVertex(startIndex) && edge.containsVertex(endIndex)) {
                    return edge.getWeight();
                }
            }
        }
        return -1;
    }
}
