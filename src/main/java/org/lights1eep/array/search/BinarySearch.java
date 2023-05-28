package org.lights1eep.array.search;

/**
 * 二分查找法
 * @author lights1eep
 */
public class BinarySearch implements Search{

    @Override
    public int search(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (right - left) / 2 + left;
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
