package org.lights1eep.graph.crosslinkedlist;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.UnweightedGraph;

/**
 * 有向无权十字链表图
 * @author lights1eep
 * @param <T>
 */
public class DirectedUnweightedCrossLinkedListGraph<T> extends AbstractCrossLinkedListGraph<T> implements DirectedGraph<T>, UnweightedGraph<T> {
    @Override
    public int getInDegree(T vertex) {
        int degree = 0;
        int index = getVertexIndex(vertex);
        EdgeNode in = getVertexes().get(index).getFirstIn();
        while (in != null) {
            degree++;
            in = in.getEndLink();
        }
        return degree;
    }

    @Override
    public int getOutDegree(T vertex) {
        int degree = 0;
        int index = getVertexIndex(vertex);
        EdgeNode out = getVertexes().get(index).getFirstOut();
        while (out != null) {
            degree++;
            out = out.getStartLink();
        }
        return degree;
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex) {
        if (containsVertex(startVertex) && containsVertex(endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            EdgeNode edgeNode = new EdgeNode(startIndex, endIndex);

            EdgeNode out = getVertexes().get(startIndex).getFirstOut();
            if (out == null) {
                getVertexes().get(startIndex).setFirstOut(edgeNode);
            } else {
                while (out.getStartLink() != null) {
                    out = out.getStartLink();
                }
                out.setStartLink(edgeNode);
            }



            EdgeNode in = getVertexes().get(endIndex).getFirstIn();
            if (in == null) {
                getVertexes().get(endIndex).setFirstIn(edgeNode);
            } else {
                while (in.getEndLink() != null) {
                    in = in.getEndLink();
                }
                in.setEndLink(edgeNode);
            }

            return true;
        }
        return false;
    }
}
