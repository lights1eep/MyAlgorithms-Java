package org.lights1eep.graph.adjacencymultilist;



import org.lights1eep.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接多重表
 * @author lights1eep
 * @param <T>
 */
public class AbstractAdjacencyMultiListGraph<T> implements Graph<T> {

    private List<VertexNode<T>> vertexes;
    private int edgeSize;


    AbstractAdjacencyMultiListGraph() {
        this.vertexes = new ArrayList<>();
        this.edgeSize = 0;
    }

    public List<VertexNode<T>> getVertexes() {
        return vertexes;
    }



    @Override
    public boolean addVertex(T vertex) {
        if (!containsVertex(vertex)) {
            vertexes.add(new VertexNode<>(vertex));
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (containsVertex(vertex)) {
            int vertexIndex = getVertexIndex(vertex);
            EdgeNode first = vertexes.get(vertexIndex).getFirst();
            if (first == null) {
                vertexes.remove(vertexIndex);
                return true;
            }
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

            VertexNode startNode = vertexes.get(startIndex);
            EdgeNode edge1 = startNode.getFirst();
            EdgeNode pre1 = null;
            while (edge1 != null) {
                if (edge1.getIVertex() == startIndex) {
                    if (edge1.getJVertex() != endIndex) {
                        pre1 = edge1;
                        edge1 = edge1.getILink();
                    } else {
                        break;
                    }
                } else if (edge1.getJVertex() == startIndex) {
                    if (edge1.getIVertex() != endIndex) {
                        pre1 = edge1;
                        edge1 = edge1.getJLink();
                    } else {
                        break;
                    }
                }
            }

            VertexNode endNode = vertexes.get(endIndex);
            EdgeNode edge2 = startNode.getFirst();
            EdgeNode pre2 = null;
            while (edge2 != null) {
                if (edge2.getIVertex() == endIndex) {
                    if (edge1.getJVertex() != startIndex) {
                        pre2 = edge2;
                        edge2 = edge2.getILink();
                    } else {
                        break;
                    }
                } else if (edge2.getJVertex() == endIndex) {
                    if (edge2.getIVertex() != startIndex) {
                        pre1 = edge1;
                        edge2 = edge1.getJLink();
                    } else {
                        break;
                    }
                }
            }

            EdgeNode iLink = edge1.getILink();
            EdgeNode jLink = edge1.getJLink();

            if (pre1 == null) {
                startNode.setFirst(iLink);
            } else {
                pre1.setILink(iLink);
            }

            if (pre2 == null) {
                endNode.setFirst(jLink);
            } else {
                pre2.setJLink(jLink);
            }

            edge1.setILink(null);
            edge1.setJLink(null);
            edgeSize--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(T startVertex, T endVertex) {
        if (containsVertex(startVertex) && containsVertex(endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            VertexNode vertexNode = vertexes.get(startIndex);
            EdgeNode edge = vertexNode.getFirst();
            while (edge != null) {
                if (edge.getIVertex() == startIndex) {
                    if (edge.getJVertex() == endIndex) {
                        return true;
                    } else {
                        edge = edge.getJLink();
                    }
                } else if (edge.getJVertex() == startIndex) {
                    if (edge.getIVertex() == endIndex) {
                        return true;
                    } else {
                        edge = edge.getILink();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getVertexSize() {
        return vertexes.size();
    }

    @Override
    public int getEdgeSize() {
        return edgeSize;
    }

    protected void addEdgeSize() {
        edgeSize++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AbstractAdjacencyMultiListGraph{\n");
        for (VertexNode<T> vertexNode : vertexes) {
            sb.append(vertexNode);
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
