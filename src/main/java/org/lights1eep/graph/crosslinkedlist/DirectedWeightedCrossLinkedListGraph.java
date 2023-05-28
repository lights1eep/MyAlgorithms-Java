package org.lights1eep.graph.crosslinkedlist;


import org.lights1eep.graph.DirectedGraph;
import org.lights1eep.graph.WeightedGraph;

/**
 * 有向有权十字链表图
 * @author lights1eep
 * @param <T>
 */
public class DirectedWeightedCrossLinkedListGraph<T> extends AbstractCrossLinkedListGraph<T> implements DirectedGraph<T>, WeightedGraph<T> {
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
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            EdgeNode edgeNode = new EdgeNode(startIndex, endIndex, weight);

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

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if (containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            EdgeNode out = getVertexes().get(startIndex).getFirstOut();
            while (out.getStartLink() != null) {
                if (out.getEndVertex() == endIndex) {
                    out.setWeight(weight);
                    break;
                }
                out = out.getStartLink();
            }
            return true;
        }
        return false;
    }

    @Override
    public int getEdgeWeight(T startVertex, T endVertex) {
        if (containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            EdgeNode out = getVertexes().get(startIndex).getFirstOut();
            while (out.getStartLink() != null) {
                if (out.getEndVertex() == endIndex) {
                    return out.getWeight();
                }
                out = out.getStartLink();
            }
        }
        return -1;
    }
}
