package org.lights1eep.array.sort;

/**
 * 归并排序 递归
 * @author lights1eep
 */
public class MergeSortRecursion implements Sort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        // 申请额外空间
        int[] result = new int[n];
        mergeRecursion(nums, result, 0, n);
    }

    /**
     *
     * @param nums      被排序数组
     * @param result    额外空间
     * @param left      被排序数组左边界
     * @param right     被排序数组有边界(取不到)
     */
    private void mergeRecursion(int[] nums, int[] result, int left, int right) {
        if(left + 1 >= right) {
            return;
        }
        // 每次递归时将数组分为两部分分别进行归并排序
        int mid = (right - left) / 2 + left;
        mergeRecursion(nums, result, left, mid);
        mergeRecursion(nums, result, mid, right);

        int start1 = left, end1 = mid;
        int start2 = mid, end2 = right;
        int index = left;
        // 将排序结果存到result中
        while(start1 < end1 && start2 < end2) {
            result[index++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
        }
        while(start1 < end1) {
            result[index++] = nums[start1++];
        }
        while(start2 < end2) {
            result[index++] = nums[start2++];
        }
        // 将排序结果从result复制回原数组
        for(int i = left; i < right; i++) {
            nums[i] = result[i];
        }
    }
}
