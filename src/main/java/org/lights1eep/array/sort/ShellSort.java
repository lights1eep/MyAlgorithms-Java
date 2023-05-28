package org.lights1eep.array.sort;
/**
 * 希尔排序
 * @author lights1eep
 */
public class ShellSort  implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for(int len = n / 2; len >= 1; len /= 2) {
            for(int i = len; i < n; i++) {
                int current = nums[i];
                int j = i - len;
                while(j >= 0 && nums[j] > current) {
                    nums[j + len] = nums[j];
                    j -= len;
                }
                nums[j + len] = current;
            }
        }
    }
}
