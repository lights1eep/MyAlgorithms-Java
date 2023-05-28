package org.lights1eep.array.search;

/**
 * 顺序查找
 * @author lights1eep
 */
public class SequenceSearch implements Search{

    @Override
    public int search(int[] nums, int value) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
