package org.lights1eep.array.sort;

import org.lights1eep.utils.SortUtil;
/**
 * 冒泡排序
 * @author lights1eep
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(nums[j] > nums[j + 1]) {
                    SortUtil.swap(nums, j, j + 1);
                }
            }
        }
    }
}
