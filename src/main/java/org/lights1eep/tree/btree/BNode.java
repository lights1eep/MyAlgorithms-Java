package org.lights1eep.tree.btree;

import java.util.Arrays;

/**
 * B树节点
 * @author lights1eep
 */
public class BNode {
    /**
     * b树的阶
     */
    int m;
    /**
     * 节点存储的关键字个数
     */
    int size;
    /**
     * 是否是叶子节点
     */
    boolean isLeaf;
    /**
     * 关键字数组
     */
    int[] nums;
    /**
     * 父节点
     */
    BNode parent;
    /**
     * 孩子节点数组
     */
    BNode[] children;

    BNode(int m) {
        this.m = m;
        this.size = 0;
        // 因为可能出现关键字个数超过m-1的情况，所以关键字数组的长度比m-1多1
        this.nums = new int[m];
        this.parent = null;
        // 孩子节点数组长度比关键字个数多1
        this.children = new BNode[m + 1];
        this.isLeaf = true;
    }

    /**
     * 在关键字数组中插入关键字
     * @param num 关键字
     */
    public void insertNum(int num) {
        int i = 0;
        // 找到第一个不小于num的索引
        while(i < this.size && num > this.nums[i]) {
            i++;
        }
        // 已经存在
        if(this.nums[i] == num) {
            return;
        }
        // 索引之后的关键字后移 为num腾出空间
        for(int j = this.size; j > i; j--) {
            this.nums[j] = this.nums[j - 1];
        }
        this.nums[i] = num;
        this.size++;
    }

    /**
     * 在孩子节点数组中插入节点
     * @param node  节点
     * @param index 插入索引
     */
    public void insertBNode(BNode node, int index) {
        for(int j = this.size; j > index; j--) {
            this.children[j] = this.children[j - 1];
        }
        this.children[index] = node;
    }

    /**
     * 在关键字数组中查询并返回索引
     * @param num 关键字
     * @return 索引 -1表示未查到
     */
    public int queryByNum(int num) {
        for(int i = 0; i < this.size; i++) {
            if(this.nums[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在孩子节点数组中查询节点索引并返回
     * @param node  节点
     * @return 索引 -1表示未查到
     */
    public int queryByBNode(BNode node) {
        for(int i = 0; i <= this.size; i++) {
            if(this.children[i] == node) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除关键字
     * @param num 关键字
     */
    public void deleteNum(int num) {
        int i = queryByNum(num);
        this.size--;
        while(i < this.size) {
            this.nums[i] = this.nums[i + 1];
            i++;
        }
        this.nums[i] = 0;
    }

    /**
     * 根据索引删除孩子节点
     * @param index 索引
     */
    public void deleteNode(int index) {
        while(index <= this.size) {
            this.children[index] = this.children[index + 1];
            index++;
        }
        this.children[index] = null;
    }

    @Override
    public String toString() {
        return "BNode{" +
                "isLeaf=" + isLeaf +
                ", size=" + size +
                ", nums=" + Arrays.toString(nums) +
                '}';
    }
}
