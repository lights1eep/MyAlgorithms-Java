package org.lights1eep.utils;

/**
 * 排序工具类
 * @author lights1eep
 */
public class SortUtil {
    /**
     * 交换数组中指定位置的两数
     * @param nums 数组
     * @param i 位置1
     * @param j 位置2
     */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
