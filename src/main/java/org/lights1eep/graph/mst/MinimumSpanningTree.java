package org.lights1eep.graph.mst;



import org.lights1eep.graph.adjacencymatrix.UndirectedWeightedAdjacencyMatrixGraph;
import org.lights1eep.graph.edgeset.EdgeNode;
import org.lights1eep.graph.edgeset.UndirectedWeightedEdgeSetGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最小生成树
 * 仅实现无向有权图
 * @author lights1eep
 */
public class MinimumSpanningTree {
    /**
     * Prime算法
     * @param graph 无向有权图
     * @param <T>
     */
    public <T> void prime(UndirectedWeightedAdjacencyMatrixGraph<T> graph) {
        int vertexSize = graph.getVertexSize();
        List<List<Integer>> edgesMatrix = graph.getEdgesMatrix();
        // 记录已在树中的点到其他点index的最短距离 -1表示该点已经加入到树中
        int[] distances = new int[vertexSize];
        int[] vertexes = new int[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            distances[i] = edgesMatrix.get(0).get(i);
        }
        distances[0] = -1;
        for (int i = 1; i < vertexSize; i++) {
            int minDistance = Integer.MAX_VALUE;
            int vertex = 0;
            // 每次找到树中的点到其他点的最小距离的那个点
            for (int j = 0; j < vertexSize; j++) {
                if (distances[j] >= 0 && minDistance > distances[j]) {
                    minDistance = distances[j];
                    vertex = j;
                }
            }
            distances[vertex] = -1;
            System.out.println("edge: start: "
                    + graph.getVertexes().get(vertexes[vertex])
                    + " -> end: "
                    + graph.getVertexes().get(vertex)
                    + " weight: "
                    + graph.getEdgeWeight(graph.getVertexes().get(vertexes[vertex]), graph.getVertexes().get(vertex)));
            // 把点加入树中，并更新distances
            for (int j = 0; j < vertexSize; j++) {
                if (distances[j] >= 0 && edgesMatrix.get(vertex).get(j) < distances[j]) {
                    distances[j] = edgesMatrix.get(vertex).get(j);
                    vertexes[j] = vertex;
                }
            }
        }
    }

    /**
     * Kruskal算法
     * @param graph 无向有权图
     * @param <T>
     */
    public <T> void kruskal(UndirectedWeightedEdgeSetGraph<T> graph) {
        List<EdgeNode> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        int vertexSize = graph.getVertexSize();
        List<Integer> fathers = new ArrayList<>();
        List<T> vertexes = graph.getVertexes();
        for (int i = 0; i < vertexSize; i++) {
            fathers.add(i);
        }
        for (EdgeNode edge : edges) {
            int startVertex = edge.getStartVertex();
            int endVertex = edge.getEndVertex();
            int startFather = findFather(fathers, startVertex);
            int endFather = findFather(fathers, endVertex);
            if (startFather != endFather) {
                System.out.println("edge: start: "
                        + graph.getVertexes().get(startVertex)
                        + " -> end: "
                        + graph.getVertexes().get(endVertex)
                        + " weight: "
                        + edge.getWeight());
                fathers.set(endFather, startVertex);
            }
        }
    }

    /**
     * 并查集查找
     * @param fathers 并查集
     * @param index 索引
     * @return
     * @param <T>
     */
    private <T> int findFather(List<Integer> fathers, int index) {
        return fathers.get(index) == index ? index : findFather(fathers, fathers.get(index));
    }
}
