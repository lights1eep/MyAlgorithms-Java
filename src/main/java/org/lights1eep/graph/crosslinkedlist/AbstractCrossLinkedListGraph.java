package org.lights1eep.graph.crosslinkedlist;



import org.lights1eep.graph.Graph;

import java.util.ArrayList;
import java.util.List;


/**
 * 十字链表图
 * @author lights1eep
 * @param <T>
 */
public class AbstractCrossLinkedListGraph<T> implements Graph<T> {

    private List<VertexNode<T>> vertexes;

    public AbstractCrossLinkedListGraph() {
        this.vertexes = new ArrayList<>();
    }

    public List<VertexNode<T>> getVertexes() {
        return vertexes;
    }

    @Override
    public boolean addVertex(T vertex) {
        if (!containsVertex(vertex)) {
            vertexes.add(new VertexNode<T>(vertex));
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(T vertex) {
        VertexNode vertexNode = vertexes.get(getVertexIndex(vertex));
        if (vertexNode.getFirstIn() == null && vertexNode.getFirstOut() == null) {
            vertexes.remove(getVertexIndex(vertex));
            return true;
        }
        return false;
    }

    @Override
    public boolean containsVertex(T vertex) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (vertexes.get(i).getVertex() == vertex) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVertexIndex(T vertex) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (vertexes.get(i).getVertex() == vertex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean removeEdge(T startVertex, T endVertex) {
        if (containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            EdgeNode out = vertexes.get(startIndex).getFirstOut();
            EdgeNode preOut = null;
            while (out != null) {
                if (out.getEndVertex() == endIndex) {
                    break;
                }
                preOut = out;
                out = out.getStartLink();
            }

            EdgeNode in = vertexes.get(endIndex).getFirstIn();
            EdgeNode preIn = null;
            while (in != null) {
                if (in.getEndVertex() == startIndex) {
                    break;
                }
                preIn = in;
                in = in.getEndLink();
            }

            EdgeNode newOut = out.getStartLink();
            EdgeNode newIn = out.getEndLink();

            if (preOut == null) {
                vertexes.get(startIndex).setFirstOut(newOut);
            } else {
                preOut.setStartLink(newOut);
            }

            if (preIn == null) {
                vertexes.get(endIndex).setFirstIn(newIn);
            } else {
                preIn.setEndLink(newIn);
            }

        }
        return false;
    }

    @Override
    public boolean containsEdge(T startVertex, T endVertex) {
        int startIndex = getVertexIndex(startVertex);
        int endIndex = getVertexIndex(endVertex);
        EdgeNode out = vertexes.get(startIndex).getFirstOut();
        while (out != null) {
            if (out.getEndVertex() == endIndex) {
                return true;
            }
            out = out.getStartLink();
        }
        return false;
    }

    @Override
    public int getVertexSize() {
        return vertexes.size();
    }

    @Override
    public int getEdgeSize() {
        int size = 0;
        for (VertexNode<T> vertex : vertexes) {
            EdgeNode firstOut = vertex.getFirstOut();
            while (firstOut != null) {
                size++;
                firstOut = firstOut.getStartLink();
            }
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AbstractCrossLinkedListGraph{\n");
        for (VertexNode<T> vertex : vertexes) {
            sb.append(vertex);
        }
        sb.append("}");
        return sb.toString();
    }
}
