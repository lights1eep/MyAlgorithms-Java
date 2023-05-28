package org.lights1eep.skiplist;

/**
 * 抽线跳表
 * @author lights1eep
 */
public interface AbstractSkipList {
    /**
     * 返回target是否在跳表中
     * @param target 元素
     * @return  true在跳表中 false不在跳表中
     */
    boolean search(int target);

    /**
     * 向跳表中添加元素
     * @param num 添加的元素
     */
    void add(int num);

    /**
     * 删除跳表中的num元素，如果不存在则返回false，如果存在多个，则删除一个
     * @param num 删除的元素
     * @return true删除成功 false表示num不存在
     */
    boolean erase(int num);
}
