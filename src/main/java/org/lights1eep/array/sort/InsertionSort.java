package org.lights1eep.array.sort;
/**
 * 插入排序
 * @author lights1eep
 */
public class InsertionSort implements Sort{

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int current = nums[i];
            int j = i - 1;
            // 从后往前遍历，如果元素比current大，则将其后移一位，继续比较
            while(j >= 0 && current < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = current;
        }
    }
}
