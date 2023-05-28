package org.lights1eep.graph;

/**
 * 有向图  接口
 * @author lights1eep
 * @param <T>
 */
public interface DirectedGraph<T> {
    /**
     * 获取点的入度
     * @param vertex    点
     * @return  入度 -1表示没有点
     */
    int getInDegree(T vertex);
    /**
     * 获取点的出度
     * @param vertex    点
     * @return  出度 -1表示没有点
     */
    int getOutDegree(T vertex);
}
