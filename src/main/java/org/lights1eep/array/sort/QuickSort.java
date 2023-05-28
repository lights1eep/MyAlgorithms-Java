package org.lights1eep.array.sort;


import org.lights1eep.utils.SortUtil;

/**
 * 快速排序
 * @author lights1eep
 */
public class QuickSort implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        quickSort(nums, 0, n);
    }

    /**
     * 快速排序
     * @param nums  被排序数组
     * @param left  被排序数组左边界
     * @param right 被排序数组右边界(取不到)
     */
    private void quickSort(int[] nums, int left, int right) {
        if(left + 1 >= right) {
            return;
        }
        int index = partition(nums, left, right - 1);
        quickSort(nums, left, index);
        quickSort(nums, index + 1, right);
    }

    /**
     * 对数组进行分界并排序
     * @param nums  被排序数组
     * @param left  被排序数组左边界
     * @param right 被排序数组右边界
     * @return  原数组第一个值在数组中的最终位置索引
     */
    private int partition(int[] nums, int left, int right) {
        int index = left;
        while (left < right) {
            while(left < right && nums[right] >= nums[index]) {
                right--;
            }
            if(left < right) {
                SortUtil.swap(nums, index, right);
                index = right;
            }
            while(left < right && nums[left] <= nums[index]) {
                left++;
            }
            if(left < right) {
                SortUtil.swap(nums, index, left);
                index = left;
            }
        }
        return index;
    }
}
