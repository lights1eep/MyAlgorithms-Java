package org.lights1eep.queue;

/**
 * 抽象队列
 * @author lights1eep
 */
public interface MyAbstractQueue {
    /**
     * 获取队内元素数量
     * @return  队内元素数量
     */
    int size();

    /**
     * 判断队是否为空
     * @return  true表示空 false表示不空
     */
    boolean isEmpty();

    /**
     * 在队尾添加元素
     * @param value 需要入队的元素
     */
    void push(int value);

    /**
     * 弹出队头元素
     * @return  队头元素
     */
    int pop();

    /**
     * 获取队头元素
     * @return  队头元素
     */
    int front();

    /**
     * 获取队尾元素
     * @return  队尾元素
     */
    int back();
}
