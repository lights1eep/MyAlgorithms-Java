package org.lights1eep.graph;

/**
 * 有权图 接口
 * @author lights1eep
 * @param <T>
 */
public interface WeightedGraph<T> {
    /**
     * 添加带权边
     * @param startVertex   端点
     * @param endVertex     端点
     * @param weight        权重
     * @return  true 添加成功 false 添加失败
     */
    boolean addEdge(T startVertex, T endVertex, int weight);

    /**
     * 设置带权边权重
     * @param startVertex   端点
     * @param endVertex     端点
     * @param weight        权重
     * @return  true 设置成功 false 设置失败
     */
    boolean setEdgeWeight(T startVertex, T endVertex, int weight);

    /**
     * 获取边权重
     * @param startVertex   端点
     * @param endVertex     端点
     * @return  权重   -1 表示边不存在
     */
    int getEdgeWeight(T startVertex, T endVertex);
}
