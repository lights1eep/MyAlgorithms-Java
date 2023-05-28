package org.lights1eep.array.sort;


import org.lights1eep.utils.SortUtil;

/**
 * 堆排序
 * @author lights1eep
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] nums) {
        int n = nums.length;

        // 将原数组初始化为大根堆
        for(int i = n - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }

        // 交换大根堆堆顶与最右下节点交换，变成有序序列的第一个元素，然后剩下的无序序列继续变成大根堆
        for(int i = n - 1; i >= 0; i--) {
            SortUtil.swap(nums, 0, i);
            // 堆的大小减一
            n--;
            // 从交换过后的元素开始对堆进行维护
            heapify(nums, 0, n);
        }
    }

    /**
     * 维护堆
     * @param nums      被排序数组
     * @param index     节点在数组中的索引
     * @param n         堆大小
     */
    private void heapify(int[] nums, int index, int n) {
        // 叶子节点
        if(index * 2 + 1 >= n) {
            return;
        }
        // 找到最大值节点
        int leftChild = index * 2 + 1, rightChild = index * 2 + 2;
        int maxIndex = nums[index] > nums[leftChild] ? index : leftChild;
        if(rightChild < n && nums[rightChild] > nums[leftChild]) {
            maxIndex = rightChild;
        }
        // 如果发生了交换
        if(maxIndex != index) {
            SortUtil.swap(nums, maxIndex, index);
            heapify(nums, maxIndex, n);
        }
    }
}
