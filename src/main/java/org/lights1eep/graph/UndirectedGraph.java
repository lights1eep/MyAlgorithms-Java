package org.lights1eep.graph;

/**
 * 无向图 接口
 * @author lights1eep
 * @param <T>
 */
public interface UndirectedGraph<T> {
    /**
     * 获取节点度数
     * @param vertex 节点值
     * @return 度数
     */
    int getDegree(T vertex);
}
