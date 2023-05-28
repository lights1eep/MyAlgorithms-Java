package org.lights1eep.array.sort;


import org.lights1eep.utils.SortUtil;

/**
 * 选择排序
 * @author lights1eep
 */
public class SelectionSort implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int index = i;
            for(int j = i; j < n ; j++) {
                if(nums[j] < nums[index]) {
                    index = j;
                }
            }
            SortUtil.swap(nums, index, i);
        }
    }
}
