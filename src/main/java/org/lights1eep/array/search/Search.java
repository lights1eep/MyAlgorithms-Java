package org.lights1eep.array.search;

/**
 * 查找接口
 * @author lights1eep
 */
public interface Search {
    /**
     * 查找
     * @param nums 数组
     * @param value 被查找的值
     * @return -1 没有查找 >=0 查到了
     */
    int search(int[] nums, int value);
}
