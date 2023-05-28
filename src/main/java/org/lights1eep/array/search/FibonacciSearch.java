package org.lights1eep.array.search;

/**
 * 斐波那契查找法
 * @author lights1eep
 */
public class FibonacciSearch implements Search{

    @Override
    public int search(int[] nums, int value) {
        // 斐波那契数列
        int[] f = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
                987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025,
                121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578,
                5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155,
                165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903};
        int n = 0, len = nums.length;
        // 找到第一个大于等于len的n
        while(f[n] < len) {
            n++;
        }
        // 数组拷贝与填充
        int[] arr = new int[f[n]];
        for(int i = 0; i < len; i++) {
            arr[i] = nums[i];
        }
        for(int i = len; i < f[n]; i++) {
            arr[i] = nums[len - 1];
        }
        // 查找
        int left = 0, right = len - 1;
        while(left <= right) {
            int mid = left + f[n - 1] - 1;
            if(arr[mid] < value) {
                left = mid + 1;
                // 长度变短，对应n变化
                n -= 1;
            } else if (arr[mid] > value) {
                right = mid - 1;
                n -= 2;
            } else {
                // 如果查找的索引是填充值，则返回原数组最大的索引
                return mid >= len ? len - 1 : mid;
            }
        }
        return -1;
    }
}
