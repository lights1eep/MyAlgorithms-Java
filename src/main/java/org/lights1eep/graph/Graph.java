package org.lights1eep.graph;

/**
 * 图接口
 * @author lights1eep
 * @param <T>
 */
public interface Graph<T> {
    /**
     * 向图中添加顶点
     * @param vertex    顶点
     */
    boolean addVertex(T vertex);

    /**
     * 移除点
     * @param vertex    要移除的点
     * @return  true 移除成功 false 移除失败
     */
    boolean removeVertex(T vertex);

    /**
     * 图中是否包含点
     * @param vertex    点
     * @return  true 包含 false 不包含
     */
    boolean containsVertex(T vertex);

    /**
     * 获取点在图中的索引
     * @param vertex    点
     * @return  索引  -1表示不存在
     */
    int getVertexIndex(T vertex);

    /**
     * 移除边
     * @param startVertex   边的端点
     * @param endVertex     边的端点
     * @return  true 移除成功 false 移除失败
     */
    boolean removeEdge(T startVertex, T endVertex);

    /**
     * 图中是否包含边
     * @param startVertex   边的端点
     * @param endVertex     边的端点
     * @return  true 包含 false 不包含
     */
    boolean containsEdge(T startVertex, T endVertex);

    /**
     * 获取图中点数目
     * @return  点数目
     */
    int getVertexSize();

    /**
     * 获取图中边数目
     * @return  边数目
     */
    int getEdgeSize();

}
