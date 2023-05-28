package org.lights1eep.array.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 * @author lights1eep
 */
public class BucketSort implements Sort{

    @Override
    public void sort(int[] nums) {
        bucketSort(nums, 5);
    }

    /**
     * 桶排序
     * @param nums          被排序数组
     * @param bucketSize    桶大小
     */
    private void bucketSort(int[] nums, int bucketSize) {
        int n = nums.length;
        int maxValue= nums[0], minValue = nums[0];

        for(int num : nums) {
            maxValue = maxValue >= num ? maxValue : num;
            minValue = minValue <= num ? minValue : num;
        }
        // 根据桶的大小获取桶的数量
        int bucketNum = (maxValue - minValue) / bucketSize + 1;
        List<ArrayList<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<>());
        }
        // 根据数值范围放到对应桶中
        for(int num : nums) {
            int index = (num - minValue) / bucketSize;
            ArrayList<Integer> list = buckets.get(index);
            list.add(num);
            buckets.set(index, list);
        }
        // 顺序遍历桶，得到排序好的数组
        int index = 0;
        for(List<Integer> bucket : buckets) {
            // 对桶内元素进行排序
            Collections.sort(bucket);
            for(int value : bucket) {
                nums[index++] = value;
            }
        }
    }
}
