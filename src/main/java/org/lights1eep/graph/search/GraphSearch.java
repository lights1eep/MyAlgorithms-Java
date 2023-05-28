package org.lights1eep.graph.search;



import org.lights1eep.graph.adjacencymatrix.AbstractAdjacencyMatrixGraph;
import org.lights1eep.graph.adjacencymatrix.DirectedUnweightedAdjacencyMatrixGraph;

import java.util.*;

/**
 * 图的遍历
 * 仅实现了邻接矩阵图的相关遍历方法
 * @author lights1eep
 */
public class GraphSearch {
    /**
     * 深度优先遍历 非递归 邻接矩阵图
     * @param graph 邻接矩阵图
     * @param startIndex    遍历起点的索引
     * @param <T>   节点类型
     */
    public <T> void depthFirstSearch(AbstractAdjacencyMatrixGraph<T> graph, int startIndex) {
        int n = graph.getVertexSize();
        int[] visited = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;
        stack.addLast(startIndex);
        while (!stack.isEmpty()) {
            // 只要可以深入遍历就一直入栈
            while (true) {
                int pointIndex = stack.peekLast();
                result.add(pointIndex);
                visited[pointIndex] = 1;
                count++;
                boolean flag = false;
                for (int i = 0; i < n; i++) {
                    if (graph.containsEdge(graph.getVertexes().get(pointIndex), graph.getVertexes().get(i)) && visited[i] == 0) {
                        stack.addLast(i);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            // 栈顶节点不能继续深入遍历，弹出，直到可以继续深入遍历为止
            while (!stack.isEmpty() && count < n) {
                int pointIndex = stack.pollLast();
                boolean flag = false;
                for (int i = 0; i < n; i++) {
                    if (graph.containsEdge(graph.getVertexes().get(pointIndex), graph.getVertexes().get(i)) && visited[i] == 0) {
                        stack.addLast(i);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (count == n) {
                break;
            }
        }
        System.out.println(result);
    }

    /**
     * 广度优先遍历 邻接矩阵图
     * @param graph 邻接矩阵图
     * @param startIndex    遍历起点的索引
     * @param <T>   节点类型
     */
    public<T> void breadthFirstSearch(AbstractAdjacencyMatrixGraph<T> graph, int startIndex) {
        int n = graph.getVertexSize();
        int[] visited = new int[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        queue.add(startIndex);
        visited[startIndex] = 1;
        while (!queue.isEmpty()) {
            int pointIndex = queue.poll();
            result.add(pointIndex);
            count++;
            if (count == n) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if (graph.containsEdge(graph.getVertexes().get(pointIndex), graph.getVertexes().get(i)) && visited[i] == 0) {
                    queue.add(i);
                    visited[i] = 1;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 拓扑遍历
     * @param graph 有向图
     * @param <T>   节点类型
     */
    public <T> void topologySearch(DirectedUnweightedAdjacencyMatrixGraph<T> graph) {
        int n = graph.getVertexSize();
        int[] inDegrees = new int[n];
        for (int i = 0; i < n; i++) {
            inDegrees[i] = graph.getInDegree(graph.getVertexes().get(i));
        }
        List<Integer> result = new ArrayList<>();
        while (result.size() != n) {
            int pointIndex = 0;
            for (int i = 0; i < n; i++) {
                if (inDegrees[i] == 0) {
                    pointIndex = i;
                    result.add(i);
                    inDegrees[i] = -1;
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (inDegrees[i] != -1 && graph.containsEdge(graph.getVertexes().get(pointIndex), graph.getVertexes().get(i))) {
                    inDegrees[i] -= 1;
                }
            }
        }
        System.out.println(result);
    }
}
