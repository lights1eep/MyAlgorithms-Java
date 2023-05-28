package org.lights1eep.array.search;

/**
 * 插值查找法
 * @author lights1eep
 */
public class InsertSearch implements Search{

    @Override
    public int search(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            // 最右侧值是被查找的值需要单独判断，否则可能会有除零的问题
            if(nums[right] == value) {
                return right;
            }
            int mid = left + (right - left) * (value - nums[left]) / (nums[right] - value);
            if(nums[mid] < value) {
                left = mid + 1;
            } else if (nums[mid] > value) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
