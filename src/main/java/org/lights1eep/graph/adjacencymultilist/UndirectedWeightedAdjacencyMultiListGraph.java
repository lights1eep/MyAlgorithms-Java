package org.lights1eep.graph.adjacencymultilist;


import org.lights1eep.graph.UndirectedGraph;
import org.lights1eep.graph.WeightedGraph;

/**
 * 无向有权邻接多重表图
 * @author lights1eep
 * @param <T>
 */
public class UndirectedWeightedAdjacencyMultiListGraph<T> extends AbstractAdjacencyMultiListGraph<T> implements UndirectedGraph<T>, WeightedGraph<T> {
    @Override
    public int getDegree(T vertex) {
        int degree = 0;
        int vertexIndex = getVertexIndex(vertex);
        VertexNode tVertexNode = getVertexes().get(vertexIndex);
        EdgeNode edge = tVertexNode.getFirst();
        while (edge != null) {
            degree++;
            if (edge.getIVertex() == vertexIndex) {
                edge = edge.getILink();
            } else {
                edge = edge.getJLink();
            }
        }
        return degree;
    }

    @Override
    public boolean addEdge(T startVertex, T endVertex, int weight) {
        if (containsVertex(startVertex) && containsVertex(endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            VertexNode startNode = getVertexes().get(startIndex);
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

            VertexNode endNode = getVertexes().get(endIndex);
            EdgeNode edge2 = endNode.getFirst();
            EdgeNode pre2 = null;
            while (edge2 != null) {
                if (edge2.getIVertex() == endIndex) {
                    if (edge2.getJVertex() != startIndex) {
                        pre2 = edge2;
                        edge2 = edge2.getILink();
                    } else {
                        break;
                    }
                } else if (edge2.getJVertex() == endIndex) {
                    if (edge2.getIVertex() != startIndex) {
                        pre2 = edge2;
                        edge2 = edge2.getJLink();
                    } else {
                        break;
                    }
                }
            }
            EdgeNode edgeNode = new EdgeNode(startIndex, endIndex, weight);
            if (pre1 == null && pre2 == null) {
                startNode.setFirst(edgeNode);
                endNode.setFirst(edgeNode);
            } else if (pre1 == null && pre2 != null) {
                startNode.setFirst(edgeNode);
                if (pre2.getIVertex() == endIndex) {
                    pre2.setILink(edgeNode);
                } else {
                    pre2.setJLink(edgeNode);
                }
            } else if (pre1 != null && pre2 == null) {
                endNode.setFirst(edgeNode);
                if (pre1.getIVertex() == startIndex) {
                    pre1.setILink(edgeNode);
                } else {
                    pre1.setJLink(edgeNode);
                }
            } else {
                if (pre1.getIVertex() == startIndex) {
                    pre1.setILink(edgeNode);
                } else {
                    pre1.setJLink(edgeNode);
                }
                if (pre2.getIVertex() == endIndex) {
                    pre2.setILink(edgeNode);
                } else {
                    pre2.setJLink(edgeNode);
                }
            }
            addEdgeSize();
            return true;
        }
        return false;
    }

    @Override
    public boolean setEdgeWeight(T startVertex, T endVertex, int weight) {
        if (containsEdge(startVertex, endVertex) && weight > 0) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);

            VertexNode startNode = getVertexes().get(startIndex);
            EdgeNode edge = startNode.getFirst();
            while (edge != null) {
                if (edge.getIVertex() == startIndex) {
                    if (edge.getJVertex() == endIndex) {
                        edge.setWeight(weight);
                        break;
                    } else {
                        edge = edge.getILink();
                    }
                } else {
                    if (edge.getIVertex() == endIndex) {
                        edge.setWeight(weight);
                        break;
                    } else {
                        edge = edge.getJLink();
                    }
                }
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

            VertexNode startNode = getVertexes().get(startIndex);
            EdgeNode edge = startNode.getFirst();
            while (edge != null) {
                if (edge.getIVertex() == startIndex) {
                    if (edge.getJVertex() == endIndex) {
                        return edge.getWeight();
                    } else {
                        edge = edge.getILink();
                    }
                } else {
                    if (edge.getIVertex() == endIndex) {
                        return edge.getWeight();
                    } else {
                        edge = edge.getJLink();
                    }
                }
            }
        }
        return -1;
    }
}
