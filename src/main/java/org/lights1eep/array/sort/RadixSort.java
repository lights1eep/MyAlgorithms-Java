package org.lights1eep.array.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 * @author lights1eep
 */
public class RadixSort implements Sort{

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        int maxDigitValue= Math.abs(nums[0]);
        // 找到位数最多的数
        for(int num : nums) {
            maxDigitValue = maxDigitValue >= Math.abs(num) ? maxDigitValue : Math.abs(num);
        }
        // 获取最大位数
        int maxDigit = 0;
        while(maxDigitValue > 0) {
            maxDigitValue /= 10;
            maxDigit++;
        }
        // 开辟额外空间
        // 考虑负数情况 开20个
        List<ArrayList<Integer>> counters = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            counters.add(new ArrayList<>());
        }

        int mod = 10, dev = 1;

        for(int i = 0; i < maxDigit; i++, mod *= 10, dev *= 10) {
            // 根据num不同位放到其对应的空间中
            for(int num : nums) {
                int index = (num % mod) / dev + 10;
                ArrayList<Integer> list = counters.get(index);
                list.add(num);
                counters.set(index, list);
            }

            // 顺序遍历空间，得到新序列，继续进行排序
            int index = 0;
            for(int j = 0; j < 20; j++) {
                for(int value : counters.get(j)) {
                    nums[index++] = value;
                }
                counters.set(j, new ArrayList<>());
            }
        }
    }
}
