package org.lights1eep.graph;

/**
 * 无权图  接口
 * @author lights1eep
 * @param <T>
 */
public interface UnweightedGraph<T>{
    /**
     * 添加边
     * @param startVertex   端点
     * @param endVertex     端点
     * @return  true 添加成功 false 添加失败
     */
    boolean addEdge(T startVertex, T endVertex);
}
