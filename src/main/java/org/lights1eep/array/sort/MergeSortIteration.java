package org.lights1eep.array.sort;

/**
 * 归并排序 迭代
 * @author lights1eep
 */
public class MergeSortIteration implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        // 申请额外空间
        int[] result = new int[n];
        // 每次迭代时的分组大小，从1到n/2，这里block < n是考虑了奇数长度数组
        for(int block = 1; block < n ; block *= 2) {
            // 多个组进行合并排序
            for(int start = 0; start < n; start += 2 * block) {
                int left = start;
                int mid = start + block < n ? start + block : n;
                int right = start + 2 * block< n ? start + 2 * block : n;

                int start1 = left, end1 = mid;
                int start2 = mid, end2 = right;
                int index = start;
                while(start1 < end1 && start2 < end2) {
                    result[index++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
                }
                while(start1 < end1) {
                    result[index++] = nums[start1++];
                }
                while(start2 < end2) {
                    result[index++] = nums[start2++];
                }
            }
            // 迭代结果
            int[] tmp = nums;
            nums = result;
            result = tmp;
        }
        nums = result;
    }
}
