package org.lights1eep.graph.edgeset;



import org.lights1eep.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象边表数组图
 * @author lights1eep
 * @param <T>
 */
public class AbstractEdgeSetGraph<T> implements Graph<T> {

    private List<T> vertexes;
    private List<EdgeNode> edges;

    AbstractEdgeSetGraph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public List<T> getVertexes() {
        return vertexes;
    }

    public List<EdgeNode> getEdges() {
        return edges;
    }

    @Override
    public boolean addVertex(T vertex) {
        if (!containsVertex(vertex)) {
            vertexes.add(vertex);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (containsVertex(vertex)) {
            boolean flag = false;
            for (EdgeNode edge : edges) {
                if (edge.containsVertex(getVertexIndex(vertex))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                vertexes.remove(getVertexIndex(vertex));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertexes.contains(vertex);
    }

    @Override
    public int getVertexIndex(T vertex) {
        return vertexes.indexOf(vertex);
    }

    private int getEdgeIndex(int startVertex, int endVertex) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStartVertex() == startVertex && edges.get(i).getEndVertex() == endVertex) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean removeEdge(T startVertex, T endVertex) {
        int edgeIndex = getEdgeIndex(getVertexIndex(startVertex), getVertexIndex(endVertex));
        if (edgeIndex != -1) {
            edges.remove(edgeIndex);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(T startVertex, T endVertex) {
        return getEdgeIndex(getVertexIndex(startVertex), getVertexIndex(endVertex)) != -1;
    }

    @Override
    public int getVertexSize() {
        return vertexes.size();
    }

    @Override
    public int getEdgeSize() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "AbstractEdgeSetGraph{" +
                "vertexes=" + vertexes +
                ", edges=" + edges +
                '}';
    }
}
