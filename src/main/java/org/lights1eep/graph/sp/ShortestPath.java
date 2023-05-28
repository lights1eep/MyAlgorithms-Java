package org.lights1eep.graph.sp;


import org.lights1eep.graph.adjacencylist.DirectedWeightedAdjacencyListGraph;
import org.lights1eep.graph.adjacencymatrix.DirectedWeightedAdjacencyMatrixGraph;

import java.util.Arrays;
import java.util.List;

/**
 * 最短路径
 * @author lights1eep
 */
public class ShortestPath {
    /**
     * Dijkstra算法
     *
     * @param graph  有向有权图
     * @param vertex 起始点
     * @param <T>
     */
    public <T> void dijkstra(DirectedWeightedAdjacencyListGraph<T> graph, T vertex) {
        if (!graph.containsVertex(vertex)) {
            return;
        }
        int startIndex = graph.getVertexIndex(vertex);
        int vertexSize = graph.getVertexSize();
        int[] res = new int[vertexSize];
        boolean[] visited = new boolean[vertexSize];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[startIndex] = 0;
        visited[startIndex] = true;
        List<Integer> startNeighbors = graph.getNeighbors(graph.getVertexes().get(startIndex).getVertex());
        for (int neighbor : startNeighbors) {
            int weight = graph.getEdgeWeight(graph.getVertexes().get(startIndex).getVertex(),
                    graph.getVertexes().get(neighbor).getVertex());
            res[neighbor] = weight;
        }

        for (int i = 0; i < vertexSize - 1; i++) {
            int minWeight = Integer.MAX_VALUE;
            int minWeightIndex = 0;
            for (int j = 0; j < vertexSize; j++) {
                if (!visited[j] && res[j] < minWeight) {
                    minWeight = res[j];
                    minWeightIndex = j;
                }
            }
            res[minWeightIndex] = minWeight;
            visited[minWeightIndex] = true;

            for (int j = 0; j < vertexSize; j++) {
                if (!visited[j] && graph.containsEdge(graph.getVertexes().get(minWeightIndex).getVertex(),
                        graph.getVertexes().get(j).getVertex())) {
                    int newDistance = res[minWeightIndex] + graph.getEdgeWeight(graph.getVertexes().get(minWeightIndex).getVertex(),
                            graph.getVertexes().get(j).getVertex());
                    if (newDistance < res[j] || !visited[j]) {
                        res[j] = newDistance;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }

    /**
     * Floyd算法
     * @param graph 有向有权图
     * @param <T>
     */
    public <T> void floyd(DirectedWeightedAdjacencyMatrixGraph<T> graph) {
        int vertexSize = graph.getVertexSize();
        int[][] res = new int[vertexSize][vertexSize];
        List<List<Integer>> edgesMatrix = graph.getEdgesMatrix();
        for (int i = 0; i < vertexSize; i++) {
            for (int j = 0; j < vertexSize; j++) {
                res[i][j] = edgesMatrix.get(i).get(j);
            }
            res[i][i] = 0;
        }

        for (int i = 0; i < vertexSize; i++) {
            for (int j = 0; j < vertexSize; j++) {
                for (int k = 0; k < vertexSize; k++) {
                    if (res[j][i] != Integer.MAX_VALUE && res[i][k] != Integer.MAX_VALUE && res[j][k] > res[j][i] + res[i][k]) {
                        res[j][k] = res[j][i] + res[i][k];
                    }
                }
            }
        }
        Arrays.stream(res).forEach(r -> System.out.println(Arrays.toString(r)));
    }
}