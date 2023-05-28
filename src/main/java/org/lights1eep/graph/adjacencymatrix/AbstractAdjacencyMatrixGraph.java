package org.lights1eep.graph.adjacencymatrix;


import org.lights1eep.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象邻接矩阵图
 * @author lights1eep
 * @param <T>
 */
public abstract class AbstractAdjacencyMatrixGraph<T>  implements Graph<T> {
    /**
     * 点集合
     */
    private List<T> vertexes;
    /**
     * 邻接矩阵
     */
    private List<List<Integer>> edgesMatrix;

    /**
     * 判断边是否存在的数字 无权图 0 有权图 Integer.MAX_VALUE
     */
    private int emptyNum;

    public AbstractAdjacencyMatrixGraph(int emptyNum) {
        vertexes = new ArrayList<>();
        edgesMatrix = new ArrayList<>();
        this.emptyNum = emptyNum;
    }

    public List<T> getVertexes() {
        return vertexes;
    }

    public List<List<Integer>> getEdgesMatrix() {
        return edgesMatrix;
    }


    public int getEmptyNum() {
        return emptyNum;
    }

    @Override
    public boolean addVertex(T vertex) {
        if(!containsVertex(vertex)) {
            getVertexes().add(vertex);
            getEdgesMatrix().stream().forEach(l -> l.add(emptyNum));
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < getVertexSize(); i++) {
                list.add(emptyNum);
            }
            edgesMatrix.add(list);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if(containsVertex(vertex)) {
            int vertexIndex = getVertexIndex(vertex);
            vertexes.remove(vertexIndex);
            edgesMatrix.remove(vertexIndex);
            edgesMatrix.stream().forEach(l -> l.remove(vertexIndex));
            return true;
        }
        return false;
    }

    @Override
    public int getVertexIndex(T vertex) {
        return containsVertex(vertex) ? vertexes.indexOf(vertex) : -1;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertexes.contains(vertex);
    }

    @Override
    public int getVertexSize() {
        return vertexes.size();
    }

    @Override
    public boolean removeEdge(T startVertex, T endVertex) {
        if(containsEdge(startVertex, endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            getEdgesMatrix().get(startIndex).set(endIndex, emptyNum);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(T startVertex, T endVertex) {
        if(containsVertex(startVertex) && containsVertex(endVertex)) {
            int startIndex = getVertexIndex(startVertex);
            int endIndex = getVertexIndex(endVertex);
            return getEdgesMatrix().get(startIndex).get(endIndex) != emptyNum;
        }
        return false;
    }

    @Override
    public int getEdgeSize() {
        return (int)getEdgesMatrix().stream().flatMap(l -> l.stream()).filter(num -> num != emptyNum).count();
    }


    @Override
    public String toString() {
        return "AbstractAdjacencyMatrixGraph{" +
                "vertexes=" + vertexes +
                ", edgesMatrix=" + edgesMatrix +
                '}';
    }
}
